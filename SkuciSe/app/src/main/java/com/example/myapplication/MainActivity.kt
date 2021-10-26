package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.myapplication.data.remote.IUsersApi
import com.example.myapplication.data.repository.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonLogin = findViewById<Button>(R.id.btnLogin)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api = retrofit.create(IUsersApi::class.java)
            val call = api.users
            call.enqueue(object : Callback<List<UsersResponse>> {
                override fun onResponse(
                    call: Call<List<UsersResponse>>,
                    response: Response<List<UsersResponse>>
                ) {
                    if (!response.isSuccessful) {
                        Log.i("CONNECTION ", "NOT SUCCESSFUL")
                        return
                    }
                    else
                    {
                        Log.i("CONNECTION ", "SUCCESSFUL")
                        val users = response.body()!!
                        var content = "username: " + users.get(0).username + ", password: " + users.get(0).password

                        Log.i("USER ", content!!)
                    }

                }

                override fun onFailure(call: Call<List<UsersResponse>>, t: Throwable) {}
            })

        }
        val buttonRegistration = findViewById<Button>(R.id.btnRegistration)
        buttonRegistration.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

    }

}