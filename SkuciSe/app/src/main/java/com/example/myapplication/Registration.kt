package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.data.remote.UsersApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var lblRegistrationMes = findViewById(R.id.lblRegistrationMessage) as TextView
        lblRegistrationMes.setText("")


        val buttonLogin = findViewById<Button>(R.id.btnLoginRegistration)
        buttonLogin.setOnClickListener {

            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            lblRegistrationMes.setText("")
        }


        val btnRegistrationNext = findViewById<Button>(R.id.btnRegistrationNext)
        btnRegistrationNext.setOnClickListener {

            lblRegistrationMes.setText("Molimo sačekajte!")

            var tbUsername = findViewById(R.id.tbUsernameRegistration) as EditText
            var tbPassword = findViewById(R.id.tbPasswordRegistration) as EditText
            var tbName = findViewById(R.id.tbNameAndLastName) as EditText
            var tbEmail = findViewById(R.id.tbEmail) as EditText

            var usernameText = tbUsername.text.toString()
            var passwordText = tbPassword.text.toString()
            var nametext = tbName.text.toString()
            var emailText = tbEmail.text.toString()

            val api = UsersApiManager.getUserApi()

            if (usernameText != "" && passwordText != "" && nametext != "" && emailText != "") {
                if(usernameText.length > 3) {
                    if (Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {

                        val call = api.Registration(
                            "" + usernameText,
                            "" + passwordText,
                            "" + nametext,
                            "" + emailText
                        )
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

                                    when {
                                        ind > 0 -> {
                                            lblRegistrationMes.setText("Uspešno!")
                                            val intent = Intent(this@Registration, RegistrationPicture::class.java)
                                            intent.putExtra("user_id", ind)
//                                            intent.putExtra("username", usernameText)
//                                            intent.putExtra("password", passwordText)
//                                            intent.putExtra("name", nametext)
//                                            intent.putExtra("email", emailText)

                                            startActivity(intent)
                                        }
                                        ind == 0 -> {
                                            lblRegistrationMes.setText("Greška, pokušajte opet!")
                                        }
                                        ind == -1 -> {
                                            lblRegistrationMes.setText("Korisničko ime je zauzeto!")
                                        }
                                        ind == -3 -> {
                                            lblRegistrationMes.setText("E-mail je zauzet!")
                                        }
                                    }

                                    Log.i("REGISTRATION STATUS ", "" + ind!!)
                                }

                            }

                            override fun onFailure(call: Call<Int>, t: Throwable) {
                                Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                                lblRegistrationMes.setText("Greška sa konekcijom!")
                                return
                            }
                        })
                    }
                    else
                    {
                        lblRegistrationMes.setText("Neispravan e-mail!")
                    }
                }
                else
                {
                    lblRegistrationMes.setText("Username mora imati više od 3 karaktera!")
                }

            }
            else
            {
                lblRegistrationMes.setText("Unesite sve podatke!")
            }

        }


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