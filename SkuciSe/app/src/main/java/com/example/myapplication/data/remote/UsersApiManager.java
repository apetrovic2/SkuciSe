package com.example.myapplication.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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
            OkHttpClient okHttp = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/api/registration/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttp)
                    .build();
            api = retrofit.create(IUsersApi.class);
        }
        return api;
    }
}
