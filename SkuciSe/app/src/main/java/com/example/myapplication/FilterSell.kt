package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class FilterSell : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_sell)

        val buttonFilter = findViewById<ImageButton>(R.id.btnFilter)
        buttonFilter.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
        val buttonFilterRent = findViewById<Button>(R.id.btnRent)
        buttonFilterRent.setOnClickListener {
            val intent = Intent(this, Filter::class.java)
            startActivity(intent)
        }
    }
}