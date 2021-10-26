package com.example.myapplication.data.remote;

import com.example.myapplication.data.repository.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface IUsersApi
{
    @GET("api/registration")
    Call<List<UsersResponse>> getUsers();
}
