package com.example.myapplication.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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
            OkHttpClient okHttp = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5000/api/ad/")
                    .client(okHttp)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(IAdApi.class);
        }
        return api;
    }
}
