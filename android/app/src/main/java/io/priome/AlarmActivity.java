package io.priome;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ca.antonious.materialdaypicker.MaterialDayPicker;

public abstract class AlarmActivity extends AppCompatActivity {

    private TimePicker timePicker;

    protected abstract int getLayoutResourceId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        final Alarm alarm;
        timePicker = findViewById(R.id.timePicker);
        final MaterialDayPicker dayPicker = findViewById(R.id.dayPicker);

        // Get existing alarm (if ID given) or make a new one
        String id = "";
        Bundle args = getIntent().getExtras();
        if(args != null) id = (String) args.get("id");
        if(id != "") {
            alarm = Alarm.find(id, getApplicationContext());
            setTime(alarm.object.hour, alarm.object.minute);
            dayPicker.setSelectedDays(alarm.object.days);
        } else {
            int hour = timePicker.getCurrentHour();
            int minute = timePicker.getCurrentMinute();
            int second = 0;
            List<MaterialDayPicker.Weekday> days = new ArrayList<>();
            alarm = Alarm.build(hour, minute, second, getApplicationContext(), days);
        }

        // On select days
        dayPicker.setDaySelectionChangedListener(new MaterialDayPicker.DaySelectionChangedListener() {
            @Override
            public void onDaySelectionChanged(List<MaterialDayPicker.Weekday> selectedDays) {
               alarm.object.days = selectedDays;
            }
        });

        // On click save
        Button saveBtn = findViewById(R.id.saveBtn);
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
