package com.example.DailyFeast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Piper extends Activity {

    //arrayList to hold events extracted from Daily Piper
    ArrayList<Event> eventsArray = new ArrayList <Event>();

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todaysevents);
        //populate the list of events from Daily Piper
        retrieveListOfEvents();

        //create adapter instance
        ArrayAdapterEvent adapter = new ArrayAdapterEvent(this, R.layout.list_view_row_event, eventsArray);
        /* get the ListView component from the layout */
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnEventClickListener());

    }
    public void retrieveListOfEvents(){
        eventsArray.add(new Event("GEOCLUB WEEKLY SEMINAR SERIES: KATHERINE FORNASH","noon", "Olin-Rice 175","Please join us for a interactive and fun talk. Pizza will be served."));
        eventsArray.add(new Event("ASH WEDNESDAY SERVICE","noon", "Weyerhaeuser Memorial Chapel","Ashes will be distributed."));
        eventsArray.add(new Event("WELLNESS WEDNESDAY HULA HOOPING","noon", "Wellness Lounge","Hula hoops and a light lunch will be provided."));
        eventsArray.add(new Event("BIO TEA","5pm","Olin-Rice 256", "Drink some tea"));
        eventsArray.add(new Event("CALLING ALL SENIORS: COUNTDOWN TO COMMENCEMENT IS THIS WEDNESDAY","5 to 6:30pm", "Kagin Commons, Alexander G. Hill Ballroom","Food served. Includes our LinkedIn photo booth."));
        eventsArray.add(new Event("WOMEN'S HISTORY MONTH SOUP & SUBSTANCE –THIS THURSDAY","11:45 a.m. to 1 p.m.","Kagin Commons (lower level)","Join us for soup and conversation."));
    }
}

