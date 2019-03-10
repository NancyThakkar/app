package com.mythicalfish.wonderfulwake;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;

public class Alarm extends Activity {
    private AlarmObject object;
    static private SharedPreferences prefs;
    private AlarmManager alarmManager;
    private Intent intent;
    private PendingIntent pendingIntent;
    private Context ctxt;
    private Calendar calendar;
    static private ArrayList<AlarmObject> alarmList;

    public Alarm(AlarmObject alarmObject, Context context) {
        object = alarmObject;
        ctxt = context;
        prefs = ctxt.getSharedPreferences("wonderful", Context.MODE_PRIVATE);
    }

    static protected Alarm find(int id, Context context) {
        for(AlarmObject alarm : Alarm.all()){
            if(alarm.id == id) return new Alarm(alarm, context);
        }
        AlarmObject ar  = new AlarmObject(0,0,0,0);
        return new Alarm(ar, context); // TODO: Would rather return false...
    }

    protected void save() {
        // TODO: This will always add, not update existing
        savePref();
        setIntent();
    }

    private void setIntent() {
        calendar = getCalendar();
        pendingIntent = getPendingIntent();
        alarmManager = (AlarmManager) ctxt.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        ArrayList<AlarmObject> list = Alarm.all();
        Log.i("ID is:", object.id + "");
        Log.i("Hour is:", object.hour + "");
        Log.i("Minute is:", object.minute + "");
        Log.i("Alarm date is:", getDateString());
        Log.i("List", list.toString());
        Toast.makeText(ctxt, "Alarm set", Toast.LENGTH_LONG).show();
    }

    private PendingIntent getPendingIntent() {
        intent = new Intent(ctxt, AlarmReceiver.class);
        return PendingIntent.getBroadcast(ctxt, object.id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
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

    private void saveListPref(ArrayList<AlarmObject> alarmList) {
        Gson gson = new Gson();
        String json = gson.toJson(alarmList);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("alarmList", json);
        editor.commit();
    }

    private void savePref() {
        alarmList = Alarm.all();
        alarmList.add(object);
        saveListPref(alarmList);
    }

    static protected ArrayList<AlarmObject> all() {
        String json = prefs.getString("alarmList", "");
        Gson gson = new Gson();
        AlarmObject[] alarmList = gson.fromJson(json, AlarmObject[].class);
        return new ArrayList<>(Arrays.asList(alarmList));
    }

    private void destroy() {
        alarmList = Alarm.all();
        for (Iterator<AlarmObject> iterator = alarmList.iterator(); iterator.hasNext(); ) {
            AlarmObject alarm = iterator.next();
            if(alarm.id == object.id)
                iterator.remove();
        }
        saveListPref(alarmList);
        pendingIntent = getPendingIntent();
        pendingIntent.cancel();
    }

    static void destroyAll(Context context) {
        alarmList = Alarm.all();
        for(AlarmObject item : alarmList){
            Alarm alarm = Alarm.find(item.id, context);
            alarm.destroy();
        }
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
