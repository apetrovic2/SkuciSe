package com.example.myapplication.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService
{
    public static IUsersApi Create()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IUsersApi.class);
    }
}


//    val call = api.users
//            call.enqueue(object : Callback<List<UsersResponse>> {
//        override fun onResponse(
//        call: Call<List<UsersResponse>>,
//        response: Response<List<UsersResponse>>
//        ) {
//        if (!response.isSuccessful) {
//        Log.i("CONNECTION ", "NOT SUCCESSFUL")
//        return
//        }
//        else
//        {
//        Log.i("CONNECTION ", "SUCCESSFUL")
//        val users = response.body()!!
//        var content = "username: " + users.get(0).username + ", password: " + users.get(0).password
//
//        Log.i("USER ", content!!)
//        }
//
//        }
//
//        override fun onFailure(call: Call<List<UsersResponse>>, t: Throwable) {
//        Log.i("CONNECTION ", "NOT SUCCESSFUL")
//        return
//        }
//        })




//    val api = UsersApiManager.getUserApi()
//
//    val call = api.getUserById(AppData.getUserID())
//        call.enqueue(object : Callback<UsersResponse> {
//        override fun onResponse(
//        call: Call<UsersResponse>,
//        response: Response<UsersResponse>
//            ) {
//                    if (!response.isSuccessful) {
//                    Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
//                    return
//                    } else {
//                    Log.i("CONNECTION1 ", "SUCCESSFUL")
//                    val user = response.body()!!
//                    Log.i("LOGIN STATUS ", "" + user.username)
//
//                    }
//                    }
//
//                    override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
//        Log.i("CONNECTION ", "NOT SUCCESSFUL2")
//        return
//        }
//        })