package com.example.etasheva.spotifyapp.services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;

import com.example.etasheva.spotifyapp.R;

import java.io.IOException;

public class MediaService extends Service {

    public class MediaServiceBinder extends Binder {
        public MediaService getService() {
            return MediaService.this;
        }
    }


    private static final int FORWARD_REWIND_CONST = 10000;
    private MediaPlayer mPlayer;
    private IBinder binder = new MediaServiceBinder();
    private int mOnPauseLength;

    private String stnSeekPos;
    private int intSeekPos;
    private int mediaPosition;
    private int mediaMax;
    private final Handler handler = new Handler();
    private static int endSong;
    public static final String BROADCAST_ACTION = "com.example.etasheva.spotifyapp.seekprogress";
    private Intent seekIntent;

    public MediaService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        this.seekIntent = new Intent(BROADCAST_ACTION);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int resId = getBaseContext().getResources().getIdentifier(intent.getStringExtra("song"), "raw", getBaseContext().getPackageName());

        try {
            this.mPlayer = MediaPlayer.create(this, resId);
            this.mPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
            this.mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            this.mPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setupHandler();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mPlayer != null) {
            this.mPlayer.stop();
        }
        this.handler.removeCallbacks(sendUpdatesToUI);
    }

    public void onPausePlayer(){
        if (this.mPlayer != null){
            if (this.mPlayer.isPlaying()){
                this.mOnPauseLength = this.mPlayer.getCurrentPosition();
                this.mPlayer.pause();
            }else {
                this.mPlayer.seekTo(this.mOnPauseLength);
                this.mOnPauseLength = 0;
                this.mPlayer.start();
            }
        }
    }

    public void onForwardPlayer(){
        if (this.mPlayer != null && this.mPlayer.isPlaying()){
            int currentSecond = this.mPlayer.getCurrentPosition();
            if (currentSecond + FORWARD_REWIND_CONST < this.mPlayer.getDuration()){
                this.mPlayer.seekTo(currentSecond + FORWARD_REWIND_CONST);
            }
        }
    }

    public void onRewindPlayer(){
        if (this.mPlayer != null && this.mPlayer.isPlaying()){
            int currentSecond = this.mPlayer.getCurrentPosition();
            if (currentSecond - FORWARD_REWIND_CONST > 0){
                this.mPlayer.seekTo(currentSecond - FORWARD_REWIND_CONST);
            }else {
                this.mPlayer.seekTo(0);
            }
        }
    }


    private void setupHandler(){
        this.handler.removeCallbacks(sendUpdatesToUI);
        this.handler.postDelayed(sendUpdatesToUI, 1000);
    }

    private Runnable sendUpdatesToUI = new Runnable() {
        @Override
        public void run() {
            logMediaPosition();
            handler.postDelayed(this,1000);
        }
    };

    private void logMediaPosition() {
        if (this.mPlayer.isPlaying()){
            this.mediaPosition = this.mPlayer.getCurrentPosition();
            this.mediaMax = this.mPlayer.getDuration();

            this.seekIntent.putExtra("counter",String.valueOf(this.mediaPosition));
            this.seekIntent.putExtra("mediamax",String.valueOf(this.mediaMax));
            this.seekIntent.putExtra("songEnd", String.valueOf(endSong));

            sendBroadcast(this.seekIntent);
        }
    }
}
