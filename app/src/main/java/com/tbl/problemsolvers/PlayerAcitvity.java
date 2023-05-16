package com.tbl.problemsolvers;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;


public class PlayerAcitvity extends AppCompatActivity {
    View view;
    // Replace video id with your video Id
    private String YOUTUBE_VIDEO_ID = "NtzftGb0EcM";
    private String BASE_URL = "https://www.youtube.com";
    //  private String mYoutubeLink = BASE_URL + "/watch?v=" + YOUTUBE_VIDEO_ID;
    private String mYoutubeLink = "https://d2q8p4pe5spbak.cloudfront.net/bpk-tv/9XM/9XM.isml/index.m3u8";
    //  private String mYoutubeLink = "https://images.all-free-download.com/footage_preview/mp4/clip_of_wild_salmon_swimming_under_stream_6892274.mp4";

    private PlaybackStateListener playbackStateListener;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    protected ExoPlayer player;
    protected StyledPlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_acitvity);
        playerView = findViewById(R.id.mPlayerView);
        playbackStateListener = new PlaybackStateListener();
        // extractYoutubeUrl();
        initializePlayer();
        playsvideo(Uri.parse(mYoutubeLink));


    }
    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory();
// Create a progressive media source pointing to a stream uri.
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(uri));
    }

    public void playsvideo(Uri uri) {
        MediaSource mediaSource = buildMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.setMediaItem(MediaItem.fromUri(uri));
        player.setMediaSource(mediaSource);
        player.prepare();
        player.play();
        Log.e("url", ";play");
    }

    private void initializePlayer() {
        player = new ExoPlayer.Builder(this).build();
        player.addListener(playbackStateListener);
        playerView.setPlayer(player);
        // String videoPath = RawResourceDataSource.buildRawResourceUri(R.raw.poison).toString();
        playsvideo(Uri.parse(mYoutubeLink));
    }


    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentMediaItemIndex();
            player.removeListener(playbackStateListener);
            player.release();
            player = null;
        }
    }


    private class PlaybackStateListener implements Player.Listener {

        @Override
        public void onPlaybackStateChanged(@Player.State int playbackState) {
            switch (playbackState) {
                case ExoPlayer.STATE_IDLE:
                    break;
                case ExoPlayer.STATE_BUFFERING:
                    break;
                case ExoPlayer.STATE_READY:
                    break;
                case ExoPlayer.STATE_ENDED:
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPlayerError(PlaybackException error) {
            switch (error.errorCode) {
                case PlaybackException.ERROR_CODE_DRM_CONTENT_ERROR:
                    // Log.e("", "TYPE_SOURCE: " + error.getSourceException().getMessage());
                    return;
                case PlaybackException.ERROR_CODE_DRM_SCHEME_UNSUPPORTED:
                    // Log.e("", "TYPE_RENDERER: " + error.getRendererException().getMessage());
                    return;
                case PlaybackException.ERROR_CODE_DRM_SYSTEM_ERROR:
                    //   Log.e("", "TYPE_UNEXPECTED: " + error.ge().getMessage());
                    return;
                default:
                    return;
            }
        }
    }

}