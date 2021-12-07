package com.example.myapplication.data.repository;

public class AppointmentInfoResponse
{
    public Integer id;
    public UsersResponse user;
    public int approved;
    public String date;
    public int ad_id;
    public  String title;
    public String owner_image;

    public UsersResponse getUser() {
        return user;
    }

    public void setUser(UsersResponse user) {
        this.user = user;
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

    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner_image() {
        return owner_image;
    }

    public void setOwner_image(String owner_image) {
        this.owner_image = owner_image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
