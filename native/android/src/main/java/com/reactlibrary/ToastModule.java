package com.reactlibrary;

/**
 * Created by Rashed on Feb,2019
 */
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;
import java.util.HashMap;

public class ToastModule extends ReactContextBaseJavaModule {

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    //constructor
    public ToastModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    //Mandatory function getName that specifies the module name
    @Override
    public String getName() {
        return "ToastExample";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }

    //Custom function that we are going to export to JS
    @ReactMethod
    public void show(String message, int duration) {
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

    //Custom function that we are going to export to JS
    @ReactMethod
    public void getDeviceName(Callback cb) {
        try{
            cb.invoke(null, android.os.Build.MODEL);
        }catch (Exception e){
            cb.invoke(e.toString(), null);
        }
    }
}
