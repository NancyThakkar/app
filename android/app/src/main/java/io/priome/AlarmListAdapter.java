package io.priome;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AlarmListAdapter extends ArrayAdapter<Alarm> {

    private static final String TAG = "AlarmListAdapter";
    private Context mContext;
    int mResource;

    public AlarmListAdapter(Context context, int resource, ArrayList<Alarm> alarms) {
        super(context, resource, alarms);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Alarm alarm = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView =   inflater.inflate(mResource, parent, false);
        TextView tvTime = convertView.findViewById(R.id.time);
        TextView tvID = convertView.findViewById(R.id.id);
        tvTime.setText(alarm.getPrettyTime());
        tvID.setText(alarm.object.id);
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent editAlarm = new Intent(mContext, EditAlarm.class);
                editAlarm.putExtra("id", alarm.object.id);
                mContext.startActivity(editAlarm);
            }
        });
        return convertView;
    }


}
