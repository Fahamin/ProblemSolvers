package com.tbl.problemsolvers.DarkTheme

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.tbl.problemsolvers.DateRagnePickerActivity
import com.tbl.problemsolvers.R


class DarkThemTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dark_them_test)
        var button = findViewById<Button>(R.id.btnID)


        // Saving state of our app
        // using SharedPreferences
        // Saving state of our app
        // using SharedPreferences
        val sharedPreferences = getSharedPreferences(
            "sharedPrefs", MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        val isDarkModeOn = sharedPreferences
            .getBoolean(
                "isDarkModeOn", false
            )

        // When user reopens the app
        // after applying dark/light mode

        // When user reopens the app
        // after applying dark/light mode
        if (isDarkModeOn) {
            AppCompatDelegate
                .setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
                )
            button.setText(
                "Disable Dark Mode"
            )
        } else {
            AppCompatDelegate
                .setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
            button
                .setText(
                    "Enable Dark Mode"
                )
        }

        button.setOnClickListener(View.OnClickListener {
            // When user taps the enable/disable
            // dark mode button
            if (isDarkModeOn) {

                // if dark mode is on it
                // will turn it off
                AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate
                            .MODE_NIGHT_NO
                    )
                // it will set isDarkModeOn
                // boolean to false
                editor.putBoolean(
                    "isDarkModeOn", false
                );
                editor.apply();

                // change text of Button
                button.setText(
                    "Enable Dark Mode"
                )
                intent = Intent(this,DateRagnePickerActivity::class.java)
                startActivity(intent)
            } else {

                // if dark mode is off
                // it will turn it on
                AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate
                            .MODE_NIGHT_YES
                    );

                // it will set isDarkModeOn
                // boolean to true
                editor.putBoolean(
                    "isDarkModeOn", true
                );
                editor.apply();

                // change text of Button
                button.setText(
                    "Disable Dark Mode"
                )
                intent = Intent(this,DateRagnePickerActivity::class.java)
                startActivity(intent)
            }

        })
    }
}