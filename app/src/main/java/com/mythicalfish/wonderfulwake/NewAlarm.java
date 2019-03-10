package com.mythicalfish.wonderfulwake;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class NewAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_alarm);

        Button saveBtn = findViewById(R.id.saveBtn);
        final TimePicker timePicker = findViewById(R.id.timePicker);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int hour = timePicker.getCurrentHour();
                //int minute = timePicker.getCurrentMinute();
                //Alarm newAlarm = new Alarm(hour, minute, getBaseContext());
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                int id = (int) ts.getTime();
                AlarmObject alarmObject = new AlarmObject(id, getCurrentHour(), getCurrentMinute(), getSec());
                Alarm newAlarm = new Alarm(alarmObject, getBaseContext());
                newAlarm.save();
            }
        });
    }

    private Calendar getCal() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private int getCurrentHour() {
        Calendar cal = getCal();
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    private int getCurrentMinute() {
        Calendar cal = getCal();
        return cal.get(Calendar.MINUTE);
    }

    private int getSec() {
        Calendar cal = getCal();
        return cal.get(Calendar.SECOND) + 5;
    }


}
