package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Filter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val buttonFilter = findViewById<ImageButton>(R.id.btnFilter)
        buttonFilter.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
        val buttonFilterSell = findViewById<Button>(R.id.btnSell)
        buttonFilterSell.setOnClickListener {
            val intent = Intent(this, FilterSell::class.java)
            startActivity(intent)
        }
    }
}