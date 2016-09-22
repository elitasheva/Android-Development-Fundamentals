package com.example.etasheva.batterychecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "AlarmReceiver..start BatteryService", Toast.LENGTH_SHORT).show();
        Intent background = new Intent(context, BatteryService.class);
        context.startService(background);
    }
}
