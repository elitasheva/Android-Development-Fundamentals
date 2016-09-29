package com.example.etasheva.fragmentstasktwo.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.etasheva.fragmentstasktwo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RomeFragment extends Fragment {


    public RomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_town_info, container, false);

        loadViewContent(view);

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void loadViewContent(View view) {
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("Rome (Italian: Roma), the Eternal City, is the capital and largest city of Italy and of the Lazio region. It's the famed city of the Roman Empire, the Seven Hills, La Dolce Vita (the sweet life), the Vatican City and Three Coins in the Fountain. Rome, as a millenium-long centre of power, culture (having been the cradle of one of the globe's greatest civilisations ever) and religion, has exerted a huge influence over the world in its roughly 2800 years of existence.\n" +
                                "The historic centre of the city is a UNESCO World Heritage Site. With wonderful palaces, millennium-old churches, grand romantic ruins, opulent monuments, ornate statues and graceful fountains, Rome has an immensely rich historical heritage and cosmopolitan atmosphere, making it one of Europe's and the world's most visited, famous, influential and beautiful capitals. Today, Rome has a growing nightlife scene and is also seen as a shopping heaven, being regarded as one of the fashion capitals of the world (some of Italy's oldest jewellery and clothing establishments were founded in the city).\n" +
                                "With so many sights and things to do, Rome can truly be classified a \"global city\".");
        ImageView imgView = (ImageView) view.findViewById(R.id.img);
        imgView.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.rome_picture));
    }

}
