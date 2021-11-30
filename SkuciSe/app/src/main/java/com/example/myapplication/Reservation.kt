package com.example.myapplication

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.*
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.AdApiManager
import com.example.myapplication.data.remote.AppointmentApiManager
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.UserImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class Reservation : AppCompatActivity() {
    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        id = intent.getIntExtra("id", 0)

        var lblReservationMessage = findViewById(R.id.lblReservationMessage) as TextView
        lblReservationMessage.setText("")

        var year = 0
        var month = 0
        var day = 0
        var dateString = ""

        var calendarView = findViewById(R.id.calendarView) as CalendarView
        calendarView.setOnDateChangeListener {view, y, m, d ->
            year = y
            month = m + 1
            day = d

            var yearString = "" + year
            var monthString = ""
            if(month < 10)
                monthString += "0"
            monthString += "" + month
            var dayString = ""
            if(day < 10)
                dayString += "0"
            dayString += "" + day

            dateString = "$dayString-$monthString-$yearString"
        }

        var btnReservation = findViewById(R.id.btnReservation) as Button
        btnReservation.setOnClickListener(){
            var tbMinute = findViewById(R.id.tbMinute) as EditText
            var tbHour = findViewById(R.id.tbHour) as EditText

            var hourText = tbHour.text.toString()
            var minutetext = tbMinute.text.toString()

            var hour = 0
            var minute = 0

            if(hourText == "" || minutetext == "")
            {
                lblReservationMessage.setText("Unesite obe vrednosti!")
            }

            else
            {
                hour = hourText.toInt()
                minute = minutetext.toInt()
            }
            if(hour > 23 || minute > 59) {
                lblReservationMessage.setText("Unesite ispravne vrednosti!")
            }
            else
            {
                if(dateString == "")
                {
                    val dateFormat = SimpleDateFormat("dd-MM-yyyy:hh:mm")
                    dateString = dateFormat.format(Date())
                }
                else
                {
                    dateString += ":$hourText:$minutetext"
                }

                val api = AppointmentApiManager.getAppointmentApi()
                val call = api.MakeAppointment(AppData.getToken(), id, dateString)
                call.enqueue(object : Callback<Int> {
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
                                lblReservationMessage.setText("Uspešno poslat zahtev!")
                            }
                            else
                            {
                                lblReservationMessage.setText("Neuspešno poslat zahtev!")
                            }
                        }
                    }
                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                        return
                    }
                })
            }



        }




    }
}