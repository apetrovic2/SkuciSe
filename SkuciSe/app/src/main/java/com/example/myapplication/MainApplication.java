package com.example.myapplication;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.data.remote.UsersApiManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainApplication extends Application
{
    public static UsersApiManager usersApiManager;
    //public final String[] responseStr = new String[1];
    @Override
    public void onCreate() {
        super.onCreate();
        //usersApiManager = UsersApiManager.getInstance();
        String url = "http://10.0.2.2:5000/api/registration";
        Helper helper = new Helper();
        try {
            helper.run(url, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    //e.printStackTrace();
                    Log.i("USER:::::::::::::::::::" , " ");
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                   String responseStr = response.body().string();
                   Log.i("USER:::::::::::::::::::" , " " + responseStr);

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
