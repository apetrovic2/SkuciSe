package com.example.myapplication.data.remote;

import com.example.myapplication.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class UsersApiManager
{
    private static IUsersApi service;
    private static UsersApiManager apiManager;

    private UsersApiManager()
    {
        service = RetrofitService.Create();
    }

    public static UsersApiManager getInstance()
    {
        if(apiManager == null)
        {
            apiManager = new UsersApiManager();
        }
        return apiManager;
    }

    public void getUsers(Callback<List<User>> callback)
    {
        Call<List<User>> usersCall = service.getUsers();
        usersCall.enqueue(callback);
    }
}
