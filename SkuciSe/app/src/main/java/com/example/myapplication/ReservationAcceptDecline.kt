package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ReservationAcceptDecline : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_accept_decline)


        val actionbar = supportActionBar
        actionbar!!.title = ""
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onBackPressed() {
    }
}