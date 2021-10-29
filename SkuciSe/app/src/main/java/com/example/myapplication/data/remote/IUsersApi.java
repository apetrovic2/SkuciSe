package com.example.myapplication.data.remote;

import com.example.myapplication.data.repository.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IUsersApi
{
    @GET("api/registration/GetAllUsers")
    Call<List<UsersResponse>> getUsers();

    @FormUrlEncoded
    @POST("api/registration/Login")
    Call<Integer> checkLogin(@Field("username") String username, @Field("password") String password);
}
