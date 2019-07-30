package com.reactlibrary;

/**
 * Created by Rashed on Feb,2019
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.NoEncryption;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import ca.antonious.materialdaypicker.MaterialDayPicker;
import timber.log.Timber;

public class ToastListModule extends ReactContextBaseJavaModule {

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    //constructor
    public ToastListModule(ReactApplicationContext reactContext) {
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
  /*  @ReactMethod
    public void getDeviceName(Callback cb) {
        try{
            cb.invoke(null, android.os.Build.MODEL);
        }catch (Exception e){
            cb.invoke(e.toString(), null);
        }
    }*/



  private static WritableMap convertJsonToMap(JSONObject jsonObject) throws JSONException {
    WritableMap map = new WritableNativeMap();

    Iterator<String> iterator = jsonObject.keys();
    while (iterator.hasNext()) {
      String key = iterator.next();
      Object value = jsonObject.get(key);
      if (value instanceof JSONObject) {
        map.putMap(key, convertJsonToMap((JSONObject) value));
      } else if (value instanceof Boolean) {
        map.putBoolean(key, (Boolean) value);
      } else if (value instanceof Integer) {
        map.putInt(key, (Integer) value);
      } else if (value instanceof Double) {
        map.putDouble(key, (Double) value);
      } else if (value instanceof String) {
        map.putString(key, (String) value);
      } else {
        map.putString(key, value.toString());
      }
    }
    return map;
  }

  @ReactMethod
  public void returnArrayOfObjects(Callback successCallback) throws JSONException {
     Hawk.init(getReactApplicationContext()).setEncryption(new NoEncryption()).build();
          // Logging
          if (BuildConfig.DEBUG) {
              Timber.plant(new Timber.DebugTree());
          } else {
              // TODO: Implement prod logging library
          }

      ArrayList<String> idList = Alarm.getAllIDs();
           Gson g = new Gson();
           WritableArray array = new WritableNativeArray();
    for(String s:idList){
        AlarmObject alarmObject = Hawk.get(s);
              JSONObject jo = new JSONObject(g.toJson(alarmObject));
              WritableMap wm = convertJsonToMap(jo);
              array.pushMap(wm);
        Log.e("idList",new Gson().toJson(alarmObject));
    }
  /*    ArrayList<Alarm> alarms = Alarm.getAll(getReactApplicationContext());
      for (Alarm co : alarms) {
      JSONObject jo = new JSONObject(g.toJson(co));
      WritableMap wm = convertJsonToMap(jo);
      array.pushMap(wm);
    }*/

    successCallback.invoke(array);
  }
  @ReactMethod
  public void saveAlarm(int hour, int minute, int second) {
      Hawk.init(getReactApplicationContext()).setEncryption(new NoEncryption()).build();
      List<MaterialDayPicker.Weekday> days = new ArrayList<>();
      Alarm alarm=Alarm.build(hour, minute, second, getReactApplicationContext(), days);
      alarm.object.hour = (int) hour;
      alarm.object.minute =(int)  minute;
      alarm.object.second = 0;
      alarm.save();

 /*      Intent intent = new Intent(getReactApplicationContext(), AlarmReceiver.class);
              PendingIntent pendingIntent = PendingIntent.getBroadcast(
                      getReactApplicationContext().getApplicationContext(), 234, intent, 0);
              AlarmManager alarmManager = (AlarmManager)getReactApplicationContext(). getSystemService(Context.ALARM_SERVICE);
              alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (5 * 1000), pendingIntent);
              Toast.makeText(getReactApplicationContext(), "Alarm set to after " + 5 + " seconds",Toast.LENGTH_LONG).show();*/
  }
}
