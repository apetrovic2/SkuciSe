package com.example.myapplication.data.remote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IAdApi
{
    @POST("api/ad/AddNewAd")
    @FormUrlEncoded
    Call<Integer> Registration(@Field("username") String username, @Field("password") String password, @Field("name") String name, @Field("email") String email);
}
