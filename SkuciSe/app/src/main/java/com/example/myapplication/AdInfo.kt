package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.model.Ad
import com.example.myapplication.data.remote.AdApiManager
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.AdResponse
import com.example.myapplication.data.repository.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdInfo : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter
    private var dataList= mutableListOf<DataModel>()

    override fun onStart() {
        super.onStart()
        if(AppData.getToken() == 0)
        {
//            val intent = Intent(this, Login::class.java)
//            startActivity(intent)
            return
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad_info)
        var id = intent.getIntExtra("id", 0)
        Log.i("AD ID:", "" + id)

        var tbLocation = findViewById(R.id.tbLocation) as TextView
        var tbPrice = findViewById(R.id.tbPrice) as TextView
        var tbSize = findViewById(R.id.tbSize) as TextView

        val buttonEditAd = findViewById<Button>(R.id.btnEditAd)
        buttonEditAd.setOnClickListener {
            val intent = Intent(this, EditAd::class.java)
            startActivity(intent)
        }

//        val api = AdApiManager.getAdApi()
//        val call = api.getAdById(id)
//        call.enqueue(object : Callback<AdResponse> {
//            override fun onResponse(
//                call: Call<AdResponse>,
//                response: Response<AdResponse>
//            )
//            {
//                if (!response.isSuccessful) {
//                    Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
//                    return
//                } else {
//                    Log.i("CONNECTION1 ", "SUCCESSFUL")
//                    val ad = response.body()!!
//                    tbLocation.setText(ad.location)
//                    tbPrice.setText(ad.price.toString())
//                    tbSize.setText(ad.size.toString())
//                    Log.i("AD STATUS ", "" + ad.title)
//                }
//            }
//            override fun onFailure(call: Call<AdResponse>, t: Throwable) {
//                Log.i("CONNECTION ", "NOT SUCCESSFUL2")
//                return
//            }
//        })


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