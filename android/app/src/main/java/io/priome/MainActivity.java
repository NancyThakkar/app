package io.priome;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.NoEncryption;

import java.util.ArrayList;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
    private static final String KEY_FIRST_FRAGMENT = "KEY_FIRST_FRAGMENT";
    private static final String KEY_SECOND_FRAGMENT = "KEY_SECOND_FRAGMENT";
    private static final String KEY_THIRD_FRAGMENT = "KEY_THIRD_FRAGMENT";

    private static final String KEY_SELECT_TAB = "KEY_SELECT_TAB";

    private Fragment[] mFragments = new Fragment[3];
    private int mSelectTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Hawk.init(this).setEncryption(new NoEncryption()).build();
        // Logging
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // TODO: Implement prod logging library
        }

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

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
}
