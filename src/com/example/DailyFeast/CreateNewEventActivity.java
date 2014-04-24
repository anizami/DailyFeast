package com.example.DailyFeast;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


    // Progress Dialog
    private ProgressDialog pDialog;

    ServerConnector serverConnector = new ServerConnector();
    EditText inputTitle;
    EditText inputTime;
    EditText inputLocation;
    EditText inputDescription;

    // url to create new product
    private static String url_create_event = "http://dailyfeast.herokuapp.com/addEvent";

    // JSON Node names
    private static Boolean success = true;

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
                // creating new event in background thread
                new CreateNewProduct().execute();
            }
        });
    }

    /**
     * Background Async Task to Create new event
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {

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
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String title = inputTitle.getText().toString();
            String time = inputTime.getText().toString();
            String location = inputLocation.getText().toString();
            String description = inputDescription.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("title", title));
            params.add(new BasicNameValuePair("time", time));
            params.add(new BasicNameValuePair("location", location));
            params.add(new BasicNameValuePair ("description", description));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONArray jArray = serverConnector.makeHttpRequest(url_create_event,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", jArray.toString());

            // check for success tag
            try {
                Boolean success2 = jArray.getBoolean(1);

                if (success2 == true) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(), TodaysEventsActivity.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
}