package com.example.DailyFeast;

/**
 * Created by fabiolagutierrez on 2/21/14.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class TodayActivity extends Activity{

    /**
     * Called when the activity is first created.
     */
    public final static String EXTRA_TITLE = "com.example.DailyFeast.TITLE";
    public final static String EXTRA_LOCATION = "com.example.DailyFeast.LOCATION";
    public final static String EXTRA_DESCRIPTION = "com.example.DailyFeast.DESCRIPTION";

    private static String logtag = "DailyFeast"; //for use as the tag when logging the data extracted

    ArrayList<String[]> eventsArray = new ArrayList <String[]>();

    //The data to show
    List<Map<String, String>> eventsList = new ArrayList<Map<String,String>>();

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todaysevents);

        initList();

        // We get the ListView component from the layout
        ListView lv = (ListView) findViewById(R.id.listView);

        // This is a simple adapter that accepts as parameter
        // Context
        // Data list
        // The row layout that is used during the row creation
        // The keys used to retrieve the data
        // The View id used to show the data. The key number and the view id must match

        SimpleAdapter simpleAdpt = new SimpleAdapter(this, eventsList, android.R.layout.simple_list_item_1, new String[] {"event"}, new int[] {android.R.id.text1});
        lv.setAdapter(simpleAdpt);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id){
            Log.d(logtag, "onClick() called - an event was selected");
            TextView clickedView = (TextView) view;
            Toast.makeText(TodayActivity.this, "Item with id ["+id+"] - Position ["+position+"] - Event ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();
            //next activity
            goToEventDetail(eventsArray.get(position));
        }
    });
    }


    private void initList() {
        // here extract Daily Piper events
        //fake arrayList that stores all event data
        eventsArray.add( new String[]{"Afternoon Tea","Chapel","description AAAA"});
        eventsArray.add(new String[]{"Dinner","Ballroom","description BBBB."});
        eventsArray.add(new String[]{"Branch","Cafeteria","description CCCC"});
        eventsArray.add(new String[]{"Late lunch","Club","description DDDD"});
        eventsArray.add(new String[]{"Breakfast","Auditorium","description EEEE"});
        //fake list to show
        eventsList.add(createEvent("event", "Afternoon Tea"));
        eventsList.add(createEvent("event", "Dinner"));
        eventsList.add(createEvent("event", "Brunch"));
        eventsList.add(createEvent("event", "Late lunch"));
        eventsList.add(createEvent("event", "Breakfast"));
    }


    private void goToEventDetail( String[] eventData) {
        Intent intent = new Intent(this, SpecificActivity.class);
        intent.putExtra(EXTRA_TITLE, eventData[0]);
        intent.putExtra(EXTRA_LOCATION, eventData[1]);
        intent.putExtra(EXTRA_DESCRIPTION, eventData[2]);
        startActivity(intent);
    }

    private HashMap<String, String> createEvent(String key, String name) {
        HashMap<String, String> event = new HashMap<String, String>();
        event.put(key, name);
        return event;

    }

}