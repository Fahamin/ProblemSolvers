package com.tbl.problemsolvers;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AudioPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.assalamualaikum);
        mediaPlayer.start();

    }
}