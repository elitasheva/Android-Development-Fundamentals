package com.example.etasheva.spotifyapp.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.etasheva.spotifyapp.R;
import com.example.etasheva.spotifyapp.adapter.RecycleViewAdapter;
import com.example.etasheva.spotifyapp.interfaces.RecycleViewSelectedElement;
import com.example.etasheva.spotifyapp.models.Song;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements RecycleViewSelectedElement {

    public interface DataExchange{
        void onSongSelected(Song song);
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private DataExchange mDataExchange;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mDataExchange = (DataExchange) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mDataExchange = (DataExchange) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        this.mLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);

        this.mAdapter = new RecycleViewAdapter(view.getContext(),this);
        this.mRecyclerView.setAdapter(this.mAdapter);

        return view;
    }

    @Override
    public void onItemSelected(Song song) {
        this.mDataExchange.onSongSelected(song);
    }
}
