package com.mythicalfish.wonderfulwake;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Alarm extends Activity {
    public int id;
    public int hour;
    public int minute;
    private SharedPreferences prefs;
    private AlarmManager alarmManager;
    private Intent intent;
    private PendingIntent pendingIntent;
    private Context baseCtxt;
    private Context ctxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = ctxt.getSharedPreferences("wonderful", Context.MODE_PRIVATE);

    }

    public Alarm(int hour, int minute, Context baseCtxt, Context ctxt) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        //this.id = (int) ts.getTime(); // Seems to be negative value
        this.id = 1;
        this.hour = hour;
        this.minute = minute;
        this.baseCtxt = baseCtxt;
        this.ctxt = ctxt;
    }

    protected void save() {
        //setPref();
        setIntent();
    }

    private void setIntent() {
        intent = new Intent(baseCtxt, AlarmReceiver.class);
        //pendingIntent = PendingIntent.getBroadcast(baseCtxt, this.id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent = PendingIntent.getBroadcast(baseCtxt, this.id, intent, 0);
        alarmManager = (AlarmManager) ctxt.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, this.hour);
        calendar.set(Calendar.MINUTE, this.minute);
        calendar.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        String alarmTime = sdf.format(calendar.getTime());
        Log.i("ID is:", this.id + "");
        Log.i("Hour is:", this.hour + "");
        Log.i("Minute is:", this.minute + "");
        Log.i("Alarm time is:", alarmTime);
        Toast.makeText(ctxt, "Next alarm at " + alarmTime, Toast.LENGTH_LONG).show();
        //alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void setPref() {
        ArrayList alarmList = new ArrayList<Alarm>();
        alarmList.add(this);
        Gson gson = new Gson();
        String json = gson.toJson(alarmList);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("alarmList", json);
        editor.commit();
    }

    protected Alarm[] retrieveAll() {
        String json = prefs.getString("alarmList", "");
        Gson gson = new Gson();
        Alarm[] alarmList = gson.fromJson(json, Alarm[].class);
        return alarmList;
    }
}
