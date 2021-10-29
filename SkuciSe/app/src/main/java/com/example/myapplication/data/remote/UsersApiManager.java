package com.example.myapplication.data.remote;

import com.example.myapplication.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UsersApiManager
{

    public static IUsersApi getClient()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IUsersApi api = retrofit.create(IUsersApi.class);
        return api;
    }
}
