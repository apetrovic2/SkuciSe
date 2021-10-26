package com.example.myapplication.data.remote;

import com.example.myapplication.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UsersApiManager
{
//    private static IUsersApi service;
//    private static UsersApiManager apiManager;
//
//    private UsersApiManager()
//    {
//        service = RetrofitService.Create();
//    }
//
//    public static UsersApiManager getInstance()
//    {
//        if(apiManager == null)
//        {
//            apiManager = new UsersApiManager();
//        }
//        return apiManager;
//    }
//
////    public void getUsers(Callback<List<User>> callback)
////    {
////        //Call<List<User>> usersCall = service.getUsers();
////        //usersCall.enqueue(callback);
////    }

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
