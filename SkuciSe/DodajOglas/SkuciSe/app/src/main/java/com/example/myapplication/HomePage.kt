package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter
    private var dataList= mutableListOf<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page2)

        val buttonRegistration = findViewById<ImageButton>(R.id.imageBtnNewAd)
        buttonRegistration.setOnClickListener {
            val intent = Intent(this, NewAd::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
        photoAdapter = PhotoAdapter(applicationContext)
        recyclerView.adapter = photoAdapter

        dataList.add(DataModel("Title","Desc",R.drawable.photo1))
        dataList.add(DataModel("Title","Desc",R.drawable.photo2))
        dataList.add(DataModel("Title","Desc",R.drawable.photo3))
        dataList.add(DataModel("Title","Desc",R.drawable.photo4))
        dataList.add(DataModel("Title","Desc",R.drawable.photo5))
        dataList.add(DataModel("Title","Desc",R.drawable.photo6))
        dataList.add(DataModel("Title","Desc",R.drawable.photo7))
        dataList.add(DataModel("Title","Desc",R.drawable.photo8))

        photoAdapter.setDataList(dataList)

    }
}