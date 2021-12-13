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
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.model.Ad
import com.example.myapplication.data.remote.AdApiManager
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class AdInfo : AppCompatActivity() {

//    private lateinit var recyclerView: RecyclerView
//    private lateinit var photoAdapter: PhotoAdapter
//    private var dataList= mutableListOf<DataModel>()
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
      //  var tbSellRent = findViewById(R.id.tbSellRent) as TextView
        var tbFlatHouse = findViewById(R.id.tbFlatHouse) as TextView
        var tbEquipment = findViewById(R.id.tbEquipment) as TextView
        var tbFloor = findViewById(R.id.tbFloor) as TextView

        val viewImage = findViewById(R.id.adPicture) as ImageView

        val buttonEditAd = findViewById<Button>(R.id.btnEditAd)
        buttonEditAd.setOnClickListener {
            val intent = Intent(this, EditAd::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        var btnCloseAd = findViewById<Button>(R.id.btnCloseAd)
        var btnDeleteAd = findViewById<Button>(R.id.btnDeleteAd)

        btnCloseAd.setOnClickListener(){
            val dateFormat = SimpleDateFormat("dd-MM-yyyy")
            val currentDate = dateFormat.format(Date())

            val api = AdApiManager.getAdApi()
            val call = api.closeAd(id, currentDate)
            call.enqueue(object : Callback<Int> {
                override fun onResponse(
                    call: Call<Int>,
                    response: Response<Int>) {
                    if (!response.isSuccessful) {
                        Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                        return
                    } else {
                        Log.i("CONNECTION1 ", "SUCCESSFUL")
                        //var userImage = response.body()!!
                        if (response.body() != null) {
                            val ind = response.body()!!
                            if(ind > 0)
                            {
                                btnCloseAd.isClickable = false
                                btnCloseAd.alpha = .5f
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                    return
                }
            })
        }

        btnDeleteAd.setOnClickListener(){
            val api = AdApiManager.getAdApi()
            val call = api.deleteAd(id)
            call.enqueue(object : Callback<Int> {
                override fun onResponse(
                    call: Call<Int>,
                    response: Response<Int>) {
                    if (!response.isSuccessful) {
                        Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                        return
                    } else {
                        Log.i("CONNECTION1 ", "SUCCESSFUL")
                        //var userImage = response.body()!!
                        if (response.body() != null) {
                            val ind = response.body()!!
                            if(ind > 0)
                            {
                                val intent = Intent(this@AdInfo, Profile::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                    return
                }
            })
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

                        tbLocation.setText("Lokacija: ${ad.ad.location}")
                        tbPrice.setText("Cena: ${ad.ad.price.toInt().toString()}$")
                        tbSize.setText("Kvadratura: " + ad.ad.size.toInt().toString())
                        tbTitle.setText(ad.ad.title)

                        if(ad.ad.date_end != null)
                        {
                            btnCloseAd.isClickable = false
                            btnCloseAd.alpha = .5f
                            buttonEditAd.isClickable = false
                            buttonEditAd.alpha = .5f
                            btnCloseAd.setText("Oglas je zatvoren")
                        }

                        var adType = ""

                        if(ad.ad.flat_house == 0)
                        {
                            //tbFlatHouse.setText("Stan")
                            adType += "Stan za "
                        }
                        else
                        {
                            //tbFlatHouse.setText("Kuća")
                            adType += "Kuća za "
                        }
                        if(ad.ad.sell_rent == 0)
                        {
                            //tbSellRent.setText("Prodaja")
                            adType += "prodaju"
                        }
                        else
                        {
                            //tbSellRent.setText("Izdavanje")
                            adType += "izdavanje"
                        }
                        tbFlatHouse.setText(adType)
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
        setContentView(R.layout.activity_ad_info)
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