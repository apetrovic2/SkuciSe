package com.example.myapplication

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.AppointmentApiManager
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.AppointmentInfoResponse
import com.example.myapplication.data.repository.UserImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Notifications : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var notificationAdapter: NotificationAdapter
    private var dataList = mutableListOf<DataModelNotifications>()

    override fun onStart() {
        super.onStart()
        if(dataList.size > 0)
            dataList = mutableListOf<DataModelNotifications>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        val buttonHome = findViewById<ImageButton>(R.id.imageBtnHome)
        buttonHome.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
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

        var lblNotifications = findViewById<TextView>(R.id.lblNotifications)
        lblNotifications.setText("Molimo sačekajte!")

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(applicationContext,1)
        notificationAdapter = NotificationAdapter(this)
        recyclerView.adapter = notificationAdapter


        val appApi = AppointmentApiManager.getAppointmentApi()
        val call = appApi.GetAppointmentByOwnerId(AppData.getToken())
        call.enqueue(object : Callback<List<AppointmentInfoResponse>> {
            override fun onResponse(
                call: Call<List<AppointmentInfoResponse>>,
                response: Response<List<AppointmentInfoResponse>>
            ) {
                if (!response.isSuccessful) {
                    Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                    return
                } else {
                    Log.i("CONNECTION1 ", "SUCCESSFUL")
                    val apps = response.body()!!
                    //dataList = mutableListOf<DataModelNotifications>()
                    for(app in apps) {
                     dataList.add(DataModelNotifications("${app.user.username}","${app.date}","${app.title}",app.user.image.image, app.user.name, app.user.email, app.approved, app.id))
                    }
                    if(dataList.size > 0)
                    {
                        lblNotifications.setText("")
                    }
                    else
                    {
                        lblNotifications.setText("Nemate nijedno obaveštenje!")
                    }
                    notificationAdapter.setDataList(dataList)

                }
            }
            override fun onFailure(call: Call<List<AppointmentInfoResponse>>, t: Throwable) {
                Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                return
            }
        })

        val appApi1 = AppointmentApiManager.getAppointmentApi()
        val call1 = appApi1.GetAppointmentResponse(AppData.getToken())
        call1.enqueue(object : Callback<List<AppointmentInfoResponse>> {
            override fun onResponse(
                call: Call<List<AppointmentInfoResponse>>,
                response: Response<List<AppointmentInfoResponse>>
            ) {
                if (!response.isSuccessful) {
                    Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                    return
                } else {
                    Log.i("CONNECTION1 ", "SUCCESSFUL")
                    val apps = response.body()!!
                    //dataList = mutableListOf<DataModelNotifications>()
                    for(app in apps) {
                        var approved = ""
                        if(app.approved == 0)
                        {
                            approved = "Zahtev na čekanju"
                        }
                        if(app.approved == -1)
                        {
                            approved = "Zahtev odbijen"
                        }
                        if(app.approved == 1)
                        {
                            approved = "Zahtev odobren"
                        }
                        dataList.add(DataModelNotifications("${app.title}","${app.date}","${approved}",app.owner_image, app.user.name, app.user.email, app.approved, app.id))
                    }
                    if(dataList.size > 0)
                    {
                        lblNotifications.setText("")
                    }
                    else
                    {
                        lblNotifications.setText("Nemate nijedno obaveštenje!")
                    }
                    notificationAdapter.setDataList(dataList)

                }
            }
            override fun onFailure(call: Call<List<AppointmentInfoResponse>>, t: Throwable) {
                Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                return
            }
        })

        //dataList.add(DataModelNotifications("Username","Date","Title",R.drawable.ic_baseline_account_circle_purple_24))

        //notificationAdapter.setDataList(dataList)

        val actionbar = supportActionBar
        actionbar!!.title = ""
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }
    override fun onBackPressed() {
    }
}