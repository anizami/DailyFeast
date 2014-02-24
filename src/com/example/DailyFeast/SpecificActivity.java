package com.example.DailyFeast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Created by fabiolagutierrez on 2/22/14.
 */
public class SpecificActivity extends Activity {

    private static String logtag = "DailyFeast"; //for use as the tag when logging

    //Create an anonymous implementation of OnClickListener
    private OnClickListener startListener = new OnClickListener() {
        public void onClick (View v){
            Log.d(logtag, "onClick() called - back button");
            Toast.makeText(SpecificActivity.this, "The Back button was clicked.",Toast.LENGTH_LONG).show();
            Log.d(logtag, "onClick() ended - back button");
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specificevent);

        //Get the message from the intent
        Intent intent = getIntent();
        String EventTitle = intent.getStringExtra(TodayActivity.EXTRA_TITLE);
        String EventLocation = intent.getStringExtra(TodayActivity.EXTRA_LOCATION);
        String EventDescription = intent.getStringExtra(TodayActivity.EXTRA_DESCRIPTION);

        //Get the textView EventTitle
        TextView tvTitle = (TextView) findViewById(R.id.EventTitle);
        tvTitle.setText(EventTitle);
        //Get the textView EventLocation
        TextView tvLocation = (TextView) findViewById(R.id.EventLocation);
        tvLocation.setText(EventLocation);
        //Get the textView EventDescription
        TextView tvDescription = (TextView) findViewById(R.id.EventDescription);
        tvDescription.setText(EventDescription);

        Button backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(startListener);
    }





}
