package com.example.myapplication.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdApiManager
{
    private static Retrofit retrofit = null;
    private static IAdApi api = null;
    public static IAdApi getAdApi()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/api/ad/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(IAdApi.class);
        }
        return api;
    }
}
