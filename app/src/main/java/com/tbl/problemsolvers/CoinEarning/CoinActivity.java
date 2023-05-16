package com.tbl.problemsolvers.CoinEarning;

import android.app.Dialog;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tbl.problemsolvers.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoinActivity extends AppCompatActivity {


    @BindView(R.id.coinCounterTxt)
    TextView counterTxt;

    SoundPool soundPool;
    HashMap<Integer, Integer> soundMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        counterTxt.setText("0");

        AudioAttributes attr = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME) // set the sound scene
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build(); // set the type of sound effect
        soundPool = new SoundPool.Builder().setAudioAttributes(attr) // set the properties of the sound pool
                .setMaxStreams(10) // set to hold up to 10 audio streams
                .build(); // ①
        //The load method loads the specified audio file and returns the loaded audio ID
        //HashMap is used here to manage these audio streams
        soundMap.put(1, soundPool.load(this, R.raw.cc, 1)); // ②
        soundMap.put(2, soundPool.load(this, R.raw.one, 1)); // ②
        soundMap.put(3, soundPool.load(this, R.raw.three, 1)); // ②
        soundMap.put(4, soundPool.load(this, R.raw.four, 1)); // ②
        soundMap.put(5, soundPool.load(this, R.raw.five, 1)); // ②

    }

    @OnClick(R.id.AddReward)
    public void AddReward() {

        Dialog dialog = new Dialog(CoinActivity.this);
        View view = LayoutInflater.from(this).inflate(R.layout.giftimage, null, false);
        view.setBackgroundColor(this.getResources().getColor(android.R.color.transparent));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(view);

        dialog.setCancelable(false);
        ImageView imageView = dialog.findViewById(R.id.gifImgId);
        Glide.with(this).asGif().apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).
                load(R.drawable.collect).into(imageView);
        dialog.show();
        soundPool.play(soundMap.get(3), 1, 1, 0, 1, 1); // ③

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                dialog.dismiss();
                soundPool.play(soundMap.get(2), 1, 1, 0, 0, 1); // ③
                counterTxt.setText("" + 10);

            }
        }, 12000);

    }

    @OnClick(R.id.AddReward2)
    public void AddReward2() {

        Dialog dialog = new Dialog(CoinActivity.this);
        View view = LayoutInflater.from(this).inflate(R.layout.giftimage, null, false);
        view.setBackgroundColor(this.getResources().getColor(android.R.color.transparent));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(view);

        dialog.setCancelable(false);
        ImageView imageView = dialog.findViewById(R.id.gifImgId);
        Glide.with(this).asGif().apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).
                load(R.drawable.collect).into(imageView);
        dialog.show();
        soundPool.play(soundMap.get(5), 1, 1, 0, 0, 1); // ③


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                dialog.dismiss();
                soundPool.play(soundMap.get(2), 1, 1, 0, 0, 1); // ③
                counterTxt.setText("" + 10);

            }
        }, 12000);

    }
}