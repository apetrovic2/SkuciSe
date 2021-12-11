package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.UsersApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    var regMessage = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        regMessage = intent.getStringExtra("reg_message").toString()

        var lblLoginMes = findViewById(R.id.lblLoginMessage) as TextView
        if(regMessage != "null")
            lblLoginMes.setText(regMessage)
        else
            lblLoginMes.setText("")

        val buttonRegistration = findViewById<Button>(R.id.btnRegistrationLogin)
        buttonRegistration.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

//        val buttonLogin = findViewById<Button>(R.id.btnLoginFinal)
//        buttonLogin.setOnClickListener {
//            val intent = Intent(this, Filter::class.java)
//            startActivity(intent)
//        }


        val buttonLogin = findViewById<Button>(R.id.btnLoginFinal)
        buttonLogin.setOnClickListener {


            lblLoginMes.setText("Molimo sačekajte!")
            val intent = Intent(this, HomePage::class.java)

            val api = UsersApiManager.getUserApi()

            var tbUsername = findViewById(R.id.tbUsernameLogin) as EditText
            var tbPassword = findViewById(R.id.tbPasswordLogin) as EditText
            var usernameText = tbUsername.text.toString()
            var passwordText = tbPassword.text.toString()

            if(usernameText != "" && passwordText != "") {
                val call = api.checkLogin("" + usernameText, "" + passwordText)
                call.enqueue(object : Callback<Int> {
                    override fun onResponse(
                        call: Call<Int>,
                        response: Response<Int>
                    ) {
                        if (!response.isSuccessful) {
                            Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                            return
                        } else {
                            Log.i("CONNECTION1 ", "SUCCESSFUL")
                            val ind = response.body()!!
                            Log.i("LOGIN STATUS ", "" + ind)

                            if (ind > 0) {
                                //AppData.setUserID(ind)
                                //AppData(applicationContext)
                                AppData.setToken(ind)
                                lblLoginMes.setText("Uspešna prijava!")
                                startActivity(intent)
                                lblLoginMes.setText("")
                            } else if (ind == -1) {
                                lblLoginMes.setText("Korisničko ime ne postoji!")
                            } else {
                                lblLoginMes.setText("Pogrešna lozinka!")
                            }
                        }
                    }

                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                        lblLoginMes.setText("Greška sa konekcijom!")
                        return
                    }
                })
            }
            else
            {
                lblLoginMes.setText("Unesite sve podatke!")
            }
        }

        val actionbar = supportActionBar
        actionbar!!.title = ""
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return true
//    }

    override fun onBackPressed() {
    }
}