package com.tbl.problemsolvers;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.tbl.problemsolvers.utils.MoveAnimation;

public class CoinMoveingAciivity extends AppCompatActivity {

    int count = 0;
    RelativeLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hut_movie_aciivity);

        linearLayout = findViewById(R.id.container);
        ImageView imageView = (findViewById(R.id.Person2));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recurtiontext(10);
            }

        });
    }

    void recurtiontext(int num) {
        if (num == 0) {
            return;
        } else {
            ImageView imageView1 = new ImageView(CoinMoveingAciivity.this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
            imageView1.setLayoutParams(layoutParams);
            imageView1.setImageDrawable(getDrawable(R.drawable.ic_coin));
            float rotation = imageView1.getRotation() + 2;
            imageView1.setRotation(rotation);
            MoveAnimation moveAnimation = new MoveAnimation(imageView1);
            moveAnimation.move(-90, -200)
                    .up(400)
                    .repeat(true)
                    .onRun();
            linearLayout.addView(imageView1);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recurtiontext(num - 1);
                    linearLayout.removeView(imageView1);
                }
            }, 700);
        }
    }

}