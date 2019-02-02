package com.mythicalfish.wonderfulwake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        FloatingActionButton newAlarmBtn = findViewById(R.id.newAlarm);
        newAlarmBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent newAlarm = new Intent(getApplicationContext(), NewAlarm.class);
                startActivity(newAlarm);
            }
        });
    }
}
