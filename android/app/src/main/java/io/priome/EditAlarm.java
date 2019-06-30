package io.priome;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditAlarm extends AlarmActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button deleteBtn = findViewById(R.id.deleteBtn);
        Bundle args = getIntent().getExtras();
        String id = (String) args.get("id");
        final Alarm alarm = io.priome.Alarm.find(id, getApplicationContext());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.delete();
                finish();
            }
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.edit_alarm;
    }

}
