package com.tbl.problemsolvers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.ramotion.fluidslider.FluidSlider
import com.tbl.problemsolvers.R

class KotlinActivity : AppCompatActivity() {
    lateinit var txt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        txt = findViewById(R.id.txtKtID);
        txt.text = "kotlin"

        val slider = findViewById<FluidSlider>(R.id.fluidSlider)
        slider.positionListener = { p -> Log.e("MainActivity", "current position is: $p" )}
    }
}