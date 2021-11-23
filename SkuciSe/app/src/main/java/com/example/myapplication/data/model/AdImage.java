package com.example.myapplication.data.model;

public class AdImage
{
    private final int id;
    private final int ad_id;
    private final String image;

    public AdImage(int id, int ad_id, String image) {
        this.id = id;
        this.ad_id = ad_id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getAd_id() {
        return ad_id;
    }

    public String getImage() {
        return image;
    }
}
