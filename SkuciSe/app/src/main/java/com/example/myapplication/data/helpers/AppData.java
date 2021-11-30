package com.example.myapplication.data.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.data.repository.AdWithImageResponse;

import java.util.List;

public class AppData
{
    private static Context _context;
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private static final String PREF_NAME = "_store";
    private int PRIVATE_MODE = 0;

    private static List<AdWithImageResponse> homePageAds = null;
    private static List<AdWithImageResponse> sellAds = null;
    private static List<AdWithImageResponse> rentAds = null;

    public AppData(Context _context)
    {
        if(_context == null || pref == null || editor == null)
        {
            this._context = _context;
            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
        }
    }
    public AppData()
    {

    }
    public static void setToken(Integer token)
    {
        editor.putInt("Token", token);
        editor.commit();
    }

    public static Integer getToken()
    {
        if(pref != null)
        {
            return pref.getInt("Token", 0);
        }
        return 0;
    }

    public static List<AdWithImageResponse> getHomePageAds() {
        return homePageAds;
    }

    public static void setHomePageAds(List<AdWithImageResponse> homePageAds) {
        AppData.homePageAds = homePageAds;
    }

    public static List<AdWithImageResponse> getSellAds() {
        return sellAds;
    }

    public static void setSellAds(List<AdWithImageResponse> sellAds) {
        AppData.sellAds = sellAds;
    }

    public static List<AdWithImageResponse> getRentAds() {
        return rentAds;
    }

    public static void setRentAds(List<AdWithImageResponse> rentAds) {
        AppData.rentAds = rentAds;
    }
}
