package com.tbl.problemsolvers;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

public class CustomDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        new AestheticDialog.Builder(this, DialogStyle.CONNECTIFY, DialogType.ERROR)
                .setTitle("Title")
                .setMessage("Message")
                .setDarkMode(true)
                .setAnimation(DialogAnimation.SLIDE_DOWN)
                .show();

    }
}