package com.tbl.problemsolvers;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.tbl.problemsolvers.utils.Typewriter;

import java.util.HashMap;

public class TextViewAnimation extends AppCompatActivity {


    //Define a soundpool
    SoundPool soundPool;
    HashMap<Integer, Integer> soundMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_animation);

        AudioAttributes attr = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME) // set the sound scene
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build(); // set the type of sound effect
        soundPool = new SoundPool.Builder().setAudioAttributes(attr) // set the properties of the sound pool
                .setMaxStreams(10) // set to hold up to 10 audio streams
                .build(); // ①
        //The load method loads the specified audio file and returns the loaded audio ID
        //HashMap is used here to manage these audio streams
        soundMap.put(1, soundPool.load(this, R.raw.type_keyboard, 1)); // ②
        soundMap.put(2, soundPool.load(this, R.raw.typelong, 1)); // ②
        soundMap.put(3, soundPool.load(this, R.raw.typesingle, 1)); // ②

        // RunAnimation();
    }

    public void starAnimation(View view) {
        String s = "Sample String Sample\n String Sample String";
        soundPool.play(soundMap.get(3), 1, 1, 0, s.length() - 22, 1); // ③
        Typewriter writer = (Typewriter) findViewById(R.id.typewriter);
        //Add a character every 150ms
        writer.setCharacterDelay(100);
        writer.animateText(s);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    writer.setTextColor(getColor(R.color.red_800));
                }
            }
        }, 4000);


    }

   /* private void RunAnimation() {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.scale);
        a.reset();
        TextView tv = (TextView) findViewById(R.id.tvanim);
        tv.clearAnimation();
        tv.startAnimation(a);
    }*/


}