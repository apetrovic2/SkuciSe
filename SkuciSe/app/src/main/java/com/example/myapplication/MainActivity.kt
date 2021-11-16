package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.data.helpers.AppData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(AppData.getToken() > 0)
        {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        val buttonLogin = findViewById<Button>(R.id.btnLogin)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val buttonRegistration = findViewById<Button>(R.id.btnRegistration)
        buttonRegistration.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
    }
}