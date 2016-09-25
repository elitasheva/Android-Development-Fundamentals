package com.example.etasheva.batterychecker;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;


public class MainActivity extends AppCompatActivity {

    private Context mCtx;
    private TextView mTextViewCurrentLevel;
    private TextView mTextViewDiff;
    private TextView mTextViewTime;
    private BatteryService mBatteryService;
    private Intent mBatteryServiceIntent;
    private boolean mIsBinded;
    private boolean mIsRegistred;
    private Time mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        this.mCtx = this;
        this.mTextViewCurrentLevel = (TextView) this.findViewById(R.id.current_level);
        this.mTextViewDiff = (TextView) this.findViewById(R.id.diff);
        this.mTextViewTime = (TextView) this.findViewById(R.id.time);
        this.mBatteryServiceIntent = new Intent(this, BatteryService.class);
        this.mTime = new Time();

        //the alarm is set to 3 min in order to avoid waiting one hour
        Intent alarm = new Intent(this.mCtx, AlarmReceiver.class);
        boolean alarmRunning = (PendingIntent.getBroadcast(this, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
        if (!alarmRunning) {
            PendingIntent pIntent = PendingIntent.getBroadcast(this.mCtx, 0, alarm, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 180000, pIntent);

        }

        if (this.isBatteryServiceRunning(BatteryService.class)) {
            bindService(this.mBatteryServiceIntent, connection, Context.BIND_AUTO_CREATE);
        } else {
            this.mTextViewCurrentLevel.setText("No connection with service");
            this.mTextViewDiff.setText("");
            this.mTextViewTime.setText("Please wait...");
        }



        this.registerReceiver(batteryInfoReceiver, new IntentFilter(BatteryService.BROADCAST_ACTION));
        this.mIsRegistred = true;

    }

    BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int diff = intent.getIntExtra("diff", Integer.MIN_VALUE);
            int oldValue = intent.getIntExtra("oldValue", Integer.MIN_VALUE);
            Toast.makeText(context, "Receiver", Toast.LENGTH_SHORT).show();

            if (diff != Integer.MIN_VALUE && oldValue != Integer.MIN_VALUE) {
                mTextViewCurrentLevel.setText(String.format(Locale.getDefault(), "Old battery level: %d %%", oldValue));
                mTextViewDiff.setText(String.format(Locale.getDefault(), "Battery change its level in the last hour with: %d %%", diff));
                mTime.setToNow();
                mTextViewTime.setText(mTime.format("%k:%M:%S"));
            }

        }
    };

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BatteryService.BatteryServiceBinder binder = (BatteryService.BatteryServiceBinder) service;
            mBatteryService = binder.getService();
            mIsBinded = true;
            Toast.makeText(mCtx, "Service binded", Toast.LENGTH_SHORT).show();
            int currentLevel = mBatteryService.checkLevelOfBattery();
            mTextViewCurrentLevel.setText(String.format(Locale.getDefault(), "Current battery level: %d %%", currentLevel));
            int diff = Math.abs(mBatteryService.getOldValue() - currentLevel);
            mTextViewDiff.setText(String.format(Locale.getDefault(), "Until this moment the battery level was changed with: %d %%", diff));
            mTime.setToNow();
            mTextViewTime.setText(mTime.format("%k:%M:%S"));

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBinded = false;
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mIsRegistred) {
            this.unregisterReceiver(batteryInfoReceiver);
            this.mIsRegistred = false;
        }

        if (this.mIsBinded) {
            this.unbindService(connection);
            this.mIsBinded = false;
        }
    }

    private boolean isBatteryServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = manager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo service : services) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}

