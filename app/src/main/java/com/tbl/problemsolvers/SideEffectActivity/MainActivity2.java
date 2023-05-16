package com.tbl.problemsolvers.SideEffectActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tbl.problemsolvers.R;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void Open3rdActivity(View view) {
        Intent intent = new Intent(this, SlideEffectActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_righ,
                R.anim.slide_out_left);
    }

    // when the user pressed back button this function
    // get invoked automatically.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }

}