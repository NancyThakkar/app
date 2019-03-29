package com.mythicalfish.wonderfulwake;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

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
        final Alarm alarm = Alarm.find(id, getApplicationContext());
        timePicker = findViewById(R.id.timePicker);
        setTime(alarm.object.hour, alarm.object.minute);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                alarm.object.hour = hour;
                alarm.object.minute = minute;
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
