package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.data.remote.UsersApiManager;

public class MainApplication extends Application
{
    public static UsersApiManager usersApiManager;
    @Override
    public void onCreate() {
        super.onCreate();
        usersApiManager = UsersApiManager.getInstance();
    }
}
