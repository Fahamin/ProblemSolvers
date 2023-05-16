package com.tbl.problemsolvers.dataPassSetResult

import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tbl.problemsolvers.R

class OneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        intent.putExtra("key", "Hello Fahamin")
        setResult(-1, intent)
        finish()
    }
}