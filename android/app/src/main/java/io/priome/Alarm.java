package io.priome;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import ca.antonious.materialdaypicker.MaterialDayPicker;
import timber.log.Timber;

public class Alarm {
    AlarmObject object;
    private AlarmManager alarmManager;
    private Intent intent;
    private PendingIntent pendingIntent;
    private Context ctxt;

    public Alarm(AlarmObject alarmObject, Context context) {
        object = alarmObject;
        ctxt = context;
    }

    static public Alarm build(Number hour, Number minute, Number second, Context context, List<MaterialDayPicker.Weekday> days) {
        AlarmObject ao = new AlarmObject(makeID(), hour.intValue(), minute.intValue(), second.intValue(), days);
        return new Alarm(ao, context);
    }

    protected void save() {
        saveAlarm();
        setIntent();
    }

    private void saveAlarm() {
        String id = object.id;
        Hawk.put(id, object);
        Timber.i("Saved alarm object: " + object.toString());
        ArrayList<String> idList = Alarm.getAllIDs();
        if(!idList.contains(id)) {
            idList.add(id);
            Hawk.put("idList", idList);
            Timber.i("Saved alarm ID in list: " + idList.toString());
        }
    }

    protected void setIntent() {
        pendingIntent = getPendingIntent();
        alarmManager = (AlarmManager) ctxt.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, getCalendar().getTimeInMillis(), pendingIntent);
        Timber.i("Set alarm intent: " + getPrettyTime());
    }

    private PendingIntent getPendingIntent() {
        intent = new Intent(ctxt, AlarmReceiver.class);
        intent.putExtra("id", object.id);
        return PendingIntent.getBroadcast(ctxt, Integer.parseInt(object.id), intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, object.hour);
        calendar.set(Calendar.MINUTE, object.minute);
        calendar.set(Calendar.SECOND, object.second);
        Date nowDate = new Date();
        Calendar nowCal = GregorianCalendar.getInstance();
        nowCal.setTime(nowDate);
        if(calendar.before(nowCal)) {
            calendar.add(Calendar.DATE, 1);
        }
        return calendar;
    }

    String getPrettyTime() {
        Calendar calendar = getCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("d M h:mm a");
        return sdf.format(calendar.getTime());
    }

    static protected ArrayList<String> getAllIDs() {
        if(Hawk.get("idList") != null) return Hawk.get("idList");
        return new ArrayList<>();
    }

    static protected ArrayList<Alarm> getAll(Context context) {
        ArrayList<Alarm> alarmList = new ArrayList<>();
        Iterator<String> iterator = Alarm.getAllIDs().iterator();
        while (iterator.hasNext()) {
            String id = iterator.next();
            AlarmObject alarmObject = Hawk.get(id);
            Alarm alarm = new Alarm(alarmObject, context);
            alarmList.add(alarm);
        }
        return alarmList;
    }

    static protected Alarm find(String id, Context context) {
        if(Hawk.contains(id)) {
            AlarmObject alarmObject = Hawk.get(id);
            return new Alarm(alarmObject, context);
        }
        return null;
    }


    void delete() {
        String id = object.id;
        ArrayList alarmIDs = Alarm.getAllIDs();
        if(alarmIDs.contains(id)) {
            alarmIDs.remove(id);
            Hawk.put("idList", alarmIDs);
        }
        if(Hawk.contains(id)) {
            Hawk.delete(id);
        }
        pendingIntent = getPendingIntent();
        pendingIntent.cancel();
    }

    static void deleteAll(Context context) {
        Iterator<String> iterator = Alarm.getAllIDs().iterator();
        while (iterator.hasNext()) {
            String id = iterator.next();
            Alarm a = Alarm.find(id, context);
            if(a != null) {
                a.delete();
            }
        }
        Hawk.put("idList", null);
    }

    static String makeID() {
        String id = (int)(Math.random() * 999 + 1) + "";
        if(!Hawk.contains(id)) return id;
        return makeID();
    }
}

class AlarmObject {
    public String id;
    public int hour;
    public int minute;
    public int second;
    public boolean enabled;
    public List<MaterialDayPicker.Weekday> days;
    public AlarmObject(String id, int hour, int minute, int second, List<MaterialDayPicker.Weekday> days) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.days = days;
    }
}


