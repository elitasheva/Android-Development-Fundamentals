package com.example.etasheva.kinveytest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.example.etasheva.kinveytest.database.SqliteController;
import com.example.etasheva.kinveytest.models.LaptopKinvey;
import com.example.etasheva.kinveytest.models.LaptopSqlite;
import com.kinvey.android.AsyncAppData;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.User;
import com.kinvey.java.core.KinveyClientCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class LoadDataService extends IntentService {

    private static final String APP_KEY = "kid_Hkz4aiD3";
    private static final String APP_SECRET = "6e30f9fd9c0b4218a6db8d6282ce25a8";
    private static final String COLLECTION_NAME = "laptops";
    public static final String BROADCAST_ACTION_LOGIN = "com.example.etasheva.kinveytest.login";
    public static final String BROADCAST_ACTION_GET_INFO = "com.example.etasheva.kinveytest.getinfo";
    private Client mKinveyClient;
    private IBinder binder;
    private SqliteController mController;


    public class LoadDataServiceBinder extends Binder {
        public LoadDataService getService() {
            return LoadDataService.this;
        }
    }

    public LoadDataService() {
        super("Download service");
    }


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public void onCreate() {
        this.binder = new LoadDataServiceBinder();
        this.mKinveyClient = new Client.Builder(APP_KEY, APP_SECRET, this.getApplicationContext()).build();
        this.mController = new SqliteController(getApplicationContext());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
        SQLiteDatabase mDatabase = this.mController.getWritableDatabase();
        this.mController.onCreate(mDatabase);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show();
        if (this.mKinveyClient != null){
            this.mKinveyClient.user().logout().execute();
        }

        super.onDestroy();
    }



    public void attemptToLogin(){
        if (!this.mKinveyClient.user().isUserLoggedIn()) {
            this.mKinveyClient.user().login(callback);
        }else {
            Intent intentLogin = new Intent(BROADCAST_ACTION_LOGIN);
            intentLogin.putExtra("info_login", "User already logged in");
            sendBroadcast(intentLogin);
        }

    }

    public void attemptToGetInfo(){
        final Intent intentGetInfo = new Intent(BROADCAST_ACTION_GET_INFO);
        AsyncAppData<LaptopKinvey> laptopsInfo = mKinveyClient.appData(COLLECTION_NAME, LaptopKinvey.class);
        laptopsInfo.get(new KinveyListCallback<LaptopKinvey>() {
            @Override
            public void onSuccess(LaptopKinvey[] laptops) {
                intentGetInfo.putExtra("info_get_info","Successfully receive the info");
                sendBroadcast(intentGetInfo);
                for (LaptopKinvey laptop : laptops) {
                     HashMap<String,String> laptopData = new LinkedHashMap<String, String>();
                    laptopData.put(SqliteController.MODEL_COLUMN,laptop.getModel());
                    laptopData.put(SqliteController.RAM_COLUMN,laptop.getCapacity_ram());
                    laptopData.put(SqliteController.HDD_COLUMN,laptop.getCapacity_hdd());
                    laptopData.put(SqliteController.PROCESSOR_COLUMN,laptop.getProcessor_type());
                    laptopData.put(SqliteController.VIDEO_CARD_COLUMN,laptop.getVideo_card_type());
                    laptopData.put(SqliteController.DISPLAY_COLUMN,laptop.getDisplay_size());
                    laptopData.put(SqliteController.CURRENCY_COLUMN,laptop.getCurrency());
                    laptopData.put(SqliteController.PRICE_COLUMN,laptop.getPrice());
                    laptopData.put(SqliteController.IMAGE_COLUMN,laptop.getImage());
                    mController.insertRecord(laptopData);
                    Toast.makeText(LoadDataService.this, "Laptop model " + laptop.getModel() + " added", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Throwable throwable) {
                intentGetInfo.putExtra("info_get_info","Fail to receive the info");
                sendBroadcast(intentGetInfo);
            }

        });

    }

    public ArrayList<LaptopSqlite> showResult(){
        ArrayList<LaptopSqlite> result = this.mController.getAllData();
        return result;
    }

    public void dropTable(){
        if (this.mController != null){
            this.mController.dropTable();
            Toast.makeText(this, "Table dropped", Toast.LENGTH_SHORT).show();
        }
    }

    KinveyClientCallback<User> callback = new KinveyClientCallback<User>() {
        Intent intentLogin = new Intent(BROADCAST_ACTION_LOGIN);
        @Override
        public void onSuccess(User user) {
            intentLogin.putExtra("info_login", "Successfully logged in");
            sendBroadcast(intentLogin);
        }

        @Override
        public void onFailure(Throwable throwable) {
            intentLogin.putExtra("info_login", "Something wrong");
            sendBroadcast(intentLogin);
        }
    };

}
