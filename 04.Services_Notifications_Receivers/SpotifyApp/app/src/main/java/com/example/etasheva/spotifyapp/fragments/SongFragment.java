package com.example.etasheva.spotifyapp.fragments;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.etasheva.spotifyapp.R;
import com.example.etasheva.spotifyapp.models.Song;
import com.example.etasheva.spotifyapp.services.MediaService;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment implements View.OnClickListener {

    public interface NavigationButtonPressed {
        void onPlayButtonPressed();

        void onPauseButtonPressed();

        void onStopButtonPressed();

        void onRewindButtonPressed();

        void onForwardButtonPressed();
    }

    private Button mBtnPlay;
    private Button mBtnStop;
    private Button mBtnRewind;
    private Button mBtnForward;
    private NavigationButtonPressed mNavBtnListener;

    private TextView mTitle;
    private TextView mAuthor;
    private TextView mAlbum;
    private ImageView mPosterView;
    private Song mCurrentSong;

    private SeekBar mSeekBar;
    private int seekMax;
    private static int songEnded = 0;
    private boolean mBroadcastIsRegistered;
    private boolean isPlaying;
    private boolean isOnPause;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mNavBtnListener = (NavigationButtonPressed) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mNavBtnListener = (NavigationButtonPressed) activity;
    }

    public SongFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStop() {
        if (this.mBroadcastIsRegistered) {
            this.getActivity().unregisterReceiver(broadcastReceiver);
            this.mBroadcastIsRegistered = false;
        }
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        this.mBtnPlay = (Button) view.findViewById(R.id.btn_play);
        this.mBtnPlay.setOnClickListener(this);

        this.mBtnStop = (Button) view.findViewById(R.id.btn_stop);
        this.mBtnStop.setOnClickListener(this);

        this.mBtnRewind = (Button) view.findViewById(R.id.btn_rewind);
        this.mBtnRewind.setOnClickListener(this);

        this.mBtnForward = (Button) view.findViewById(R.id.btn_forward);
        this.mBtnForward.setOnClickListener(this);

        this.mTitle = (TextView) view.findViewById(R.id.tv_current_title);
        this.mTitle.setText(this.mCurrentSong.getTitle());

        this.mAuthor = (TextView) view.findViewById(R.id.tv_current_author);
        this.mAuthor.setText(this.mCurrentSong.getAuthor());

        this.mAlbum = (TextView) view.findViewById(R.id.tv_current_album);
        this.mAlbum.setText(this.mCurrentSong.getAlbum());

        this.mPosterView = (ImageView) view.findViewById(R.id.iv_poster);

        int resId = getActivity().getResources().getIdentifier(this.mCurrentSong.getFilePoster(), "drawable", getActivity().getPackageName());

        this.mPosterView.setImageResource(resId);

        this.mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.btn_play:
                    Button btn = (Button) view.findViewById(R.id.btn_play);
                    if (!isPlaying) {
                        if (this.isOnPause) {
                            this.mNavBtnListener.onPauseButtonPressed();
                            this.isOnPause = false;
                        } else {
                            this.mNavBtnListener.onPlayButtonPressed();
                            this.getActivity().registerReceiver(broadcastReceiver, new IntentFilter(MediaService.BROADCAST_ACTION));
                            this.mBroadcastIsRegistered = true;
                        }
                        this.isPlaying = true;
                        btn.setBackgroundResource(R.drawable.pause_icon);
                    } else {
                        this.mNavBtnListener.onPauseButtonPressed();
                        this.isPlaying = false;
                        this.isOnPause = true;
                        btn.setBackgroundResource(R.drawable.play_icon);
                    }
                    break;
                case R.id.btn_stop:
                    this.isPlaying = false;
                    this.isOnPause = false;
                    this.mBtnPlay.setBackgroundResource(R.drawable.play_icon);
                    this.mNavBtnListener.onStopButtonPressed();
                    if (this.mBroadcastIsRegistered) {
                        this.getActivity().unregisterReceiver(broadcastReceiver);
                        this.mBroadcastIsRegistered = false;
                    }
                    break;
                case R.id.btn_rewind:
                    this.mNavBtnListener.onRewindButtonPressed();
                    break;
                case R.id.btn_forward:
                    this.mNavBtnListener.onForwardButtonPressed();
                    break;
            }
        }
    }

    public void updateSongViewElements(Song song) {
        this.mCurrentSong = song;
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }

    };

    private void updateUI(Intent intent) {
        int seekProgress = Integer.parseInt(intent.getStringExtra("counter"));
        this.seekMax = Integer.parseInt(intent.getStringExtra("mediamax"));
        songEnded = Integer.parseInt(intent.getStringExtra("songEnd"));
        this.mSeekBar.setMax(this.seekMax);
        this.mSeekBar.setProgress(seekProgress);
    }
}
