package io.wonderfulwake;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import timber.log.Timber;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.e("wake up mofo");
        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_SHORT).show();
        Intent i = new Intent();
        i.setClassName("io.wonderfulwake", "io.wonderfulwake.Sunrise");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
