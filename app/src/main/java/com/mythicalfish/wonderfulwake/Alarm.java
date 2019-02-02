package com.mythicalfish.wonderfulwake;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Alarm extends AppCompatActivity {
    public final int hour;
    public final int minute;
    private SharedPreferences prefs = getSharedPreferences("wonderful", Context.MODE_PRIVATE);

    public Alarm(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    protected void save() {
        ArrayList alarmList = new ArrayList<Alarm>();
        alarmList.add(this);
        Gson gson = new Gson();
        String json = gson.toJson(alarmList);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("alarmList", json);
        editor.commit();
    }

    protected Alarm[] retrieveAll() {
        String json = prefs.getString("alarmList", "");
        Gson gson = new Gson();
        Alarm[] alarmList = gson.fromJson(json, Alarm[].class);
        return alarmList;
    }
}
