package com.example.myapplication.data.remote;

import com.example.myapplication.data.repository.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IUsersApi
{
    @GET("GetAllUsers")
    Call<List<UsersResponse>> getUsers();
    
    @POST("Login")
    @FormUrlEncoded
    Call<Integer> checkLogin(@Field("username") String username, @Field("password") String password);

    @POST("GetUserById")
    @FormUrlEncoded
    Call<UsersResponse> getUserById(@Field("id") Integer id);

    @POST("Register")
    @FormUrlEncoded
    Call<Integer> Registration(@Field("username") String username, @Field("password") String password, @Field("name") String name, @Field("email") String email);

    @PUT("EditUser")
    @FormUrlEncoded
    Call<Integer> EditUser(@Field("id") Integer id, @Field("username") String username, @Field("password") String password, @Field("name") String name, @Field("email") String email);
}
