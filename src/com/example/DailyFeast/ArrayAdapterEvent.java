package com.example.DailyFeast;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

 import java.util.ArrayList;

public class ArrayAdapterEvent extends ArrayAdapter<Event> {

    Context mContext;
    int layoutResourceId;

    ArrayList<Event> data = null;

    public ArrayAdapterEvent(Context mContext, int layoutResourceId, ArrayList data) {

        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }
        // object event based on the position
        Event event = data.get(position);
        // get the TextView and then set the text (event name) and tag (event description) values
        TextView textViewItem = (TextView) convertView.findViewById(R.id.textViewEvent);
        textViewItem.setText(event.getTitle());
        String formattedTag = "<p>" + event.getLocation() + "</p> <p>" + event.getDescription() + "</p>";
        textViewItem.setTag(Html.fromHtml(formattedTag));

        return convertView;
    }

}
