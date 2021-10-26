package com.example.myapplication;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Helper
{
    OkHttpClient client = new OkHttpClient();
    Call run(String url, Callback callback) throws IOException
    {
        Request request = new Request.Builder()
                .url(url)
                .build();
//        try(Response response = client.newCall(request).execute())
//        {
//            return response.body().string();
//        }
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
}
