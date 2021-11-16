package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.data.helpers.AppData;

public class MainApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        AppData appData = new AppData(getApplicationContext());
    }
}
