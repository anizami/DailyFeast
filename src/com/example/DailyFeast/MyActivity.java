package com.example.DailyFeast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MyActivity extends Activity{


    private OnClickListener startListener = new OnClickListener() {
        public void onClick (View v){
            startPiper();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        Button buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(startListener);

    }

    /** Called when the user clicks the startButton */
    public void startPiper () {
        Intent intent = new Intent(this, Piper.class);
        startActivity(intent);
}
}

