
package com.example.DailyFeast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

@SuppressLint("NewApi")

public class TodaysEventsActivity extends Activity{
    public final static String EXTRA_TITLE = "com.example.DailyFeast.TITLE";
    public final static String EXTRA_TIME = "com.example.DailyFeast.TIME";
    public final static String EXTRA_LOCATION = "com.example.DailyFeast.LOCATION";
    public final static String EXTRA_DESCRIPTION = "com.example.DailyFeast.DESCRIPTION";


    public Piper piper = new Piper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todaysevents);

        piper = new Piper();
        ArrayList<Event> eventsArray = piper.retrieveListOfEvents();

        // create adapter instance that will help populate listView component with our eventsArray data
        EventsArrayAdapter adapter = new EventsArrayAdapter(TodaysEventsActivity.this, R.layout.list_view_event_row, eventsArray);

        //get the ListView component from the layout
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new OnEventClickListener());

    }


}