package com.example.etasheva.fragmentsapp.fragments;


import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.etasheva.fragmentsapp.R;
import com.example.etasheva.fragmentsapp.RecycleViewSelectedElement;
import com.example.etasheva.fragmentsapp.RecyclerViewAdapter;
import com.example.etasheva.fragmentsapp.models.Article;


/**
 * A simple {@link Fragment} subclass.
 */
public class TitlesFragment extends Fragment implements RecycleViewSelectedElement {

    public interface DataExchange {
        void onArticleSelected (Article article);
    }

    private DataExchange mDataExchange;

    public TitlesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mDataExchange = (DataExchange) context;
    }


    //for the older versions of API
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mDataExchange = (DataExchange) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_titles, container, false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_titles);
        recyclerView.setLayoutManager(layoutManager);
        //set Adapter to recycleView
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(view.getContext(), this);
        recyclerView.setAdapter(adapter);
        //this.mDataExchange = (DataExchange) view.getContext();
        return view;
    }


    @Override
    public void onItemSelected(Article article) {
        Toast.makeText(getActivity(), "Text pressed", Toast.LENGTH_SHORT).show();
        this.mDataExchange.onArticleSelected(article);
    }
}
