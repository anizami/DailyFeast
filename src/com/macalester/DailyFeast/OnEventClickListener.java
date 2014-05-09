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
        intent.putExtra(EventAttributes.TITLE.toString(), eventClicked.getTitle());
        intent.putExtra(EventAttributes.TIME.toString(), eventClicked.getTime());
        intent.putExtra(EventAttributes.LOCATION.toString(), eventClicked.getLocation());
        intent.putExtra(EventAttributes.DESCRIPTION.toString(), eventClicked.getDescription());
        context.startActivity(intent);
    }
}



