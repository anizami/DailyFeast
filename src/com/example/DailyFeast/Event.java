package com.example.DailyFeast;

/**
 * Created by fabiolagutierrez on 3/3/14.
 */
public class Event {
    private String title;
    private String location;
    private String description;

    public Event(String title, String location, String description) {
        this.title = title;
        this.location = location;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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