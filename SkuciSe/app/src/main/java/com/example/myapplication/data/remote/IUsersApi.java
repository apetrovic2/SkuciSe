package com.example.myapplication.data.remote;

import com.example.myapplication.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IUsersApi
{
    @GET("registration")
    Call<List<User>> getUsers();
}
