package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Notifications : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private var dataList = mutableListOf<DataModelNotifications>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(applicationContext,1)
        notificationAdapter = NotificationAdapter(this)
        recyclerView.adapter = notificationAdapter


        dataList.add(DataModelNotifications("Username","Date","Title",R.drawable.ic_baseline_account_circle_purple_24))

        notificationAdapter.setDataList(dataList)


    }
    override fun onBackPressed() {
    }
}