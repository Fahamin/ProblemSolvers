package com.tbl.problemsolvers;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class SoundEffectActivity extends AppCompatActivity implements View.OnClickListener {
    Button bomb, shot, arrow;
    //Define a soundpool
    SoundPool soundPool;
    HashMap<Integer, Integer> soundMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_effect);

        bomb = (Button) findViewById(R.id.bomb);
        shot = (Button) findViewById(R.id.shot);
        arrow = (Button) findViewById(R.id.arrow);
        AudioAttributes attr = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME) // set the sound scene
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build(); // set the type of sound effect
        soundPool = new SoundPool.Builder().setAudioAttributes(attr) // set the properties of the sound pool
                .setMaxStreams(10) // set to hold up to 10 audio streams
                .build(); // ①
        //The load method loads the specified audio file and returns the loaded audio ID
        //HashMap is used here to manage these audio streams
        soundMap.put(1, soundPool.load(this, R.raw.assalamualaikum, 1)); // ②
        soundMap.put(2, soundPool.load(this, R.raw.assalamualaikum, 1)); // ②
        soundMap.put(3, soundPool.load(this, R.raw.three, 1)); // ②

        bomb.setOnClickListener(this);
        shot.setOnClickListener(this);
        arrow.setOnClickListener(this);
    }

    //Method of overriding onclicklistener listener interface
    @Override
    public void onClick(View v) {
        //Determine which button was clicked
        if (v.getId() == R.id.bomb) {
            soundPool.play(soundMap.get(1), 1, 1, 0, 0, 1); // ③
        } else if (v.getId() == R.id.shot) {
            soundPool.play(soundMap.get(2), 1, 1, 0, 0, 1); // ③
        } else if (v.getId() == R.id.arrow) {
            soundPool.play(soundMap.get(3), 1, 1, 0, 0, 1); // ③
        }
    }
}