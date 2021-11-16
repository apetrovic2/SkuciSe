package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter
    private var dataList= mutableListOf<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var btnLogout = findViewById(R.id.btnLogout) as Button
        btnLogout.setOnClickListener()
        {
            AppData.setToken(0);
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val buttonNewAd = findViewById<ImageButton>(R.id.imageBtnNewAd)
        buttonNewAd.setOnClickListener {
            val intent = Intent(this, NewAd::class.java)
            startActivity(intent)
        }

        val buttonHome = findViewById<ImageButton>(R.id.imageBtnHome)
        buttonHome.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
        val buttonEditProfile = findViewById<Button>(R.id.btnEditProfile)
        buttonEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(applicationContext,1)
        photoAdapter = PhotoAdapter(applicationContext)
        recyclerView.adapter = photoAdapter

        dataList.add(DataModel("Title","Desc",R.drawable.photo1, 1))
        dataList.add(DataModel("Title","Desc",R.drawable.photo2, 2))
        dataList.add(DataModel("Title","Desc",R.drawable.photo3, 3))


        photoAdapter.setDataList(dataList)

        val actionbar = supportActionBar
        actionbar!!.title = ""
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()

        if(AppData.getToken() == 0)
        {
            finish()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            return
        }

        var lblUsername = findViewById(R.id.lblUsername) as TextView
        var lblName = findViewById(R.id.lblName) as TextView


        val api = UsersApiManager.getUserApi()
        val call = api.getUserById(AppData.getToken())
        call.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(
                call: Call<UsersResponse>,
                response: Response<UsersResponse>)
            {
                if (!response.isSuccessful) {
                    Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                    return
                } else {
                    Log.i("CONNECTION1 ", "SUCCESSFUL")
                    val user = response.body()!!
                    lblUsername.setText("@" + user.username)
                    lblName.setText(user.name)
                    Log.i("LOGIN STATUS ", "" + user.username)
                }
            }
            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
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