package com.mythicalfish.wonderfulwake;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;


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
                Alarm newAlarm = new Alarm(hour, minute);
                newAlarm.save();
            }
        });
    }



}
