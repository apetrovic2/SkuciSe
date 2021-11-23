package com.example.myapplication

import android.Manifest
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
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.io.ByteArrayOutputStream
import java.io.InputStream

class RegistrationPicture : AppCompatActivity() {

    ///////////////////////////////////
    var data = ""

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri ->
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
    ////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_picture)



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