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
public class MadridFragment extends Fragment {


    public MadridFragment() {
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
        textView.setText("Madrid is the capital of Spain since the year 1562 and is located right in the heart of the Iberian Peninsula. It is in the center of the Castilian plain 646 meters above sea level. Madrid occupies third position in the list of largest cities of Europe. Madrid and its suburbs are based on the banks of the River Manzanares that flows through the centre of the country and Madrid community. In Spain, a community comprises of the main city and the immediate suburbs and villages." +
                System.lineSeparator() +
        "The autonomous community of Castile and León and Castile-La Mancha forms a boundary around this community. Madrid is considered to be the political center of Spain along with being its capital, seat of Government as well as a residence of the Spanish Monarch." +
        System.lineSeparator() +
        "Madrid is considered to be the major financial center of the Iberian Peninsula because of its economic output, high standard of living and huge market size. It is a hub that hosts the head offices of majority of major companies in Spain. Also, it includes the headquarters of the three largest companies in the world out of 100 - Telefónica, Repsol-YPF and Banco Santander.\n" +
                "Being a cosmopolitan city, Madrid, also is a home to the Spanish Royal Family. It is a business center and is also the headquarters for the Government, Spanish Parliament and Public Administration. Moreover, Madrid covers the banking and industrial sectors as well. Most of the textile, food and metal work factories are clustered towards the southern fringe of the city.\n" +
                "Languages spoken in Madrid are: Spanish is the official language of Madrid. However, English is the one widely understood by the tourists. Catalan, Galician and Basque are frequently spoken in the relevant areas.");
        ImageView imgView = (ImageView) view.findViewById(R.id.img);
        imgView.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.madrid_picture));
    }

}
