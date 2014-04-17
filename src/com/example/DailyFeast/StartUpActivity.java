package com.example.DailyFeast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class StartUpActivity extends Activity{


    //Create an anonymous implementation of OnClickListener
    private View.OnClickListener startListener = new OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.buttonStart:
                    gotoTodaysEvents();
                    break;
                case R.id.buttonSettings:
                    gotoSettings();
                    break;
                case R.id.buttonCreateEvents:
                    createEvents();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        Button buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(startListener);
        Button buttonSettings = (Button)findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(startListener);

    }


    /** Called when the user clicks the startButton */
    public void gotoTodaysEvents() {
        Intent intent = new Intent(this, TodaysEventsActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the settingsButton */
    public void gotoSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void createEvents () {
        Intent intent = new Intent(this, CreateNewEventActivity.class);
        startActivity(intent);
    }

}