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
import com.example.myapplication.data.remote.AdApiManager
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter
    private var dataList = mutableListOf<DataModel>()

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
        val buttonNotifications = findViewById<ImageButton>(R.id.imageBtnNotifications)
        buttonNotifications.setOnClickListener {
            val intent = Intent(this, Notifications::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
        photoAdapter = PhotoAdapter(this)
        recyclerView.adapter = photoAdapter

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
        var lblUserNoAdsMessage = findViewById(R.id.lblUserNoAdsMessage) as TextView
        lblUserNoAdsMessage.setText("Molimo sačekajte!")

        val apiUser = UsersApiManager.getUserApi()
        val call = apiUser.getUserById(AppData.getToken())
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

        val apiUserImage = UsersApiManager.getUserApi()
        val callImage = apiUserImage.getUserImage(AppData.getToken())
        callImage.enqueue(object : Callback<UserImageResponse> {
            override fun onResponse(
                call: Call<UserImageResponse>,
                response: Response<UserImageResponse>) {
                if (!response.isSuccessful) {
                    Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                    return
                } else {
                    Log.i("CONNECTION1 ", "SUCCESSFUL")
                    //var userImage = response.body()!!
                    if (response.body() != null) {
                        val userImage = response.body()!!
                        var image = findViewById(R.id.profilePicture) as ImageView

                        val imageBytes = Base64.decode(userImage.image, 0)
                        val imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

                        image.setImageBitmap(imageBitmap)
                    }
                }
            }
            override fun onFailure(call: Call<UserImageResponse>, t: Throwable) {
                Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                return
            }
        })


        val apiAds = AdApiManager.getAdApi()
        val callAds = apiAds.getAdsByUserId(AppData.getToken())
        callAds.enqueue(object : Callback<List<AdWithImageResponse>> {
            override fun onResponse(
                call: Call<List<AdWithImageResponse>>,
                response: Response<List<AdWithImageResponse>>)
            {
                if (!response.isSuccessful) {
                    Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                    return
                } else {
                    Log.i("CONNECTION1 ", "SUCCESSFUL")
                    val ads = response.body()!!
                    dataList = mutableListOf<DataModel>()
                    if(ads.size == 0)
                    {
                        lblUserNoAdsMessage.setText("Nemate nijedan oglas!")
                    }
                    else
                    {
                        lblUserNoAdsMessage.setText("")

                       // val apiAdImage = AdApiManager.getAdApi()
                        for(ad in ads)
                        {
                            var ind = 0
                            if(ad.ad.user_id == AppData.getToken())
                            {
                                ind = 1
                            }
                            dataList.add(DataModel("${ad.ad.title}","${ad.ad.price.toInt()}$",ad.image, ad.ad.id, ind))


                        }
                        photoAdapter.setDataList(dataList)
                    }
                }
            }
            override fun onFailure(call: Call<List<AdWithImageResponse>>, t: Throwable) {
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