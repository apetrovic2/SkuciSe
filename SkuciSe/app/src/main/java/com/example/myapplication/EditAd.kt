package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.AdApiManager
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.AdResponse
import com.example.myapplication.data.repository.AdWithImageResponse
import com.example.myapplication.data.repository.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EditAd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_ad)
        var id = intent.getIntExtra("id", 0)

        var tbLocation = findViewById(R.id.tbLocation) as EditText
        var tbPrice = findViewById(R.id.tbPrice) as EditText
        var tbSize = findViewById(R.id.tbSize) as EditText
        var tbDescription = findViewById(R.id.tbDescription) as EditText
        var tbTitle = findViewById(R.id.tbTitle) as EditText
        var tbFloor = findViewById(R.id.tbFloor) as EditText

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

        val rbSell = findViewById(R.id.rbSell) as RadioButton
        val rbRent = findViewById(R.id.rbRent) as RadioButton
        val rbFlat = findViewById(R.id.rbFlat) as RadioButton
        val rbHouse = findViewById(R.id.rbHouse) as RadioButton

        var date_start = ""

        val api = AdApiManager.getAdApi()
        val call = api.getAdById(id)
        call.enqueue(object : Callback<AdWithImageResponse> {
            override fun onResponse(
                call: Call<AdWithImageResponse>,
                response: Response<AdWithImageResponse>
            ) {
                if (!response.isSuccessful) {
                    Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                    return
                } else {
                    Log.i("CONNECTION1 ", "SUCCESSFUL")
                    val adImage = response.body()!!

                    if (adImage != null) {
                        var ad = adImage.ad
                        date_start = ad.date_start
                        tbLocation.setText(ad.location)
                        tbPrice.setText(ad.price.toString())
                        tbSize.setText(ad.size.toString())
                        tbTitle.setText(ad.title)
                        tbFloor.setText(ad.floor.toString())
                        tbDescription.setText(ad.description)

                        if(ad.heating == 1){cbHeating.isChecked = true}
                        if(ad.ac == 1){cbAc.isChecked = true}
                        if(ad.internet == 1){cbInternet.isChecked = true}
                        if(ad.intercom == 1){cbIntercom.isChecked = true}
                        if(ad.tv == 1){cbCableTv.isChecked = true}
                        if(ad.tv == 2){cbSatelliteTv.isChecked = true}
                        if(ad.garage == 1){cbGarage.isChecked = true}
                        if(ad.elevator == 1){cbElevator.isChecked = true}
                        if(ad.balcony == 1){cbBalcony.isChecked = true}
                        if(ad.yard == 1){cbYard.isChecked = true}
                        if(ad.sell_rent == 0){rbSell.isChecked = true}
                        if(ad.sell_rent == 1){rbRent.isChecked = true}
                        if(ad.flat_house == 0){rbFlat.isChecked = true}
                        if(ad.flat_house == 1){rbHouse.isChecked = true}

                    }

                }
            }
                override fun onFailure(call: Call<AdWithImageResponse>, t: Throwable) {
                    Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                    return
                }
            })

        var btnEditAdFinal = findViewById(R.id.btnEditAdFinal) as Button
        btnEditAdFinal.setOnClickListener()
        {

            var lblEditAdInfoMessage = findViewById(R.id.lblEditAdInfoMessage) as TextView
            lblEditAdInfoMessage.setText("")

            val rgHouseFlat = findViewById(R.id.rgHouseFlat) as RadioGroup
            var selectedHouseFlat = rgHouseFlat.checkedRadioButtonId
            val rbHouseFlat = findViewById(selectedHouseFlat) as RadioButton

            val rgSellRent = findViewById(R.id.rgSellRent) as RadioGroup
            var selectedSellRent = rgSellRent.checkedRadioButtonId
            val rbSellRent = findViewById(selectedSellRent) as RadioButton

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
                //0 - flat
                //1 - house
                var houseFlatNum = 0

                //0 - sell
                //1 - rent
                var sellRentNum = 0

                if (rbHouseFlat.text == "Kuća") {
                    houseFlatNum = 1
                }
                if (rbSellRent.text == "Izdavanje") {
                    sellRentNum = 1
                }
                //val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                //val currentDate = dateFormat.format(Date())

                var heating = 0
                var internet = 0
                var tv = 0
                var intercom = 0
                var ac = 0
                var garage = 0
                var elevator = 0
                var balcony = 0
                var yard = 0

                if (cbHeating.isChecked) {
                    heating = 1
                }
                if (cbInternet.isChecked) {
                    internet = 1
                }
                if (cbCableTv.isChecked) {
                    tv = 1
                }
                if (cbSatelliteTv.isChecked) {
                    tv = 2
                }
                if (cbIntercom.isChecked) {
                    intercom = 1
                }
                if (cbAc.isChecked) {
                    ac = 1
                }
                if (cbGarage.isChecked) {
                    garage = 1
                }
                if (cbElevator.isChecked) {
                    elevator = 1
                }
                if (cbBalcony.isChecked) {
                    balcony = 1
                }
                if (cbYard.isChecked) {
                    yard = 1
                }


                //Log.i("ADD AD", "$titleText $priceText $sizeText $currentDate")
                val api = AdApiManager.getAdApi()
                val call = api.editAd(
                    id,
                    titleText,
                    houseFlatNum,
                    sellRentNum,
                    descriptionText,
                    sizeText,
                    date_start,
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
                    tv,
                    AppData.getToken()
                )
                call.enqueue(object : Callback<Int> {
                    override fun onResponse(
                        call: Call<Int>,
                        response: Response<Int>
                    ) {
                        if (!response.isSuccessful) {
                            Log.i("CONNECTION1 ", "NOT SUCCESSFUL ${response.message()}")
                            lblEditAdInfoMessage.setText("Neuspešno!")
                            return
                        } else {
                            Log.i("CONNECTION1 ", "SUCCESSFUL")
                            val ind = response.body()!!
                            if(ind == 0)
                            {
                                lblEditAdInfoMessage.setText("Nijedan podatak nije izmenjen!")
                            }
                            if(ind == 1)
                            {
                                lblEditAdInfoMessage.setText("Uspešno izmenjen oglas!")
                            }
                            Log.i("NEW AD STATUS", "" + ind)

                        }
                    }

                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                        lblEditAdInfoMessage.setText("Greška pri konekciji!")
                        return
                    }
                })
            }
            else
            {
                lblEditAdInfoMessage.setText("Unesite sve obavezne podatke!")
            }
        }

    }
}