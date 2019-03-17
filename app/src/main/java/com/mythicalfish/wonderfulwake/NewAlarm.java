package com.mythicalfish.wonderfulwake;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.widget.Toast.*;


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
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                int second = 0;
                //Calendar cal = getTestCal();
                //Number hour = cal.get(Calendar.HOUR_OF_DAY);
                //Number minute = cal.get(Calendar.MINUTE);
                //Number second = cal.get(Calendar.SECOND);
                Alarm newAlarm = Alarm.build(hour, minute, second, getBaseContext());
                newAlarm.save();
                Toast.makeText(getApplicationContext(), "Alarm set", LENGTH_LONG).show();
                finish();
            }
        });
    }

    private Calendar getTestCal() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

}
