package com.example.myapplication.data.repository;

public class AppointmentResponse
{
    private int id;
    private int user_id;
    private int ad_id;
    private int approved;
    private String date;
    private AdResponse ad;
    private UsersResponse user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AdResponse getAd() {
        return ad;
    }

    public void setAd(AdResponse ad) {
        this.ad = ad;
    }

    public UsersResponse getUser() {
        return user;
    }

    public void setUser(UsersResponse user) {
        this.user = user;
    }
}
