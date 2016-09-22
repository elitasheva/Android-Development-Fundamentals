package com.example.etasheva.spotifyapp;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.etasheva.spotifyapp.fragments.MainFragment;
import com.example.etasheva.spotifyapp.fragments.SongFragment;
import com.example.etasheva.spotifyapp.models.Song;
import com.example.etasheva.spotifyapp.services.MediaService;

public class MainActivity extends AppCompatActivity implements MainFragment.DataExchange, SongFragment.NavigationButtonPressed {

    private MainFragment mMainFragment;
    private SongFragment mSongFragment;
    private Intent mMediaServiceIntent;
    private Song mCurrentSong;
    private boolean isServiceBound;

    private MediaService.MediaServiceBinder mConnectedMediaService;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mConnectedMediaService = (MediaService.MediaServiceBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStop() {
        if (isServiceBound) {
            unbindService(connection);
            isServiceBound = false;
        }
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (savedInstanceState != null) {
//            //Restore the fragment's instance
//            this.mSongFragment = (SongFragment) getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
//            this.getSupportFragmentManager().beginTransaction().replace(R.id.container, this.mSongFragment).commit();
//            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        }
            setContentView(R.layout.activity_main);
            Toolbar custom_toolbar = (Toolbar) this.findViewById(R.id.toolbar);
            this.setSupportActionBar(custom_toolbar);
            this.getSupportActionBar().setTitle(R.string.toolbar_title);
            this.getSupportActionBar().setIcon(R.drawable.tool_main_icon);

            this.mMainFragment = new MainFragment();
            this.getSupportFragmentManager().beginTransaction().replace(R.id.container, this.mMainFragment).commit();

            this.mMediaServiceIntent = new Intent(this, MediaService.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.custom_menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.menu_refresh:
//                Toast.makeText(this,"Refresh",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menu_print:
//                Toast.makeText(this,"Print",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menu_settings:
//                Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
//                break;
            case android.R.id.home:
                this.onBackArrowPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onSongSelected(Song song) {
        stopService(this.mMediaServiceIntent);
        this.mCurrentSong = song;
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.mSongFragment = new SongFragment();
        this.mSongFragment.updateSongViewElements(song);
        this.getSupportFragmentManager().beginTransaction().replace(R.id.container, this.mSongFragment).commit();
    }

    @SuppressWarnings("ConstantConditions")
    private void onBackArrowPressed() {
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        if (this.mMediaServiceIntent == null) {
            this.mMediaServiceIntent = new Intent(this, MediaService.class);
        }
        if (isServiceBound){
            unbindService(connection);
            isServiceBound = false;
        }
        stopService(this.mMediaServiceIntent);
        this.getSupportFragmentManager().beginTransaction().replace(R.id.container, this.mMainFragment).commit();
    }

    @Override
    public void onPlayButtonPressed() {
        //this.mMediaServiceIntent = new Intent(this, MediaService.class);
        this.mMediaServiceIntent.putExtra("song", this.mCurrentSong.getFileSong());
        this.startService(this.mMediaServiceIntent);
        bindService(this.mMediaServiceIntent, connection, Context.BIND_AUTO_CREATE);
        this.isServiceBound = true;
    }

    @Override
    public void onPauseButtonPressed() {
        if (this.mConnectedMediaService != null) {
            this.mConnectedMediaService.getService().onPausePlayer();
        }
    }

    @Override
    public void onStopButtonPressed() {
        if (this.mMediaServiceIntent == null) {
            this.mMediaServiceIntent = new Intent(this, MediaService.class);
        }
        if (isServiceBound){
            unbindService(connection);
            isServiceBound = false;
        }
        stopService(this.mMediaServiceIntent);
    }

    @Override
    public void onRewindButtonPressed() {
        if (this.mConnectedMediaService != null) {
            this.mConnectedMediaService.getService().onRewindPlayer();
        }
    }

    @Override
    public void onForwardButtonPressed() {
        if (this.mConnectedMediaService != null) {
            this.mConnectedMediaService.getService().onForwardPlayer();
        }
    }


//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        //Save the fragment's instance
//        getSupportFragmentManager().putFragment(outState, "mContent", this.mSongFragment);
//        super.onSaveInstanceState(outState);
//    }
}