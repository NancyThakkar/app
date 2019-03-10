package com.mythicalfish.wonderfulwake;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hawk.init(this).setEncryption(HawkBuilder.EncryptionMethod.NO_ENCRYPTION);
        Hawk.init(getApplicationContext()).build();
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
