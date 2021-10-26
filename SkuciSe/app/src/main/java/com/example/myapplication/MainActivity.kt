package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.UsersRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonLogin = findViewById<Button>(R.id.btnLogin)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            val mainApp = MainApplication()
            //val response = mainApp.responseStr[0]
            //val url = "http://10.0.2.2:5000/api/registration"
            //val response = Helper().run(url)
            //Log.i("USERNAME:::::::::::::::", response + "")
//            val uam = UsersApiManager.getInstance()
//            val ur = UsersRepository.getInstance(uam)
//            val users = ur.users
//            val us = users.value
//            val user = us?.get(0)
//            if (user != null) {
//                Log.i("USERNAME:::::::::::::::", user.username)
//            }
//            else
//            {
//                Log.i("USERNAME:::::::::::::::", "NULL")
//            }
        }
        val buttonRegistration = findViewById<Button>(R.id.btnRegistration)
        buttonRegistration.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

    }
}