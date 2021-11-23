package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.model.Ad
import com.example.myapplication.data.remote.AdApiManager
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.AdResponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePage : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter
    private var dataList = mutableListOf<DataModel>()

    var sell_rent = -1
    var flat_house = 0
    var to_number_of_rooms = 0
    var to_size = 0.0
    var to_price = 0.0
    var location = ""
    var internet = 0
    var ac = 0
    var heating = 0
    var tv = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page2)

        sell_rent = intent.getIntExtra("sell_rent", -1)
        flat_house = intent.getIntExtra("flat_house", 0)
        to_number_of_rooms = intent.getIntExtra("to_number_of_rooms", 0)
        to_size = intent.getDoubleExtra("to_size", 0.0)
        to_price = intent.getDoubleExtra("to_price", 0.0)
        location = intent.getStringExtra("location").toString()
        internet = intent.getIntExtra("internet", 0)
        ac = intent.getIntExtra("ac", 0)
        heating = intent.getIntExtra("heating", 0)
        tv = intent.getIntExtra("tv", 0)


        val buttonNewAd = findViewById<ImageButton>(R.id.imageBtnNewAd)
        buttonNewAd.setOnClickListener {
            val intent = Intent(this, NewAd::class.java)
            startActivity(intent)
        }

        val buttonProfile = findViewById<ImageButton>(R.id.imageBtnProfile)
        buttonProfile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val buttonRent = findViewById<Button>(R.id.btnRent)
        buttonRent.setOnClickListener {
            val intent = Intent(this, RentPage::class.java)
            startActivity(intent)
        }
        val buttonSell = findViewById<Button>(R.id.btnSell)
        buttonSell.setOnClickListener {
            val intent = Intent(this, SellPage::class.java)
            startActivity(intent)
        }
        val buttonFilter = findViewById<ImageButton>(R.id.btnFilter)
        buttonFilter.setOnClickListener {
            val intent = Intent(this, Filter::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
        photoAdapter = PhotoAdapter(this)
        recyclerView.adapter = photoAdapter




//        dataList.add(DataModel("Title","Desc",R.drawable.photo1))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo2))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo3))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo4))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo5))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo6))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo7))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo8))
//
//        photoAdapter.setDataList(dataList)

        val actionbar = supportActionBar
        actionbar!!.title = ""
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()

        if(AppData.getToken() == 0)
        {
//            val intent = Intent(this, Login::class.java)
//            startActivity(intent)
            return
        }

        if(sell_rent == -1) {
            val api = AdApiManager.getAdApi()
            val call = api.getAllAds(
                -1
            )
            call.enqueue(object : Callback<List<AdResponse>> {
                override fun onResponse(
                    call: Call<List<AdResponse>>,
                    response: Response<List<AdResponse>>
                ) {
                    if (!response.isSuccessful) {
                        Log.i("CONNECTION1 ", "NOT SUCCESSFUL ${response.message()}")
                        return
                    } else {
                        Log.i("CONNECTION1 ", "SUCCESSFUL")
                        val ads = response.body()!!
                        dataList = mutableListOf<DataModel>()

                        for (ad in ads) {
                            dataList.add(
                                DataModel(
                                    "${ad.title}",
                                    "${ad.price.toString()}$",
                                    R.drawable.photo1,
                                    ad.id
                                )
                            )
                        }
                        photoAdapter.setDataList(dataList)

                    }
                }

                override fun onFailure(call: Call<List<AdResponse>>, t: Throwable) {
                    Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                    return
                }
            })
        }
        else
        {
            val api = AdApiManager.getAdApi()
            val call = api.filterAds(sell_rent, flat_house, 0, to_number_of_rooms, 0.0, to_size, 0.0, to_price,
            location, internet, ac, heating, tv)
            call.enqueue(object : Callback<List<AdResponse>> {
                override fun onResponse(
                    call: Call<List<AdResponse>>,
                    response: Response<List<AdResponse>>
                ) {
                    if (!response.isSuccessful) {
                        Log.i("CONNECTION1 ", "NOT SUCCESSFUL ${response.message()}")
                        return
                    } else {
                        Log.i("CONNECTION1 ", "SUCCESSFUL")
                        val ads = response.body()!!
                        dataList = mutableListOf<DataModel>()

                        for (ad in ads) {
                            dataList.add(
                                DataModel(
                                    "${ad.title}",
                                    "${ad.price.toString()}$",
                                    R.drawable.photo1,
                                    ad.id
                                )
                            )
                        }
                        photoAdapter.setDataList(dataList)

                    }
                }

                override fun onFailure(call: Call<List<AdResponse>>, t: Throwable) {
                    Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                    return
                }
            })
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}