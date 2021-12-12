package com.example.myapplication

import android.graphics.BitmapFactory
import android.media.Image
import android.media.ImageReader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.AppointmentApiManager
import com.example.myapplication.data.repository.AppointmentInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationAcceptDecline : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_accept_decline)

        var id = intent.getIntExtra("id", 0)
        var approved = intent.getIntExtra("approved", 0)
        var title = intent.getStringExtra("title")
        var image = intent.getStringExtra("image")
        var username = intent.getStringExtra("username")
        var name = intent.getStringExtra("name")
        var email = intent.getStringExtra("email")
        var date = intent.getStringExtra("date")

        var tbTitle = findViewById(R.id.tbTitle) as TextView
        var tbUsername = findViewById(R.id.tbUsername) as TextView
        var tbName = findViewById(R.id.tbName) as TextView
        var tbEmail = findViewById(R.id.tbEmail) as TextView
        var tbDate = findViewById(R.id.tbDateTime) as TextView

        var imageView = findViewById(R.id.UserPicture) as ImageView

        var lblmessage = findViewById(R.id.lblMessage) as TextView
        lblmessage.setText("")

        tbTitle.setText(title);
        tbUsername.setText(username)
        tbName.setText(name)
        tbEmail.setText(email)
        tbDate.setText(date)

        val imageBytes = Base64.decode(image, 0)
        val imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        imageView.setImageBitmap(imageBitmap)

        var btnAccept = findViewById(R.id.acceptPicture) as ImageView
        var btnDecline = findViewById(R.id.declinePicture) as ImageView

        if(approved != 0)
        {
            btnAccept.visibility = View.GONE
            btnDecline.visibility = View.GONE
            lblmessage.setText("zahtev je već obrađen!")
        }

        btnAccept.setOnClickListener(){
            val appApi1 = AppointmentApiManager.getAppointmentApi()
            val call1 = appApi1.ApproveAppointment(id, 1)
            call1.enqueue(object : Callback<Int> {
                override fun onResponse(
                    call: Call<Int>,
                    response: Response<Int>
                ) {
                    if (!response.isSuccessful) {
                        Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                        return
                    } else {
                        Log.i("CONNECTION1 ", "SUCCESSFUL")
                        val ind = response.body()!!
                        if(ind > 0)
                        {
                            lblmessage.setText("Uspešno")
                        }
                        if(ind == 0)
                        {
                            lblmessage.setText("Ovaj zahtev je već obrađen")
                        }

                    }
                }
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                    return
                }
            })
        }

        btnDecline.setOnClickListener(){
            val appApi1 = AppointmentApiManager.getAppointmentApi()
            val call1 = appApi1.ApproveAppointment(id, -1)
            call1.enqueue(object : Callback<Int> {
                override fun onResponse(
                    call: Call<Int>,
                    response: Response<Int>
                ) {
                    if (!response.isSuccessful) {
                        Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                        return
                    } else {
                        Log.i("CONNECTION1 ", "SUCCESSFUL")
                        val ind = response.body()!!
                        if(ind > 0)
                        {
                            lblmessage.setText("Uspešno")
                        }
                        if(ind == 0)
                        {
                            lblmessage.setText("Ovaj zahtev je već obrađen")
                        }

                    }
                }
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                    return
                }
            })
        }

        val actionbar = supportActionBar
        actionbar!!.title = ""
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}