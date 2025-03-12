package com.mini.fieldServices;

public class logHelperCLass {

    String name,time,date,device,details,slotNo;

    public logHelperCLass(String name, String time, String date, String device, String details) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.device = device;
        this.details = details;
    }

    public logHelperCLass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
