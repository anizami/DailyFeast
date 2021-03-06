package com.macalester.DailyFeast;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by fabiolagutierrez on 3/28/14.
 */
public class CreateNewEventActivity extends Activity {

    // Alert Dialog
    private AlertDialog.Builder alertDialog;
    // Progress Dialog
    private ProgressDialog pDialog;

    private ServerConnector serverConnector = new ServerConnector();
    private EditText inputTitle;
    private EditText inputTime;
    private EditText inputLocation;
    private EditText inputDescription;

    private static String URL_CREATE_EVENT = "http://thedailyfeast.herokuapp.com/addEvent";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        // Edit Text
        inputTitle = (EditText) findViewById(R.id.inputTitle);
        inputTime = (EditText) findViewById(R.id.inputTime);
        inputLocation = (EditText) findViewById(R.id.inputLocation);
        inputDescription = (EditText)  findViewById(R.id.inputDescription);

        // Create button
        Button btnCreateEvent = (Button) findViewById(R.id.btnCreateEvent);

        // button click event
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String title = inputTitle.getText().toString();
                String time = inputTime.getText().toString();
                String location = inputLocation.getText().toString();

                if (title.equals("")  || time.equals("")  || location.equals("")){
                    alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(CreateNewEventActivity.this, R.style.AlertDialogCustom));
                    alertDialog.setMessage("Please fill out the title, time, and location fields.");
                    alertDialog.setCancelable(false);
                    alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener(){
                        public void onClick(final DialogInterface dialog, int id){
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    // dismiss the dialog once done
                                    dialog.cancel();
                                }}, 3000);
                        }});
                    alertDialog.create();
                    alertDialog.show();
                }
                else {
                    // creating new event in background thread
                    new CreateNewEvent().execute();
                }
            }
        });
    }

    /**
     * Background Async Task to Create new event
     * */
    class CreateNewEvent extends AsyncTask<String, String, String> {

        // Variable to check if event was successfully created
        Boolean success2 = true;

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CreateNewEventActivity.this);
            pDialog.setMessage("Creating Event...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating event
         * */
        protected String doInBackground(String... args) {

            String title = inputTitle.getText().toString();
            String time = inputTime.getText().toString();
            String location = inputLocation.getText().toString();
            String description = inputDescription.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("title", title));
            params.add(new BasicNameValuePair("time", time));
            params.add(new BasicNameValuePair("location", location));
            params.add(new BasicNameValuePair ("description", description));

            JSONArray jArray = serverConnector.makeHttpRequest(URL_CREATE_EVENT,
                    "POST", params);

            // check log cat for response
            Log.d("Create Response", jArray.toString());

            // check for success tag in response json
            try {
                success2 = jArray.getBoolean(1);

                if (success2) { //successfully created event
                    Intent i = new Intent(getApplicationContext(), TodaysEventsActivity.class);
                    startActivity(i);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String result) {
            if (success2){
            pDialog.setMessage("Your event was successfully added!");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    pDialog.dismiss();
                }}, 3000);
            Intent intent = new Intent(getApplicationContext(), TodaysEventsActivity.class);
            startActivity(intent);
            }
           else {
                pDialog.setMessage("Unable to create event.");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        pDialog.dismiss();
                    }}, 3000);
            }
        }
    }
}
