package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import com.example.myapplication.data.helpers.AppData
import com.example.myapplication.data.remote.UsersApiManager
import com.example.myapplication.data.repository.UserImageResponse
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.InputStream

class RegistrationPicture : AppCompatActivity() {

    ///////////////////////////////////
    var data = ""

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if(uri != null) {
            val viewImage = findViewById<ImageView>(R.id.profilePicture)
            viewImage.setImageURI(uri)

            var cr = baseContext.contentResolver as ContentResolver
            var inputStream = cr.openInputStream(uri) as InputStream
            var bitmap = BitmapFactory.decodeStream(inputStream) as Bitmap
            var baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

            var dataByte = baos.toByteArray()
            data = Base64.encodeToString(dataByte, 0)
        }
    }
    ////////////////////////////////////
    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_picture)
        var id = intent.getIntExtra("user_id", 0)

        val viewImage = findViewById<ImageView>(R.id.profilePicture)
        viewImage.invalidate()
        var bitmap = viewImage.drawable.toBitmap() as Bitmap
        var baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        var dataByte = baos.toByteArray()
        data = Base64.encodeToString(dataByte, 0)

        val lblRegistrationMes = findViewById(R.id.lblRegistrationMessage) as TextView
        lblRegistrationMes.setText("")

        val btnRegistrationFinal = findViewById(R.id.btnRegistrationFinal) as Button
        btnRegistrationFinal.isClickable = true
        btnRegistrationFinal.alpha = 1f
        btnRegistrationFinal.setOnClickListener() {
            btnRegistrationFinal.isClickable = false
            btnRegistrationFinal.alpha = 0.5f
            val apiUserImage = UsersApiManager.getUserApi()
            val callImage = apiUserImage.setProfilePicture(id, data)
            callImage.enqueue(object : Callback<Int> {
                override fun onResponse(
                    call: Call<Int>,
                    response: Response<Int>
                ) {
                    if (!response.isSuccessful) {
                        Log.i("CONNECTION1 ", "NOT SUCCESSFUL")
                        btnRegistrationFinal.isClickable = true
                        btnRegistrationFinal.alpha = 1f
                        return
                    } else {
                        Log.i("CONNECTION1 ", "SUCCESSFUL")
                        val ind = response.body()!!
                        if (ind > 0) {
                            lblRegistrationMes.setText("Uspešna registracija!")
                            btnRegistrationFinal.isClickable = true
                            btnRegistrationFinal.alpha = 1f
                            val intent = Intent(this@RegistrationPicture, Login::class.java)
                            intent.putExtra("reg_message", "Uspešna registracija. Prijavite se!")
                            startActivity(intent)
                        }
                        else
                        {
                            lblRegistrationMes.setText("Neuspešna registracija!")
                        }
                    }
                }
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.i("CONNECTION ", "NOT SUCCESSFUL2")
                    btnRegistrationFinal.isClickable = true
                    btnRegistrationFinal.alpha = 1f
                    return
                }
            })
        }


        /////////////////////////////////////////////////// kod za slike iz galerije

        val IMAGE_CHOOSE = 1000;
        val PERMISSION_CODE = 1001;
        val REQUEST_CODE = 13

        fun chooseImageGallery() {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            //startActivityForResult(intent, IMAGE_CHOOSE)
            getContent.launch("image/*")
        }


        val btnChoosePhoto = findViewById<Button>(R.id.btnChoosePhoto)
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
////////////////////////////////////////////////////////////////
    }
}