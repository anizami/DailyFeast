package com.macalester.DailyFeast;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class EventsArrayAdapter extends ArrayAdapter<Event> {

    Context mContext;
    int layoutResourceId;

    List<Event> data = null;

    public EventsArrayAdapter(Context mContext, int layoutResourceId, List<Event> data) {

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
        textViewItem.setTag(event);
        //String formattedTag = "<p>" + event.getTime() + "</p> <p>" + event.getLocation() + "</p> <p>" + event.getDescription() + "</p>";
        //textViewItem.setTag(Html.fromHtml(formattedTag));

        return convertView;
    }

}
