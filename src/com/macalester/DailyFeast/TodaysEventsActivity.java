package com.macalester.DailyFeast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.ListView;

import com.google.gson.*;
import org.apache.http.NameValuePair;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fabiolagutierrez on 3/28/14.
 */


@SuppressLint("NewApi")

public class TodaysEventsActivity extends Activity {

    public final static String EXTRA_TITLE = "com.macalester.DailyFeast.TITLE";
    public final static String EXTRA_TIME = "com.macalester.DailyFeast.TIME";
    public final static String EXTRA_LOCATION = "com.macalester.DailyFeast.LOCATION";
    public final static String EXTRA_DESCRIPTION = "com.macalester.DailyFeast.DESCRIPTION";

    // Progress Dialog
    private ProgressDialog pDialog;

    // Alert Dialog
    private AlertDialog.Builder alertDialog;


    // Creating ServerConnector object
    ServerConnector serverConnector = new ServerConnector();

    // url to get all events list
//    private static String urlGetEvents = "http://dailyfeast.herokuapp.com/getPiper";
    private static String urlGetEvents = "http://pure-lake-3835.herokuapp.com/getPiper";

    //ArrayList<Event> eventsArray = piper.retrieveListOfEvents();
    private ArrayList<Event> eventsArray = new ArrayList <Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todaysevents);
        // Loading products in Background Thread
        new LoadAllEvents().execute();

    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     */
    //                         AsyncTask<Params, Progress, Result>
    class LoadAllEvents extends AsyncTask<String, Void, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TodaysEventsActivity.this);
            pDialog.setMessage("Loading events. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting all events from url
         */
        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting   JSON object from URL
            JSONArray jArray =  serverConnector.makeHttpRequest(urlGetEvents, "GET", params);
            if (jArray.length() == 0){
                return "EMPTY";
            }
            String jsonString = jArray.toString();
            // Check your log cat for JSON response
            Log.d("All Events: ", jsonString);

            try{
                //create a jsonElement (com.google.gson) using the jsonString (we do that so it's easy to get it as an array that we can iterate
                JsonElement jsonElement = new JsonParser().parse(jsonString);
                //now get the the jsonElement as an array
                JsonArray jsonArray = jsonElement.getAsJsonArray();

                //now iterate through this array to extract the json representation of each individual event
                Iterator iterator = jsonArray.iterator();
                while(iterator.hasNext()){
                    JsonElement jsonEvent = (JsonElement)iterator.next();
                    Event event = new Gson().fromJson(jsonEvent, Event.class);
                    eventsArray.add(event);

                }
            } catch (JsonParseException e){
                eventsArray.add(new Event());
            } catch (IllegalStateException e){
                eventsArray.add(new Event());
            }
            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String result) {
            if (result == "EMPTY"){
                pDialog.setMessage("No free food events found.");
                pDialog.dismiss();
                alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(TodaysEventsActivity.this, R.style.AlertDialogCustom));
                alertDialog.setMessage("No free food events found.");
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
            else{
                // dismiss the dialog after getting all events
                pDialog.dismiss();

            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {

                    /**
                     * Updating parsed JSON data into ListView
                     * */

                    // create adapter instance that will help populate listView component with our eventsArray data
                    EventsArrayAdapter adapter = new EventsArrayAdapter(TodaysEventsActivity.this, R.layout.list_view_event_row, eventsArray);

                    //get the ListView component from the layout
                    ListView lv = (ListView) findViewById(R.id.listView);
                    lv.setAdapter(adapter);

                    //add a listener to the items in the listView component (to show the event detailed information when user clicks on it)
                    lv.setOnItemClickListener(new OnEventClickListener());

                }


            });
        }
    }
}
}
