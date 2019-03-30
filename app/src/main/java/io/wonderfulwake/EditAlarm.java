package io.wonderfulwake;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditAlarm extends AppCompatActivity {

    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_alarm);

        Button saveBtn = findViewById(R.id.saveBtn);
        Button deleteBtn = findViewById(R.id.deleteBtn);
        Bundle args = getIntent().getExtras();
        String id = (String) args.get("id");
        final io.wonderfulwake.Alarm alarm = io.wonderfulwake.Alarm.find(id, getApplicationContext());
        timePicker = findViewById(R.id.timePicker);
        setTime(alarm.object.hour, alarm.object.minute);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                alarm.object.hour = hour;
                alarm.object.minute = minute;
                alarm.object.second = 0;
                /*Calendar cal = getTestCal();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND) + 30;
                alarm.object.hour = hour;
                alarm.object.minute = minute;
                alarm.object.second = second;*/
                alarm.save();
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.delete();
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

    private void setTime(int hour, int minute) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(hour);
            timePicker.setMinute(minute);
        } else {
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }
    }
}
