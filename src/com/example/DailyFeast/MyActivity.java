package com.example.DailyFeast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MyActivity extends Activity{
    /**
     * Called when the activity is first created.
     */
    public final static String EXTRA_MESSAGE = "com.example.DailyFeast.MESSAGE";

    private static String logtag = "DailyFeast"; //for use as the tag when logging

    //Create an anonymous implementation of OnClickListener
    private OnClickListener startListener = new OnClickListener() {
        public void onClick (View v){

            Log.d(logtag, "onClick() called - start button");
            Toast.makeText(MyActivity.this, "The Start button was clicked.",Toast.LENGTH_LONG).show();
            Log.d(logtag, "onClick() ended - start button");
            goToListofEvents (v);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(startListener); // Register the onClick listener with the implementation above

    }

    /** Called when the user clicks the Send button */
    public void goToListofEvents (View view){
        Intent intent = new Intent(this, TodayActivity.class);
        startActivity(intent);
}
}

