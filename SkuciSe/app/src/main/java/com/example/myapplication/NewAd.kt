package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.AdApiManager
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class NewAd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_ad)

        val btnAddNewAd = findViewById(R.id.btnAddNewAd) as Button
        btnAddNewAd.setOnClickListener() {

            var lblAddNewAdMessage = findViewById(R.id.lblAddNewAdMessage) as TextView
            lblAddNewAdMessage.setText("")

            val rgHouseFlat = findViewById(R.id.rgHouseFlat) as RadioGroup
            var selectedHouseFlat = rgHouseFlat.checkedRadioButtonId
            val rbHouseFlat = findViewById(selectedHouseFlat) as RadioButton

            val rgSellRent = findViewById(R.id.rgSellRent) as RadioGroup
            var selectedSellRent = rgSellRent.checkedRadioButtonId
            val rbSellRent = findViewById(selectedSellRent) as RadioButton

            //0 - house
            //1 - flat
            var houseFlatNum = 0

            //0 - buy
            //1 - sell
            var sellRentNum = 0

            if(rbHouseFlat.text == "Stan")
            {
                houseFlatNum = 1
            }
            if(rbSellRent.text == "Izdavanje")
            {
                sellRentNum = 1
            }
            val dateFormat = SimpleDateFormat("dd-mm-yyyy")
            val currentDate = dateFormat.format(Date())

            val tbTitle = findViewById(R.id.tbTitle) as EditText
            val tbLocation = findViewById(R.id.tbLocation) as EditText
            val tbPrice = findViewById(R.id.tbPrice) as EditText
            val tbSize = findViewById(R.id.tbSize) as EditText
            val tbDescription = findViewById(R.id.tbDescription) as EditText

            var titleText = tbTitle.text.toString()
            var locationText = tbLocation.text.toString()
            var priceText = tbPrice.text.toString().toDouble()
            var sizeText = tbSize.text.toString().toDouble()
            var descriptionText = tbDescription.text.toString()


            Log.i("ADD AD", "$titleText $priceText $sizeText $currentDate")

            val api = AdApiManager.getAdApi()
            val call = api.addNewAd(titleText, houseFlatNum, sellRentNum, descriptionText, sizeText, currentDate, priceText, locationText)
            call.enqueue(object : Callback<Int> {
                override fun onResponse(
                    call: Call<Int>,
                    response: Response<Int>
                )
                {
                    if (!response.isSuccessful) {
                        Log.i("CONNECTION1 ", "NOT SUCCESSFUL ${response.message()}")
                        lblAddNewAdMessage.setText("Neuspešno!")
                        return
                    } else {
                        Log.i("CONNECTION1 ", "SUCCESSFUL")
                        val ind = response.body()!!
                        Log.i("NEW AD STATUS", "" + ind)
                        lblAddNewAdMessage.setText("Uspešno dodat oglas!")

                    }
                }
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                    lblAddNewAdMessage.setText("Greška pri konekciji!")
                    return
                }
            })
        }



    }
}