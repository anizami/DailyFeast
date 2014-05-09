package com.macalester.DailyFeast;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by fabiolagutierrez on 3/28/14.
 */
public class EventsArrayAdapter extends ArrayAdapter<Event> {

    private Context mContext;
    private int layoutResourceId;

    private List<Event> data = null;

    public EventsArrayAdapter(Context mContext, int layoutResourceId, List<Event> data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }
        Event event = data.get(position);
        // get the TextView and then set the text (event name) and tag (event description) values
        TextView textViewItem = (TextView) convertView.findViewById(R.id.textViewEvent);
        textViewItem.setText(event.getTitle());
        textViewItem.setTag(event);
        return convertView;
    }
}
