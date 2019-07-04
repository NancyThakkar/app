package io.priome;

import android.content.Intent;
import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    @Override
    protected String getMainComponentName() {
        return "Priome";
    }
}
