package com.macalester.DailyFeast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * Created by fabiolagutierrez on 3/28/14.
 */

public class SpecificEventActivity extends Activity {

    private OnClickListener startListener = new OnClickListener() {
        public void onClick (View v){
            //close this activity
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specificevent);


        // Get the message from the intent
        Intent intent = getIntent();
        String eventTitle = intent.getStringExtra(TodaysEventsActivity.EXTRA_TITLE);
        String eventLocation = intent.getStringExtra(TodaysEventsActivity.EXTRA_LOCATION);
        String eventDescription = intent.getStringExtra(TodaysEventsActivity.EXTRA_DESCRIPTION);
        String eventTime= intent.getStringExtra(TodaysEventsActivity.EXTRA_TIME);

        //check if some information is missing
        if (eventLocation == null){
            eventLocation = "Unable to find information";
        }
        if (eventDescription == null || eventDescription.equals("")){
            eventDescription = "Unable to find information";
        }
        if (eventTime == null){
            eventTime = "Unable to find information";
        }

        //Get the textView EventTitle

        TextView tvTitle = (TextView) findViewById(R.id.EventTitle);
        tvTitle.setText(eventTitle);
        //Get the textView EventLocation
        TextView tvLocation = (TextView) findViewById(R.id.EventLocation);
        tvLocation.setText(eventLocation);
        //Get the textView EventDescription
        TextView tvDescription = (TextView) findViewById(R.id.EventDescription);
        tvDescription.setText(eventDescription);
        //Get the textView EventTime
        TextView tvTime = (TextView) findViewById(R.id.EventTime);
        tvTime.setText(eventTime);

    }

    //taken from: http://www.androidhive.info/2013/11/android-working-with-action-bar/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.home_menu:
                goHomeScreen();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goHomeScreen(){
        Intent i = new Intent(this, StartUpActivity.class);
        startActivity(i);
    }
}



