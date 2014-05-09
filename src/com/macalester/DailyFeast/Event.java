package com.macalester.DailyFeast;

/**
 * Created by fabiolagutierrez on 3/1/14.
 */

public class Event {
    private long id;
    private String title;
    private String location;
    private String description;
    private String time;


    public long getId() {
        return id;
    }

    public void setId (long id) {this.id = id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}