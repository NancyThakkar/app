package io.wonderfulwake;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.NoEncryption;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        Timber.i("Alarm triggered");

        // Set the next alarm
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            Timber.i("Setting next alarm");
            //Hawk.init(context).setEncryption(new NoEncryption()).build();
            Bundle args = intent.getExtras();
            String id = (String) args.get("id");
            Alarm alarm = Alarm.find(id, context);
            alarm.setIntent();
            Timber.i("Set the next alarm");
            Thread.currentThread().interrupt();
        }

        // Show sunrise
        Intent i = new Intent();
        i.setClassName("io.wonderfulwake", "io.wonderfulwake.Sunrise");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

}
