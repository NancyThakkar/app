package com.mythicalfish.wonderfulwake;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Alarm {
    private AlarmObject object;
    private AlarmManager alarmManager;
    private Intent intent;
    private PendingIntent pendingIntent;
    private Context ctxt;

    public Alarm(AlarmObject alarmObject, Context context) {
        object = alarmObject;
        ctxt = context;
    }

    static public Alarm build(Number hour, Number minute, Number second, Context context) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Number id = ts.getTime();
        AlarmObject ao = new AlarmObject(id, hour, minute, second);
        return new Alarm(ao, context);
    }

    protected void save() {
        saveAlarm();
        setIntent();
    }

    private void saveAlarm() {
        Hawk.put(object.id + "", object);
        Log.i("Saved alarm object", object.toString());
        ArrayList<Number> idList = Alarm.getAllIDs();
        if(!idList.contains(object.id)) {
            idList.add(object.id);
            Hawk.put("idList", idList);
            Log.i("Saved alarm ID in list", idList.toString());
        }
    }

    private void setIntent() {
        pendingIntent = getPendingIntent();
        alarmManager = (AlarmManager) ctxt.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, getAlarmTime(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Log.i("Set alarm intent", getAlarmTime() + "");
        Toast.makeText(ctxt, "Alarm set", Toast.LENGTH_LONG).show();
    }

    private PendingIntent getPendingIntent() {
        intent = new Intent(ctxt, AlarmReceiver.class);
        return PendingIntent.getBroadcast(ctxt, object.id.intValue(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private long getAlarmTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, object.hour.intValue());
        calendar.set(Calendar.MINUTE, object.minute.intValue());
        calendar.set(Calendar.SECOND, object.second.intValue());
        return calendar.getTimeInMillis();
    }

    static protected ArrayList<Number> getAllIDs() {
        if(Hawk.contains("idList")) return Hawk.get("idList");
        return new ArrayList<>();
    }

    static protected ArrayList<AlarmObject> getAll() {
        ArrayList<AlarmObject> alarmList = new ArrayList<>();
        Iterator<Number> iterator = Alarm.getAllIDs().iterator();
        while (iterator.hasNext()) {
            AlarmObject alarmObject = Hawk.get(iterator.next() + "");
            alarmList.add(alarmObject);
        }
        return alarmList;
    }

    static protected Alarm find(Number alarmID, Context context) {
        String id = alarmID + "";
        if(Hawk.contains(id)) {
            AlarmObject alarmObject = Hawk.get(id);
            return new Alarm(alarmObject, context);
        }
        return null;
    }


    private void delete() {
        ArrayList alarmIDs = Alarm.getAllIDs();
        alarmIDs.remove(object.id);
        Hawk.put("idList", alarmIDs);
        Hawk.delete(object.id + "");
        pendingIntent = getPendingIntent();
        pendingIntent.cancel();
    }

    static void deleteAll(Context context) {
        Iterator<Number> iterator = Alarm.getAllIDs().iterator();
        while (iterator.hasNext()) {
            Number id = iterator.next();
            Alarm a = Alarm.find(id, context);
            a.delete();
        }
        Hawk.put("idList", null);
    }
}

class AlarmObject {
    public Number id;
    public Number hour;
    public Number minute;
    public Number second;
    public AlarmObject(Number id, Number hour, Number minute, Number second) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
}
