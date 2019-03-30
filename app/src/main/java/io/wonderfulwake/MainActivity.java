package io.wonderfulwake;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.NoEncryption;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Init state, settings & view
        super.onCreate(savedInstanceState);
        Hawk.init(this).setEncryption(new NoEncryption()).build();
        setContentView(R.layout.home);

        showList();

        // New alarm button
        FloatingActionButton newAlarmBtn = findViewById(R.id.newAlarm);
        newAlarmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent newAlarm = new Intent(getApplicationContext(), NewAlarm.class);
                startActivity(newAlarm);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showList();
    }

    protected void showList() {
        ListView alarmList = findViewById(R.id.alarmList);
        ArrayList<Alarm> alarms = Alarm.getAll(getApplicationContext());
        AlarmListAdapter adapter = new AlarmListAdapter(this, R.layout.list_item, alarms);
        alarmList.setAdapter(adapter);

    }

}
