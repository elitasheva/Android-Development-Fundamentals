package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ListAdapter extends ArrayAdapter<Earthquake> {

    public ListAdapter(Context context, int resource, List<Earthquake> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_layout,null);
        }

        Earthquake current = getItem(position);
        if (current != null){
            TextView t1 = (TextView) convertView.findViewById(R.id.text_magnitude);
            t1.setText(String.valueOf(current.getMagnitude()));
            GradientDrawable magnitudeCircle = (GradientDrawable) t1.getBackground();
            int magnitudeColor = this.getMagnitudeColor(current.getMagnitude());
            magnitudeCircle.setColor(magnitudeColor);

            TextView t2 = (TextView) convertView.findViewById(R.id.text_location);
            TextView t4 = (TextView) convertView.findViewById(R.id.text_offset);

            String location = current.getLocation();
            int indexOf = location.indexOf("of");
            if (indexOf == -1){
                t4.setText("Near the ");
                t2.setText(location);
            }else {
                String offset = location.substring(0,indexOf + 2);
                t4.setText(offset);
                t2.setText(location.substring(indexOf + 3));
            }


            TextView t3 = (TextView) convertView.findViewById(R.id.text_date);
            //SimpleDateFormat formater = new SimpleDateFormat("MMM DD, yyyy h:mm a");
            SimpleDateFormat formaterDate = new SimpleDateFormat("LLL dd, yyyy");
            String dateStr = formaterDate.format(current.getDate());
            t3.setText(dateStr);

            TextView t5 = (TextView) convertView.findViewById(R.id.text_time);
            SimpleDateFormat formaterTime = new SimpleDateFormat("h:mm a");
            String timeStr = formaterTime.format(current.getDate());
            t5.setText(timeStr);
        }
        return convertView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
