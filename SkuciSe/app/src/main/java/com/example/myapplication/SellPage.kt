package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.Ad
import com.example.myapplication.data.remote.AdApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SellPage : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter
    private var dataList= mutableListOf<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_page)

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
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
        photoAdapter = PhotoAdapter(applicationContext)
        recyclerView.adapter = photoAdapter



//        dataList.add(DataModel("Title","Desc",R.drawable.photo4))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo5))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo7))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo2))

//        photoAdapter.setDataList(dataList)

        val actionbar = supportActionBar
        actionbar!!.title = ""
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        val api = AdApiManager.getAdApi()
        val call = api.getAllAds(
            0
        )
        call.enqueue(object : Callback<List<Ad>> {
            override fun onResponse(
                call: Call<List<Ad>>,
                response: Response<List<Ad>>
            ) {
                if (!response.isSuccessful) {
                    Log.i("CONNECTION1 ", "NOT SUCCESSFUL ${response.message()}")
                    return
                } else {
                    Log.i("CONNECTION1 ", "SUCCESSFUL")
                    val ads = response.body()!!

                    for(ad in ads)
                    {
                        dataList.add(DataModel("${ad.title}","${ad.description}",R.drawable.photo1))
                    }
                    photoAdapter.setDataList(dataList)
                    Log.i("HOME PAGE", "" + 1)

                }
            }

            override fun onFailure(call: Call<List<Ad>>, t: Throwable) {
                Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                return
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}