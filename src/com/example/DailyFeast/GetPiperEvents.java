package com.example.DailyFeast;

import android.app.Activity;

/**
 * Created by Asra Nizami on 3/18/14.
 */


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetPiperEvents extends ListActivity {
    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating ServerConnector object
    ServerConnector serverConnector = new ServerConnector();

    ArrayList<String> eventsList;

    // url to get all products list
    private static String urlGetEvents = "http://10.0.2.2:4567/";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piperevents);

        // Hashmap for ListView
        eventsList = new ArrayList<String>();

        // Loading products in Background Thread
        new LoadAllEvents().execute();

    }


    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllEvents extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(GetPiperEvents.this);
            pDialog.setMessage("Loading events. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All events from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = serverConnector.makeHttpRequest(urlGetEvents, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Events: ", json.toString());
            // Create an iterator that goes through the json object
            // and then adds all the event strings to the map
            Iterator<String> keys = json.keys();
            for (int i = 0; i < 2; i++) {
                String key = keys.next();
                String val = "";
                try {
                    val = json.get(key).toString();
                }
                catch (JSONException e){
                    val = "No event found";
                }
                eventsList.add(val);
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new ArrayAdapter<String>(
                            GetPiperEvents.this,
                            R.layout.list_item, R.id.eventstring,
                            eventsList);
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }




}
