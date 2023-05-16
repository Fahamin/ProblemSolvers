package com.tbl.problemsolvers.dataPassSetResult

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.tbl.problemsolvers.R

class TwoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        findViewById<Button>(R.id.button4).setOnClickListener(View.OnClickListener {

            val intent = Intent(this, OneActivity::class.java)
            activityResultLauncher.launch(intent)

        })
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    var activityResultLauncher = registerForActivityResult(
        StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // There are no request codes
            val data = result.data
            assert(data != null)
            val valueFromOneActivity = data!!.getStringExtra("key")
            Log.e("valueFromOneActivity", "" + valueFromOneActivity)
        }
    }
}


