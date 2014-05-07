package com.example.DailyFeast;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.Calendar;



public class StartUpActivity extends Activity{

    // Alert Dialog
    private AlertDialog.Builder alertDialog;

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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

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

        if (!isNetworkAvailable()) {
            new AlertDialog.Builder(StartUpActivity.this).setMessage("Please turn on your internet").show();
        }

        if (day == 1 || day == 7){

            alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("WARNING!");
            alertDialog.setMessage("The Daily Piper does not publish on the weekends. You can still see and add free food events submitted by your fellow users.");
            alertDialog.setCancelable(false);
            alertDialog.setIcon(R.drawable.dailyfeastlogo);
            alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    dialog.cancel();
                }
            });

            // create alert dialog
            alertDialog.create();

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