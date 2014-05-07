package com.macalester.DailyFeast;

/**
 * Created by fabiolagutierrez on 2/27/14.
 */

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;


public class OnEventClickListener implements OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Context context = view.getContext();

        TextView textViewEvent = ((TextView) view.findViewById(R.id.textViewEvent));

        //get the item (event) clicked
        Event eventClicked = (Event) textViewEvent.getTag();

        Intent intent = new Intent(context, SpecificEventActivity.class);
        intent.putExtra(TodaysEventsActivity.EXTRA_TITLE, eventClicked.getTitle());
        intent.putExtra(TodaysEventsActivity.EXTRA_TIME,  eventClicked.getTime());
        intent.putExtra(TodaysEventsActivity.EXTRA_LOCATION,  eventClicked.getLocation());
        intent.putExtra(TodaysEventsActivity.EXTRA_DESCRIPTION,  eventClicked.getDescription());
        context.startActivity(intent);

    }


}



