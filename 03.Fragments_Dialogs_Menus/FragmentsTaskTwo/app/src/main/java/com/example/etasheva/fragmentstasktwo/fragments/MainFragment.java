package com.example.etasheva.fragmentstasktwo.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.etasheva.fragmentstasktwo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    public interface TownInfoClicked{
        void onTownInfoClicked(View view);
    }

    private Button mBtnParis;
    private Button mBtnRome;
    private Button mBtnMadrid;
    private TownInfoClicked mTownInfoClicked;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mTownInfoClicked = (TownInfoClicked) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mTownInfoClicked = (TownInfoClicked) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        this.mBtnParis = (Button) view.findViewById(R.id.button_paris);
        this.mBtnRome = (Button) view.findViewById(R.id.button_rome);
        this.mBtnMadrid = (Button) view.findViewById(R.id.button_madrid);

        this.mBtnParis.setOnClickListener(this);
        this.mBtnRome.setOnClickListener(this);
        this.mBtnMadrid.setOnClickListener(this);

//        this.mTownInfoClicked = (TownInfoClicked) view.getContext();

        return view;
    }

    @Override
    public void onClick(View view) {
        this.mTownInfoClicked.onTownInfoClicked(view);
    }
}
