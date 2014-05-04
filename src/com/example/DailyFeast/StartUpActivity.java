package com.example.DailyFeast;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.Calendar;



public class StartUpActivity extends Activity{


    //Create an anonymous implementation of OnClickListener
    private View.OnClickListener startListener = new OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.buttonStart:
                    gotoTodaysEvents();
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

        Button buttonCreateEvents = (Button)findViewById(R.id.buttonCreateEvents);
        buttonCreateEvents.setOnClickListener(startListener);

        // The daily piper is not published on the weekend. Check to see if it's the weekend.
        Calendar calendar = Calendar.getInstance();
        // Sunday = 1, Saturday = 7, etc.
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        if (day == 1 || day == 7){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("WARNING!");
            alertDialogBuilder.setMessage("The Daily Piper does not publish on the weekends. You can still see and add free food events submitted by your fellow users.");
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    dialog.cancel();
                }
            });

            // create alert dialog
           AlertDialog alertDialog =  alertDialogBuilder.create();

           // show it
           alertDialog.show();

    }
    }


    /** Called when the user clicks the startButton */
    public void gotoTodaysEvents() {
        Intent intent = new Intent(this, TodaysEventsActivity.class);
        startActivity(intent);
    }


    public void createEvents () {
        Intent intent = new Intent(this, CreateNewEventActivity.class);
        startActivity(intent);
    }

}