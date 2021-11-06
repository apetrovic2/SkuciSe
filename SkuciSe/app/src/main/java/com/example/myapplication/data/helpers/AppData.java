package com.example.myapplication.data.helpers;

public class AppData
{
    private static int userID;

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        AppData.userID = userID;
    }
}
