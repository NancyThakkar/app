package io.priome;

import android.os.Bundle;

public class NewAlarm extends AlarmActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.new_alarm;
    }

}
