package com.example.myapplication.data.repository;

import com.example.myapplication.data.model.Ad;

public class AdWithImageResponse
{
    private Ad ad;
    private String image;

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
