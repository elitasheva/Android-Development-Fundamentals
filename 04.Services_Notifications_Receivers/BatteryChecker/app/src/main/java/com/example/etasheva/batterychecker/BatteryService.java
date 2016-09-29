package com.example.etasheva.batterychecker;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Locale;

public class BatteryService extends Service {

    public class BatteryServiceBinder extends Binder {

        public BatteryService getService() {
            return BatteryService.this;
        }
    }

    public static final String BROADCAST_ACTION = "com.example.etasheva.batterychecker.batteryprogress";
    private IBinder mBinder;
    private int mOldValue;
    private int mDiff;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    public int getOldValue() {
        return this.mOldValue;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mBinder = new BatteryServiceBinder();
        Toast.makeText(getApplicationContext(), "Service onCreate called", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int level = checkLevelOfBattery();
        if (this.mOldValue == 0) {
            this.mDiff = 0;
            this.mOldValue = level;
        } else {
            this.mDiff = Math.abs(this.mOldValue - level);
        }

        String message = String.format(Locale.getDefault(), "Service onStart called .. battery level: %d %%", level);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

        Intent intentForBroadcast = new Intent(BROADCAST_ACTION);
        intentForBroadcast.putExtra("diff", this.mDiff);
        intentForBroadcast.putExtra("oldValue", this.mOldValue);
        this.mOldValue = level;
        sendBroadcast(intentForBroadcast);

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Service onDestroy called", Toast.LENGTH_SHORT).show();
    }

    public int checkLevelOfBattery() {
        Intent batteryIntent = getApplicationContext().registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra("level", Integer.MIN_VALUE);

        return level;
    }


}
