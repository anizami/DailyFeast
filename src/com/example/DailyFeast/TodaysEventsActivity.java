package com.example.DailyFeast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.*;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint("NewApi")

public class TodaysEventsActivity extends Activity {

    public final static String EXTRA_TITLE = "com.example.DailyFeast.TITLE";
    public final static String EXTRA_TIME = "com.example.DailyFeast.TIME";
    public final static String EXTRA_LOCATION = "com.example.DailyFeast.LOCATION";
    public final static String EXTRA_DESCRIPTION = "com.example.DailyFeast.DESCRIPTION";

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating ServerConnector object
    ServerConnector serverConnector = new ServerConnector();

    // public Piper piper = new Piper();

    // url to get all events list
    private static String urlGetEvents = "http://10.0.2.2:5000/getPiper";

    //ArrayList<Event> eventsArray = piper.retrieveListOfEvents();
    private ArrayList<Event> eventsArray = new ArrayList <Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todaysevents);

        //piper = new Piper();


        // Loading products in Background Thread
        new LoadAllEvents().execute();


    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     */
    //                         AsyncTask<Params, Progress, Result>
    class LoadAllEvents extends AsyncTask<String, String, String> {

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
            JSONObject json =  serverConnector.makeHttpRequest(urlGetEvents, "GET", params);
            String jsonString = json.toString();
            // Check your log cat for JSON response
            Log.d("All Events: ", jsonString);
            Event testEvent = new Event();
            testEvent.setLocation("");
            testEvent.setTime("");
            testEvent.setDescription("No event found");
            testEvent.setTitle("Parsing error");

            try{
                //create a jsonElement (com.google.gson) using the jsonString (we do that so it's easy to get it as an array that we can iterate
                JsonElement jsonElement = new JsonParser().parse(jsonString);
                //now get the the jsonElement as an array
                JsonArray jsonArray = jsonElement.getAsJsonArray();

                //now iterate through this array to extract the json representation of each individual event
                Iterator iterator = jsonArray.iterator();
                while(iterator.hasNext()){
                    JsonElement jsonEvent = (JsonElement)iterator.next();
                    try {
                        JsonObject jsonEvent2 = jsonEvent.getAsJsonObject();
                        String title = jsonEvent2.get("title").getAsString();
                        String location = jsonEvent2.get("location").getAsString();
                        String description = jsonEvent2.get("description").getAsString();
                        String time = jsonEvent2.get("time").getAsString();
                        Event newEvent = new Event();
                        newEvent.setDescription(description);
                        newEvent.setTime(time);
                        newEvent.setLocation(location);
                        newEvent.setTitle(title);
                        eventsArray.add(newEvent);
                    }

                    catch (Exception e){
                        eventsArray.add(testEvent);
                    }

                    //                Event event = new Gson().fromJson(jsonEvent, Event.class);
                    //               eventsArray.add(event);
                    System.out.println("Adding event to list");

                }
            } catch (JsonParseException e){
                eventsArray.add(testEvent);
            } catch (IllegalStateException e){
                eventsArray.add(testEvent);
            }
            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String result) {
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