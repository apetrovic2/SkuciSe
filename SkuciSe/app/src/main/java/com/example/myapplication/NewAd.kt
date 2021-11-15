package com.example.myapplication

import android.content.Intent
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

        val buttonHome = findViewById<ImageButton>(R.id.imageBtnHome)
        buttonHome.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        val buttonProfile = findViewById<ImageButton>(R.id.imageBtnProfile)
        buttonProfile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
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

            val tbTitle = findViewById(R.id.tbTitle) as EditText
            val tbLocation = findViewById(R.id.tbLocation) as EditText
            val tbPrice = findViewById(R.id.tbPrice) as EditText
            val tbSize = findViewById(R.id.tbSize) as EditText
            val tbDescription = findViewById(R.id.tbDescription) as EditText
            val tbFloor = findViewById(R.id.tbFloor) as EditText

            val cbHeating = findViewById(R.id.cbHeating) as CheckBox
            val cbInternet = findViewById(R.id.cbInternet) as CheckBox
            val cbCableTv = findViewById(R.id.cbCableTv) as CheckBox
            val cbSatelliteTv = findViewById(R.id.cbSatelliteTv) as CheckBox
            val cbIntercom = findViewById(R.id.cbIntercom) as CheckBox
            val cbAc = findViewById(R.id.cbAc) as CheckBox

            val cbGarage = findViewById(R.id.cbGarage) as CheckBox
            val cbElevator = findViewById(R.id.cbElevator) as CheckBox
            val cbBalcony = findViewById(R.id.cbBalcony) as CheckBox
            val cbYard = findViewById(R.id.cbYard) as CheckBox

            var priceText = 0.0
            var sizeText = 0.0
            var floorText = 0

            var titleText = tbTitle.text.toString()
            var locationText = tbLocation.text.toString()
            if(tbPrice.text.toString() != "")
            {
                priceText = tbPrice.text.toString().toDouble()
            }
            if(tbSize.text.toString() != "")
            {
                sizeText = tbSize.text.toString().toDouble()
            }
            var descriptionText = tbDescription.text.toString()
            if(tbFloor.text.toString() != "")
            {
                floorText = tbFloor.text.toString().toInt()
            }

            if(titleText != "" && locationText != "" && priceText != 0.0 && sizeText != 0.0 && descriptionText != "") {
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
                val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                val currentDate = dateFormat.format(Date())

                var heating = 0
                var internet = 0
                var tv = 0
                var intercom = 0
                var ac = 0
                var garage = 0
                var elevator = 0
                var balcony = 0
                var yard = 0

                if(cbHeating.isChecked){heating = 1}
                if(cbInternet.isChecked){internet = 1}
                if(cbCableTv.isChecked){tv = 1}
                if(cbSatelliteTv.isChecked){tv = 2}
                if(cbIntercom.isChecked){intercom = 1}
                if(cbAc.isChecked){ac = 1}
                if(cbGarage.isChecked){garage = 1}
                if(cbElevator.isChecked){elevator = 1}
                if(cbBalcony.isChecked){balcony = 1}
                if(cbYard.isChecked){yard = 1}


                Log.i("ADD AD", "$titleText $priceText $sizeText $currentDate")


                val api = AdApiManager.getAdApi()
                val call = api.addNewAd(
                    titleText,
                    houseFlatNum,
                    sellRentNum,
                    descriptionText,
                    sizeText,
                    currentDate,
                    priceText,
                    locationText,
                    floorText,
                    internet,
                    ac,
                    intercom,
                    garage,
                    elevator,
                    balcony,
                    yard,
                    heating,
                    tv
                )
                call.enqueue(object : Callback<Int> {
                    override fun onResponse(
                        call: Call<Int>,
                        response: Response<Int>
                    ) {
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
            else
            {
                lblAddNewAdMessage.setText("Unesite sve podatke!")
            }
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