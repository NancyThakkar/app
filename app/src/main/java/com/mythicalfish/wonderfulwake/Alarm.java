package com.mythicalfish.wonderfulwake;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Alarm {
    public int id;
    public int hour;
    public int minute;
    public int second;
    private SharedPreferences prefs;
    private AlarmManager alarmManager;
    private Intent intent;
    private PendingIntent pendingIntent;
    private Context ctxt;
    private Calendar calendar;

    public Alarm(int hour, int minute, int second, Context ctxt) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        this.id = (int) ts.getTime(); // Seems to be negative value
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.ctxt = ctxt;
        this.prefs = ctxt.getSharedPreferences("wonderful", Context.MODE_PRIVATE);
    }

    protected void save() {
        //setPref();
        setIntent();
    }

    private void setIntent() {
        intent = new Intent(ctxt, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(ctxt, this.id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) ctxt.getSystemService(Context.ALARM_SERVICE);
        calendar = getCalendar();
        Log.i("ID is:", this.id + "");
        Log.i("Hour is:", this.hour + "");
        Log.i("Minute is:", this.minute + "");
        Log.i("Alarm date is:", getDateString());
        Toast.makeText(ctxt, "Alarm set", Toast.LENGTH_LONG).show();
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, this.hour);
        calendar.set(Calendar.MINUTE, this.minute);
        calendar.set(Calendar.SECOND, this.second);
        return calendar;
    }

    private String getDateString() {
        calendar = getCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        return sdf.format(calendar.getTime());
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
