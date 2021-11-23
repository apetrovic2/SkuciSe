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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.model.Ad
import com.example.myapplication.data.remote.AdApiManager
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.AdImageResponse
import com.example.myapplication.data.repository.AdResponse
import com.example.myapplication.data.repository.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        var tbSellRent = findViewById(R.id.tbSellRent) as TextView
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

        val api = AdApiManager.getAdApi()
        val call = api.getAdById(id)
        call.enqueue(object : Callback<AdResponse> {
            override fun onResponse(
                call: Call<AdResponse>,
                response: Response<AdResponse>
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
//                        val callImage = api.getAdImage(ad.id)
//                        callImage.enqueue(object : Callback<AdImageResponse> {
//                            override fun onResponse(
//                                call: Call<AdImageResponse>,
//                                response: Response<AdImageResponse>) {
//                                if (!response.isSuccessful) {
//                                    Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
//                                    return
//                                } else {
//                                    Log.i("CONNECTION1 ", "SUCCESSFUL")
//                                    var adImage = response.body()!!
//                                    val imageBytes = Base64.decode(adImage.image, 0)
//                                    val imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//                                    viewImage.setImageBitmap(imageBitmap)
//
//                                }
//                            }
//                            override fun onFailure(call: Call<AdImageResponse>, t: Throwable) {
//                                Log.i("CONNECTION ", "NOT SUCCESSFUL2")
//                                return
//                            }
//                        })
                        viewImage.setImageResource(R.drawable.photo4)



                        tbLocation.setText(ad.location)
                        tbPrice.setText(ad.price.toInt().toString() + "$")
                        tbSize.setText("Kvadratura: " + ad.size.toInt().toString())
                        tbTitle.setText(ad.title)



                        if(ad.sell_rent == 0)
                        {
                            tbSellRent.setText("Prodaja")
                        }
                        else
                        {
                            tbSellRent.setText("Izdavanje")
                        }
                        if(ad.flat_house == 0)
                        {
                            tbFlatHouse.setText("Stan")
                        }
                        else
                        {
                            tbFlatHouse.setText("Kuća")
                        }
                        tbFloor.setText("Sprat: " + ad.floor.toString())
                        tbDescription.setText("Opis: " + ad.description)
                        var equipmentList = ""
                        if(ad.ac == 1)
                        {
                            if(equipmentList != "")
                            {
                                equipmentList += ", "
                            }
                            equipmentList += "klima"
                        }
                        if(ad.internet == 1)
                        {
                            if(equipmentList != "")
                            {
                                equipmentList += ", "
                            }
                            equipmentList += "internet"
                        }
                        if(ad.tv == 1)
                        {
                            if(equipmentList != "")
                            {
                                equipmentList += ", "
                            }
                            equipmentList += "kablovska"
                        }
                        if(ad.tv == 2)
                        {
                            if(equipmentList != "")
                            {
                                equipmentList += ", "
                            }
                            equipmentList += "satelit"
                        }
                        if(ad.heating == 1)
                        {
                            if(equipmentList != "")
                            {
                                equipmentList += ", "
                            }
                            equipmentList += "grejanje"
                        }
                        if(ad.number_of_rooms != 0)
                        {
                            if(equipmentList != "")
                            {
                                equipmentList += ", "
                            }
                            equipmentList += "broj soba: " + ad.number_of_rooms.toString()
                        }
                        tbEquipment.setText("Oprema: " + equipmentList)


                        var otherList = ""
                        if(ad.garage == 1)
                        {
                            if(otherList != "")
                            {
                                otherList += ", "
                            }
                            otherList += "garaža"
                        }
                        if(ad.elevator == 1)
                        {
                            if(otherList != "")
                            {
                                otherList += ", "
                            }
                            otherList += "lift"
                        }
                        if(ad.intercom == 1)
                        {
                            if(otherList != "")
                            {
                                otherList += ", "
                            }
                            otherList += "interfon"
                        }
                        if(ad.yard == 1)
                        {
                            if(otherList != "")
                            {
                                otherList += ", "
                            }
                            otherList += "dvorište"
                        }
                        tbOther.setText("Ostalo: " + otherList)

                        Log.i("AD STATUS ", "" + ad.title)
                    }

                }
            }
            override fun onFailure(call: Call<AdResponse>, t: Throwable) {
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