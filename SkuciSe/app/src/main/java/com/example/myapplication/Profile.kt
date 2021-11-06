package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var lblUsername = findViewById(R.id.lblUsername) as TextView
        var lblName = findViewById(R.id.lblName) as TextView


        val api = UsersApiManager.getUserApi()
        val call = api.getUserById(AppData.getUserID())
        call.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(
                call: Call<UsersResponse>,
                response: Response<UsersResponse>)
                {
                    if (!response.isSuccessful) {
                        Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                        return
                    } else {
                        Log.i("CONNECTION1 ", "SUCCESSFUL")
                        val user = response.body()!!
                        lblUsername.setText("@" + user.username)
                        lblName.setText(user.name)
                        Log.i("LOGIN STATUS ", "" + user.username)
                    }
                }
            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                return
            }
        })
    }
}