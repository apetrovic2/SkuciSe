package com.example.myapplication

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.AdApiManager
import com.example.myapplication.data.repository.AdResponse
import com.example.myapplication.data.repository.AdWithImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdInfoVisitor : AppCompatActivity() {
    var id = 0

    override fun onStart() {
        super.onStart()
        if(AppData.getToken() == 0)
        {
//            val intent = Intent(this, Login::class.java)
//            startActivity(intent)
            return
        }
        var tbLocation = findViewById(R.id.tbLocation) as TextView
        var tbPrice = findViewById(R.id.tbPrice) as TextView
        var tbSize = findViewById(R.id.tbSize) as TextView
        var tbDescription = findViewById(R.id.tbDescription) as TextView
        var tbOther = findViewById(R.id.tbOther) as TextView
        var tbTitle = findViewById(R.id.tbTitle) as TextView
        var tbSellRent = findViewById(R.id.tbSellRent) as TextView
        var tbFlatHouse = findViewById(R.id.tbFlatHouse) as TextView
        var tbEquipment = findViewById(R.id.tbEquipment) as TextView
        var tbFloor = findViewById(R.id.tbFloor) as TextView

        val viewImage = findViewById(R.id.adPicture) as ImageView

        val buttonReservation = findViewById<Button>(R.id.btnReservation)
        buttonReservation.setOnClickListener {
            val intent = Intent(this, Reservation::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        val api = AdApiManager.getAdApi()
        val call = api.getAdById(id)
        call.enqueue(object : Callback<AdWithImageResponse> {
            override fun onResponse(
                call: Call<AdWithImageResponse>,
                response: Response<AdWithImageResponse>
            )
            {
                if (!response.isSuccessful) {
                    Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                    return
                } else {
                    Log.i("CONNECTION1 ", "SUCCESSFUL")
                    val ad = response.body()!!
                    if(ad != null)
                    {

                        val imageBytes = Base64.decode(ad.image, 0)
                        val imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

                        viewImage.setImageBitmap(imageBitmap)

                        tbLocation.setText(ad.ad.location)
                        tbPrice.setText(ad.ad.price.toInt().toString() + "$")
                        tbSize.setText("Kvadratura: " + ad.ad.size.toInt().toString())
                        tbTitle.setText(ad.ad.title)



                        if(ad.ad.sell_rent == 0)
                        {
                            tbSellRent.setText("Prodaja")
                        }
                        else
                        {
                            tbSellRent.setText("Izdavanje")
                        }
                        if(ad.ad.flat_house == 0)
                        {
                            tbFlatHouse.setText("Stan")
                        }
                        else
                        {
                            tbFlatHouse.setText("Kuća")
                        }
                        tbFloor.setText("Sprat: " + ad.ad.floor.toString())
                        tbDescription.setText("Opis: " + ad.ad.description)
                        var equipmentList = ""
                        if(ad.ad.ac == 1)
                        {
                            if(equipmentList != "")
                            {
                                equipmentList += ", "
                            }
                            equipmentList += "klima"
                        }
                        if(ad.ad.internet == 1)
                        {
                            if(equipmentList != "")
                            {
                                equipmentList += ", "
                            }
                            equipmentList += "internet"
                        }
                        if(ad.ad.tv == 1)
                        {
                            if(equipmentList != "")
                            {
                                equipmentList += ", "
                            }
                            equipmentList += "kablovska"
                        }
                        if(ad.ad.tv == 2)
                        {
                            if(equipmentList != "")
                            {
                                equipmentList += ", "
                            }
                            equipmentList += "satelit"
                        }
                        if(ad.ad.heating == 1)
                        {
                            if(equipmentList != "")
                            {
                                equipmentList += ", "
                            }
                            equipmentList += "grejanje"
                        }
                        if(ad.ad.number_of_rooms != 0)
                        {
                            if(equipmentList != "")
                            {
                                equipmentList += ", "
                            }
                            equipmentList += "broj soba: " + ad.ad.number_of_rooms.toString()
                        }
                        tbEquipment.setText("Oprema: " + equipmentList)


                        var otherList = ""
                        if(ad.ad.garage == 1)
                        {
                            if(otherList != "")
                            {
                                otherList += ", "
                            }
                            otherList += "garaža"
                        }
                        if(ad.ad.elevator == 1)
                        {
                            if(otherList != "")
                            {
                                otherList += ", "
                            }
                            otherList += "lift"
                        }
                        if(ad.ad.intercom == 1)
                        {
                            if(otherList != "")
                            {
                                otherList += ", "
                            }
                            otherList += "interfon"
                        }
                        if(ad.ad.yard == 1)
                        {
                            if(otherList != "")
                            {
                                otherList += ", "
                            }
                            otherList += "dvorište"
                        }
                        tbOther.setText("Ostalo: " + otherList)

                        Log.i("AD STATUS ", "" + ad.ad.title)
                    }

                }
            }
            override fun onFailure(call: Call<AdWithImageResponse>, t: Throwable) {
                Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                return
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad_info_visitor)
        id = intent.getIntExtra("id", 0)
        Log.i("AD ID:", "" + id)

//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = GridLayoutManager(applicationContext,1)
//        photoAdapter = PhotoAdapter(applicationContext)
//        recyclerView.adapter = photoAdapter
//
//        dataList.add(DataModel("Title","Desc",R.drawable.photo1, id))
//
//        photoAdapter.setDataList(dataList)

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
