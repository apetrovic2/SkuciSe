package com.example.myapplication.data.remote;

import com.example.myapplication.data.repository.AppointmentInfoResponse;
import com.example.myapplication.data.repository.AppointmentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IAppointmentApi
{
    @POST("MakeAppointment")
    @FormUrlEncoded
    Call<Integer> MakeAppointment(@Field("user_id") Integer user_id, @Field("ad_id") Integer ad_id, @Field("date") String date);

    @GET("GetAppointmentByOwnerId")
    Call<List<AppointmentInfoResponse>> GetAppointmentByOwnerId(@Query("id") Integer id);

    @PUT("ApproveAppointment")
    @FormUrlEncoded
    Call<Integer> ApproveAppointment(@Field("app_id") Integer app_id, @Field("approve_status") Integer approve_status);

    @GET("GetAppointmentResponse")
    Call<List<AppointmentInfoResponse>> GetAppointmentResponse(@Query("id") Integer id);

}
