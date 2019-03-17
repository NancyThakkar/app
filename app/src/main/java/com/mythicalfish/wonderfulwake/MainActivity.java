package com.mythicalfish.wonderfulwake;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Init state, settings & view
        super.onCreate(savedInstanceState);
        Hawk.init(getApplicationContext()).build();
        setContentView(R.layout.home);

        // New alarm button
        FloatingActionButton newAlarmBtn = findViewById(R.id.newAlarm);
        newAlarmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent newAlarm = new Intent(getApplicationContext(), NewAlarm.class);
                startActivity(newAlarm);
            }
        });

        // List of alarms
        ListView alarmList = findViewById(R.id.alarmList);
        ArrayList<AlarmObject> alarms = Alarm.getAll();
        AlarmListAdapter adapter = new AlarmListAdapter(this, R.layout.list_item, alarms);
        alarmList.setAdapter(adapter);

    }

}
