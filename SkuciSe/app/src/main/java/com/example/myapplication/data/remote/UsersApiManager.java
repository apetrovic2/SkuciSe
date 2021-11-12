package com.example.myapplication.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UsersApiManager
{
    private static Retrofit retrofit = null;
    private static IUsersApi api = null;
    public static IUsersApi getUserApi()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/api/registration/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(IUsersApi.class);
        }
        return api;
    }
}
