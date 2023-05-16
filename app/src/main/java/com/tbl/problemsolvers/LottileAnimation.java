package com.tbl.problemsolvers;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class LottileAnimation extends AppCompatActivity {
    SoundPool soundPool;
    HashMap<Integer, Integer> soundMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottile_animation);

        AudioAttributes attr = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME) // set the sound scene
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build(); // set the type of sound effect
        soundPool = new SoundPool.Builder().setAudioAttributes(attr) // set the properties of the sound pool
                .setMaxStreams(10) // set to hold up to 10 audio streams
                .build();

        soundMap.put(1, soundPool.load(this, R.raw.one, 1)); // ②
        soundMap.put(2, soundPool.load(this, R.raw.two, 1)); // ②
        soundMap.put(3, soundPool.load(this, R.raw.welcome, 1));
        soundMap.put(4, soundPool.load(this, R.raw.assalamualaikum, 1));
        soundMap.put(5, soundPool.load(this, R.raw.assalamualaikum, 1));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                soundPool.play(soundMap.get(5), 1, 1, 0, 5, 1); // ③
            }
        }, 500);

    }
}