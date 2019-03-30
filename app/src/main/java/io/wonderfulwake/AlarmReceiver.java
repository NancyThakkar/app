package io.wonderfulwake;

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
        Intent i = new Intent();
        i.setClassName("io.wonderfulwake", "io.wonderfulwake.Sunrise");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
