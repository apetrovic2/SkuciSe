package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import com.example.myapplication.data.remote.AdApiManager
import com.example.myapplication.data.remote.UsersApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.InputStream

class NewAdPicture : AppCompatActivity() {

    var data = ""

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri ->
        val viewImage = findViewById<ImageView>(R.id.adPicture)
        viewImage.setImageURI(uri)

        var cr = baseContext.contentResolver as ContentResolver
        var inputStream = cr.openInputStream(uri) as InputStream
        var bitmap = BitmapFactory.decodeStream(inputStream) as Bitmap
        var baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        var dataByte = baos.toByteArray()
        data = Base64.encodeToString(dataByte, 0)
    }

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_ad_picture)


        var id = intent.getIntExtra("ad_id", 0)

        val viewImage = findViewById<ImageView>(R.id.adPicture)
        viewImage.invalidate()
        var bitmap = viewImage.drawable.toBitmap() as Bitmap
        var baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        var dataByte = baos.toByteArray()
        data = Base64.encodeToString(dataByte, 0)


        val lblAddNewAdMessage = findViewById(R.id.lblAddNewAdMessage) as TextView
        lblAddNewAdMessage.setText("")

        val btnAdNewAddFinal = findViewById(R.id.btnAdNewAddFinal) as Button
        btnAdNewAddFinal.setOnClickListener() {
            val apiAdImage = AdApiManager.getAdApi()
            val callImage = apiAdImage.setAdPicture(id, data)
            callImage.enqueue(object : Callback<Int> {
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
                        if (ind > 0) {
                            lblAddNewAdMessage.setText("Uspešno dodat oglas!")
                        } else {
                            lblAddNewAdMessage.setText("Neuspešno!")
                        }
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.i("CONNECTION ", "NOT SUCCESSFUL2")
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


        val btnChoosePhoto = findViewById<Button>(R.id.btnAddPicture)
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

    }
}