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
import java.util.Arrays;
import java.util.Calendar;

public class Alarm {
    private AlarmObject object;
    static private SharedPreferences prefs;
    private AlarmManager alarmManager;
    private Intent intent;
    private PendingIntent pendingIntent;
    private Context ctxt;
    private Calendar calendar;

    public Alarm(int hour, int minute, int second, Context context) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        int id = System.identityHashCode(object);
        object = new AlarmObject(id, hour, minute, second);
        ctxt = context;
        prefs = ctxt.getSharedPreferences("wonderful", Context.MODE_PRIVATE);
    }

    protected void save() {
        setPref();
        setIntent();
    }

    private void setIntent() {
        intent = new Intent(ctxt, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(ctxt, object.id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) ctxt.getSystemService(Context.ALARM_SERVICE);
        calendar = getCalendar();
        ArrayList<AlarmObject> list = getList();
        Log.i("ID is:", object.id + "");
        Log.i("Hour is:", object.hour + "");
        Log.i("Minute is:", object.minute + "");
        Log.i("Alarm date is:", getDateString());
        Log.i("List", list.toString());
        Toast.makeText(ctxt, "Alarm set", Toast.LENGTH_LONG).show();
        // TODO: Change to setInexactRepeating
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, object.hour);
        calendar.set(Calendar.MINUTE, object.minute);
        calendar.set(Calendar.SECOND, object.second);
        return calendar;
    }

    private String getDateString() {
        calendar = getCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        return sdf.format(calendar.getTime());
    }

    private void setPref() {
        ArrayList<AlarmObject> alarmList = getList();
        alarmList.add(object);
        Gson gson = new Gson();
        String json = gson.toJson(alarmList);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("alarmList", json);
        editor.commit();
    }

    static protected ArrayList<AlarmObject> getList() {
        String json = prefs.getString("alarmList", "");
        Gson gson = new Gson();
        AlarmObject[] alarmList = gson.fromJson(json, AlarmObject[].class);
        return new ArrayList<>(Arrays.asList(alarmList));
    }
}

class AlarmObject {
    public int id;
    public int hour;
    public int minute;
    public int second;
    public AlarmObject(int id, int hour, int minute, int second) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
}
