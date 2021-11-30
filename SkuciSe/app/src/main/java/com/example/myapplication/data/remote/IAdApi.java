package com.example.myapplication.data.remote;

import com.example.myapplication.data.model.Ad;
import com.example.myapplication.data.model.AdWithImage;
import com.example.myapplication.data.repository.AdImageResponse;
import com.example.myapplication.data.repository.AdResponse;
import com.example.myapplication.data.repository.AdWithImageResponse;
import com.example.myapplication.data.repository.AppointmentResponse;
import com.example.myapplication.data.repository.UserImageResponse;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IAdApi
{
    @POST("AddNewAd")
    @FormUrlEncoded
    Call<Integer> addNewAd(@Field("title") String title, @Field("flat_house") Integer flat_house, @Field("sell_rent") Integer sell_rent, @Field("description") String description, @Field("size") Double size, @Field("date_start") String date_start, @Field("price") Double price, @Field("location") String location, @Field("floor") Integer floor, @Field("internet") Integer internet, @Field("ac") Integer ac, @Field("intercom") Integer intercom, @Field("garage") Integer garage, @Field("elevator") Integer elevator, @Field("balcony") Integer balcony, @Field("yard") Integer yard, @Field("heating") Integer heating, @Field("tv") Integer tv, @Field("user_id") Integer user_id);

    @GET("GetAllAds")
    Call<List<AdWithImageResponse>> getAllAds(@Query("category") Integer category);

    @GET("GetAdById")
    Call<AdWithImageResponse> getAdById(@Query("id") Integer id);

    @PUT("EditAd")
    @FormUrlEncoded
    Call<Integer> editAd(@Field("id") Integer id, @Field("title") String title, @Field("flat_house") Integer flat_house, @Field("sell_rent") Integer sell_rent, @Field("description") String description, @Field("size") Double size, @Field("date_start") String date_start, @Field("price") Double price, @Field("location") String location, @Field("floor") Integer floor, @Field("internet") Integer internet, @Field("ac") Integer ac, @Field("intercom") Integer intercom, @Field("garage") Integer garage, @Field("elevator") Integer elevator, @Field("balcony") Integer balcony, @Field("yard") Integer yard, @Field("heating") Integer heating, @Field("tv") Integer tv, @Field("user_id") Integer user_id);

    @GET("GetAdsByUserId")
    Call<List<AdWithImageResponse>> getAdsByUserId(@Query("user_id") Integer user_id);

    @GET("FilterAds")
    Call<List<AdWithImageResponse>> filterAds(@Query("sell_rent") Integer sell_rent, @Query("flat_house") Integer flat_house, @Query("from_number_of_rooms") Integer from_number_of_rooms, @Query("to_number_of_rooms") Integer to_number_of_rooms, @Query("from_size") Double from_size, @Query("to_size") Double to_size, @Query("from_price") Double from_price, @Query("to_price") Double to_price, @Query("location") String location, @Query("internet") Integer internet, @Query("ac") Integer ac, @Query("heating") Integer heating, @Query("tv)") Integer tv);

    @PUT("SetAdPicture")
    @FormUrlEncoded
    Call<Integer> setAdPicture(@Field("ad_id") Integer ad_id, @Field("image") String image);

    @GET("GetAdImage")
    Call<AdImageResponse> getAdImage(@Query("id") Integer id);

}
