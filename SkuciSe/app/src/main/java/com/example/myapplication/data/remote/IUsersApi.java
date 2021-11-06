package com.example.myapplication.data.remote;

import com.example.myapplication.data.repository.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IUsersApi
{
    @GET("api/registration/GetAllUsers")
    Call<List<UsersResponse>> getUsers();
    
    @POST("api/registration/Login")
    @FormUrlEncoded
    Call<Integer> checkLogin(@Field("username") String username, @Field("password") String password);

    @POST("api/registration/GetUserById")
    @FormUrlEncoded
    Call<UsersResponse> getUserById(@Field("id") Integer id);

    @POST("api/registration/Register")
    @FormUrlEncoded
    Call<Integer> Registration(@Field("username") String username, @Field("password") String password, @Field("name") String name, @Field("email") String email);
}
