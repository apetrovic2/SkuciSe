package com.example.myapplication.data.remote;

import com.example.myapplication.data.model.Ad;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IAdApi
{
    @POST("AddNewAd")
    @FormUrlEncoded
    Call<Integer> addNewAd(@Field("title") String title, @Field("flat_house") Integer flat_house, @Field("sell_rent") Integer sell_rent, @Field("description") String description, @Field("size") Double size, @Field("date_start") String date_start, @Field("price") Double price, @Field("location") String location, @Field("floor") Integer floor, @Field("internet") Integer internet, @Field("ac") Integer ac, @Field("intercom") Integer intercom, @Field("garage") Integer garage, @Field("elevator") Integer elevator, @Field("balcony") Integer balcony, @Field("yard") Integer yard, @Field("heating") Integer heating, @Field("tv") Integer tv);

    @GET("GetAllAds")
    Call<List<Ad>> getAllAds(@Query("category") Integer category);

}
