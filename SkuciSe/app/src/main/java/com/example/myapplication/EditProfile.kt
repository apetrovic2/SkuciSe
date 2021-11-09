package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfile : AppCompatActivity() {

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

        val api = UsersApiManager.getUserApi()
        val call = api.getUserById(AppData.getUserID())
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
                    tbEditUsername.setText(user.username);
                    tbEditName.setText(user.name);
                    tbEditEmail.setText(user.email);
                    tbEditPassword.setText(user.password);
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
                    AppData.getUserID(),
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
                            Log.i("LOGIN STATUS ", "" + status)
                            finish()
                            overridePendingTransition(0, 0)
                            startActivity(intent)
                            overridePendingTransition(0, 0)
                            lblEditUser.setText("Uspešna izmena!")
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

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}