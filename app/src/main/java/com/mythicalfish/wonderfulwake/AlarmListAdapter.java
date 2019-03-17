package com.mythicalfish.wonderfulwake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AlarmListAdapter extends ArrayAdapter<AlarmObject> {

    private static final String TAG = "AlarmListAdapter";
    private Context mContext;
    int mResource;

    public AlarmListAdapter(Context context, int resource, ArrayList<AlarmObject> alarms) {
        super(context, resource, alarms);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AlarmObject alarm = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView =   inflater.inflate(mResource, parent, false);
        TextView tvTime = convertView.findViewById(R.id.time);
        TextView tvID = convertView.findViewById(R.id.id);
        tvTime.setText(alarm.hour + "");
        tvID.setText(alarm.id + "");
        return convertView;
    }
}
