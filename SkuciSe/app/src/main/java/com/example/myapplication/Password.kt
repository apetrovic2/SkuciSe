package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.AdApiManager
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        val tbNewPassword = findViewById(R.id.tbNewPassword) as EditText
        val tbConfirmPassword = findViewById(R.id.tbConfirmPassword) as EditText
        val lblChangePasswordMessage = findViewById(R.id.lblChangePasswordMessage) as TextView
        lblChangePasswordMessage.setText("")

        val btnChangePasswordFinal = findViewById(R.id.btnChangePasswordFinal) as Button
        btnChangePasswordFinal.setOnClickListener(){

            var passwordText = tbNewPassword.text.toString()
            var confirmPasswordText = tbConfirmPassword.text.toString()

            if(passwordText == confirmPasswordText) {
                val api = UsersApiManager.getUserApi()
                val call = api.changePassword(AppData.getToken(), passwordText)
                call.enqueue(object : Callback<Int> {
                    override fun onResponse(
                        call: Call<Int>,
                        response: Response<Int>
                    )
                    {
                        if (!response.isSuccessful) {
                            Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                            lblChangePasswordMessage.setText("Neuspešno!")
                            return
                        } else {
                            Log.i("CONNECTION1 ", "SUCCESSFUL")
                            val ind = response.body()!!
                            lblChangePasswordMessage.setText("Uspešno!")
                        }
                    }
                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                        lblChangePasswordMessage.setText("Neuspešno!")
                        return
                    }
                })
            }
            else
            {
                lblChangePasswordMessage.setText("Lozinke se ne poklapaju!")
                tbNewPassword.setText("")
                tbConfirmPassword.setText("")
            }

        }

    }
}