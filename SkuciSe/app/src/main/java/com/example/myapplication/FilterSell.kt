package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.slider.Slider

class FilterSell : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_sell)

        val rgHouseFlat = findViewById(R.id.rgHouseFlat) as RadioGroup

        val cbHeating = findViewById(R.id.cbHeating) as CheckBox
        val cbInternet = findViewById(R.id.cbInternet) as CheckBox
        val cbCableTv = findViewById(R.id.cbCableTv) as CheckBox
        val cbSatelliteTv = findViewById(R.id.cbSatelliteTv) as CheckBox
        val cbIntercom = findViewById(R.id.cbIntercom) as CheckBox
        val cbAc = findViewById(R.id.cbAc) as CheckBox

        val cbGarage = findViewById(R.id.cbGarage) as CheckBox
        val cbElevator = findViewById(R.id.cbElevator) as CheckBox
        val cbBalcony = findViewById(R.id.cbBalcony) as CheckBox
        val cbYard = findViewById(R.id.cbYard) as CheckBox

        val buttonFilter = findViewById<ImageButton>(R.id.btnFilter)
        buttonFilter.setOnClickListener {

            var selectedHouseFlat = rgHouseFlat.checkedRadioButtonId
            val rbHouseFlat = findViewById(selectedHouseFlat) as RadioButton

            var houseFlatNum = 0
            if(rbHouseFlat.text == "Kuća")
            {
                houseFlatNum = 1
            }
            var heating = 0
            var internet = 0
            var tv = 0
            var intercom = 0
            var ac = 0
            var garage = 0
            var elevator = 0
            var balcony = 0
            var yard = 0

            if(cbHeating.isChecked){heating = 1}
            if(cbInternet.isChecked){internet = 1}
            if(cbCableTv.isChecked){tv = 1}
            if(cbSatelliteTv.isChecked){tv = 2}
            if(cbIntercom.isChecked){intercom = 1}
            if(cbAc.isChecked){ac = 1}
            if(cbGarage.isChecked){garage = 1}
            if(cbElevator.isChecked){elevator = 1}
            if(cbBalcony.isChecked){balcony = 1}
            if(cbYard.isChecked){yard = 1}

            val tbLocation = findViewById(R.id.tbLocation) as EditText
            val locationText = tbLocation.text.toString()

            val sliderPrice = findViewById(R.id.sliderPrice) as Slider
            val to_price = sliderPrice.value.toDouble()

            val sliderSize = findViewById(R.id.sliderSize) as Slider
            val to_size = sliderSize.value.toDouble()

            val sliderRooms = findViewById(R.id.sliderRooms) as Slider
            val to_number_of_rooms = sliderRooms.value.toInt()


            val intent = Intent(this, HomePage::class.java)

            intent.putExtra("sell_rent", 0)
            intent.putExtra("flat_house", houseFlatNum)
            intent.putExtra("to_number_of_rooms", to_number_of_rooms)
            intent.putExtra("to_size", to_size)
            intent.putExtra("to_price", to_price)
            intent.putExtra("location", locationText)
            intent.putExtra("internet", internet)
            intent.putExtra("ac", ac)
            intent.putExtra("heating", heating)
            intent.putExtra("tv", tv)

            startActivity(intent)
        }
        val buttonFilterRent = findViewById<Button>(R.id.btnRent)
        buttonFilterRent.setOnClickListener {
            val intent = Intent(this, Filter::class.java)
            startActivity(intent)
        }
    }
}