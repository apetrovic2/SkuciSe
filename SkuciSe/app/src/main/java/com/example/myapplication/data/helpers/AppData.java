package com.example.myapplication.data.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class AppData
{
    private static Context _context;
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private static final String PREF_NAME = "_store";
    private int PRIVATE_MODE = 0;

    public AppData(Context _context)
    {
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
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
        return pref.getInt("Token", 0);
    }
}
