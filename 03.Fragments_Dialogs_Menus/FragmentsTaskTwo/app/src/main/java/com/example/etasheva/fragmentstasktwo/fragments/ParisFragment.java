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
public class ParisFragment extends Fragment {


    public ParisFragment() {
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
        textView.setText("Paris, the cosmopolitan capital of France, is one of the largest agglomerations in Europe, with 2.2 million people living in the dense, central city and almost 12 million people living in the whole metropolitan area. Located in the north of France on the river Seine, Paris has the well deserved reputation of being the most beautiful and romantic of all cities, brimming with historic associations and remaining vastly influential in the realms of culture, art, fashion, food and design. Dubbed the City of Light (la Ville Lumière) and Capital of Fashion, it is home to the world's finest and most luxurious fashion designers and cosmetics, such as Chanel, Dior, Yves Saint-Laurent, Guerlain, Lancôme, L'Oréal, Clarins, etc. A large part of the city, including the River Seine, is a UNESCO World Heritage Site. The city has the second highest number of Michelin restaurants in the world (after Tokyo) and contains numerous iconic landmarks, such as the world's most visited tourist site the Eiffel Tower, the Arc de Triomphe, the Notre-Dame Cathedral, the Louvre Museum, Moulin Rouge, and Lido, making it the most popular tourist destination in the world with 45 million tourists annually.");
        ImageView imgView = (ImageView) view.findViewById(R.id.img);
        imgView.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.paris_picture));
    }

}
