package com.example.DailyFeast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Piper extends Activity {

    AlertDialog alertDialogStores;

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
        lv.setOnItemClickListener(new OnItemClickListenerListViewEvent());

    }
    public void retrieveListOfEvents(){
        eventsArray.add(new Event("Afternoon Tea","Chapel","description for Afternoon Tea"));
        eventsArray.add(new Event("Dinner","Ballroom","description for Dinner."));
        eventsArray.add(new Event("Brunch","Cafeteria","description for Brunch"));
        eventsArray.add(new Event("Late lunch","Club","description for Late Lunch"));
        eventsArray.add(new Event("Breakfast","Auditorium","description for Breakfast"));
        eventsArray.add(new Event("Afternoon Tea 2","Chapel","description for Afternoon Tea"));
        eventsArray.add(new Event("Dinner 2","Ballroom","description for Dinner."));
        eventsArray.add(new Event("Brunch 2","Cafeteria","description for Brunch"));
        eventsArray.add(new Event("Late lunch 2","Club","description for Late Lunch"));
        eventsArray.add(new Event("Breakfast 2","Auditorium","description for Breakfast"));
        eventsArray.add(new Event("Afternoon Tea 3","Chapel","description for Afternoon Tea"));
        eventsArray.add(new Event("Dinner 3","Ballroom","description for Dinner."));
        eventsArray.add(new Event("Brunch 3","Cafeteria","description for Brunch"));
        eventsArray.add(new Event("Late lunch 3","Club","description for Late Lunch"));
        eventsArray.add(new Event("Breakfast 3","Auditorium","description for Breakfast"));
    }
}

