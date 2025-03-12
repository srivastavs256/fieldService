package com.mini.fieldServices;

public class FeedDataHelperClass {
    String name;

    String date,details;

    public FeedDataHelperClass(String name, String date, String details) {
        this.name = name;
        this.date = date;
        this.details = details;
    }

    public FeedDataHelperClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
