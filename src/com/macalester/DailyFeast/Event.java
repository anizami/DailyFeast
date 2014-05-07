package com.macalester.DailyFeast;

import java.sql.Time;

public class Event {
    private long id;
    private String title;
    private String location;
    private String description;
    private String time;
    private Time createdAt, updatedAt;




   // public Event(long id, String title, String time, String location, String description) {
   //     this.id = id;
   //     this.title = title;
   //     this.location = location;
   //     this.description = description;
    //    this.time = time;
    //}


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

    public Time getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Time createdAt) {
        this.createdAt = createdAt;
    }

    public Time getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Time updatedAt) {
        this.updatedAt = updatedAt;

    }
}