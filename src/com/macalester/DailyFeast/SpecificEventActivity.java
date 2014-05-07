package com.macalester.DailyFeast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


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
}



