package com.mythicalfish.wonderfulwake;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.mythicalfish.wonderfulwake.Alarm.Alarm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewAlarm extends AppCompatActivity {

    private static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_alarm);

        prefs = getSharedPreferences("wonderful", Context.MODE_PRIVATE);
        Button saveBtn = findViewById(R.id.saveBtn);
        final TimePicker timePicker = findViewById(R.id.timePicker);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                Alarm newAlarm = new Alarm(hour, minute);
                ArrayList alarmList = new ArrayList<Alarm>();
                alarmList.add(newAlarm);
                setList(alarmList);
            }
        });
    }

    public static void setList(List<Alarm> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("alarmList", json);
        editor.commit();
    }

}
