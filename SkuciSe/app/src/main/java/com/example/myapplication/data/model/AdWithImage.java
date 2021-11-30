package com.example.myapplication.data.model;

public class AdWithImage
{
    private final Ad ad;
    private final String image;

    public AdWithImage(Ad ad, String image) {
        this.ad = ad;
        this.image = image;
    }

    public Ad getAd() {
        return ad;
    }

    public String getImage() {
        return image;
    }
}
