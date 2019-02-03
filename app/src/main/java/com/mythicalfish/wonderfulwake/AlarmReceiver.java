package com.mythicalfish.wonderfulwake;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Alarm!", "wake up mofo");
        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_SHORT).show();
    }
}
