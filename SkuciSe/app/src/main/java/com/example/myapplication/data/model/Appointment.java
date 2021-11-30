package com.example.myapplication.data.model;

public class Appointment
{
    private final int id;
    private final int user_id;
    private final int ad_id;
    private final int approved;
    private final String date;

    public Appointment(int id, int user_id, int ad_id, int approved, String date) {
        this.id = id;
        this.user_id = user_id;
        this.ad_id = ad_id;
        this.approved = approved;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getAd_id() {
        return ad_id;
    }

    public int getApproved() {
        return approved;
    }

    public String getDate() {
        return date;
    }
}
