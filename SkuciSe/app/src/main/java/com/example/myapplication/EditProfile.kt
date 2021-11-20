package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfile : AppCompatActivity() {

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
        setContentView(R.layout.activity_edit_profile)
        val actionbar = supportActionBar
        actionbar!!.title = ""
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        var lblEditUser = findViewById(R.id.lblEditUser) as TextView
        lblEditUser.setText("")

        var tbEditUsername = findViewById(R.id.tbEditUsername) as EditText
        var tbEditName = findViewById(R.id.tbEditName) as EditText
        var tbEditEmail = findViewById(R.id.tbEditEmail) as EditText
        var tbEditPassword = findViewById(R.id.tbEditPassword) as EditText

        val buttonPassword = findViewById<ImageButton>(R.id.imgBtnPassword)
        buttonPassword.setOnClickListener {
            val intent = Intent(this, Password::class.java)
            startActivity(intent)
        }

        val api = UsersApiManager.getUserApi()
        val call = api.getUserById(AppData.getToken())
        call.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(
                call: Call<UsersResponse>,
                response: Response<UsersResponse>
            )
            {
                if (!response.isSuccessful) {
                    Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                    return
                } else {
                    Log.i("CONNECTION1 ", "SUCCESSFUL")
                    val user = response.body()!!
                    tbEditUsername.setText(user.username)
                    tbEditName.setText(user.name)
                    tbEditEmail.setText(user.email)
                    tbEditPassword.setText("")
                    Log.i("LOGIN STATUS ", "" + user.username)
                }
            }
            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                return
            }
        })

        val btnEditFinal = findViewById(R.id.btnEditFinal) as Button
        btnEditFinal.setOnClickListener(){

            var usernameText = tbEditUsername.text.toString()
            var passwordText = tbEditPassword.text.toString()
            var nameText = tbEditName.text.toString()
            var emailText = tbEditEmail.text.toString()

            if(usernameText != "" && passwordText != "" && nameText != "" && emailText != "") {
                val call1 = api.EditUser(
                    AppData.getToken(),
                    usernameText,
                    passwordText,
                    nameText,
                    emailText
                )
                call1.enqueue(object : Callback<Int> {
                    override fun onResponse(
                        call: Call<Int>,
                        response: Response<Int>
                    ) {
                        if (!response.isSuccessful) {
                            Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                            lblEditUser.setText("Neuspešna izmena!")
                            return
                        } else {
                            Log.i("CONNECTION1 ", "SUCCESSFUL")
                            val status = response.body()!!
                            Log.i("EDIT STATUS ", "" + status)
                            if(status > 0) {
                                finish()
                                overridePendingTransition(0, 0)
                                startActivity(intent)
                                overridePendingTransition(0, 0)
                                lblEditUser.setText("Uspešna izmena!")
                            }
                            if(status == 0)
                            {
                                lblEditUser.setText("Nijedan podatak nije izmenjen!")
                            }
                            if(status == -2)
                            {
                                lblEditUser.setText("Neispravna lozinka!")
                            }
                            if(status == -3)
                            {
                                lblEditUser.setText("Korisničko ime je zauzeto!")
                            }
                            if(status == -4)
                            {
                                lblEditUser.setText("Email je zauzet!")
                            }
                        }
                    }

                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                        lblEditUser.setText("Greška sa konekcijom!")
                        return
                    }
                })
            }
            else
            {
                lblEditUser.setText("Unesite sve podatke!")
            }

        }

/////////////////////////////////////////////////// kod za slike iz galerije

        val IMAGE_CHOOSE = 1000;
        val PERMISSION_CODE = 1001;
        val REQUEST_CODE = 13

        fun chooseImageGallery() {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_CHOOSE)
        }


        val btnChoosePhoto = findViewById<ImageButton>(R.id.btnChoosePhoto)
        btnChoosePhoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    chooseImageGallery();
                }
            } else {
                chooseImageGallery();
            }
        }
        fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            when (requestCode) {
                PERMISSION_CODE -> {
                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        chooseImageGallery()
                    } else {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        fun onActivityResult(resultCode: Int, requestCode: Int, data: Intent?) {
            if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                val viewImage = findViewById<ImageView>(R.id.profilePicture)
                viewImage.setImageURI(data?.data)
            }
        }
////////////////////////////////////////////////////////////////////////////////////

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}