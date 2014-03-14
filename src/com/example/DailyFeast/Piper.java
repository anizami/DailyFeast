package com.example.DailyFeast;

import java.util.ArrayList;

/**
 * Created by fabiolagutierrez on 2/27/14.
 */
public class Piper {
    //arrayList ot hold of the events extracted from the Daily Piper newsletter
    private ArrayList<Event> eventsArray = new ArrayList <Event>();


    public ArrayList<Event> retrieveListOfEvents(){
        eventsArray.add(new Event("GEOCLUB WEEKLY SEMINAR SERIES: KATHERINE FORNASH","noon", "Olin-Rice 175","Please join us for a interactive and fun talk. Pizza will be served."));
        eventsArray.add(new Event("ASH WEDNESDAY SERVICE","noon", "Weyerhaeuser Memorial Chapel","Ashes will be distributed."));
        eventsArray.add(new Event("WELLNESS WEDNESDAY HULA HOOPING","noon", "Wellness Lounge","Hula hoops and a light lunch will be provided."));
        eventsArray.add(new Event("BIO TEA","5pm","Olin-Rice 256", "Drink some tea"));
        eventsArray.add(new Event("CALLING ALL SENIORS: COUNTDOWN TO COMMENCEMENT","5 to 6:30pm", "Kagin Commons, Alexander G. Hill Ballroom","Food served. Includes our LinkedIn photo booth."));
        eventsArray.add(new Event("WOMEN'S HISTORY MONTH SOUP & SUBSTANCE","11:45 a.m. to 1 p.m.","Kagin Commons (lower level)","Join us for soup and conversation."));
        eventsArray.add( new Event("Afternoon Tea","11:45 a.m. to 1 p.m.","Chapel","description for Afternoon Tea"));
        return eventsArray;
    }
}