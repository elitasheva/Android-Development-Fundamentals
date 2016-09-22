package com.example.etasheva.myfavoriteapp;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class RingtonesFragment extends Fragment {



    public RingtonesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ringtones, container, false);
        //List of the items
        String[] songs = {"CHAINSMOKERS FT HALSEY - CLOSER","DJ SNAKE FT JUSTIN BIEBER - LET ME LOVE YOU","MAJOR LAZER/JUSTIN BIEBER/MO - COLD WATER","CALUM SCOTT - DANCING ON MY OWN","TWENTY ONE PILOTS - HEATHENS","CHAINSMOKERS FT DAYA - DON'T LET ME DOWN","JONAS BLUE FT JP COOPER - PERFECT STRANGERS","DRAKE FT WIZKID &amp; KYLA - ONE DANCE","SHAWN MENDES - TREAT YOU BETTER","DRAKE FT RIHANNA - TOO GOOD","KUNGS VS COOKIN' ON 3 BURNERS - THIS GIRL","CALVIN HARRIS FT RIHANNA - THIS IS WHAT YOU CAME FOR","LIL WAYNE/WIZ KHALIFA/IMAGINE - SUCKER FOR PAIN","CHARLIE PUTH FT SELENA GOMEZ - WE DON'T TALK ANYMORE","TIEKS FT DAN HARKNA - SUNSHINE","ANNE-MARIE - ALARM","MARTIN GARRIX &amp; BEBE REXHA - IN THE NAME OF LOVE","JUSTIN TIMBERLAKE - CAN'T STOP THE FEELING","SIA - CHEAP THRILLS","BOB MARLEY FT LVNDSCAPE/BOLIER - IS THIS LOVE (REMIX)","CLEAN BANDIT FT LOUISA JOHNSON - TEARS","ARIANA GRANDE - INTO YOU","KENT JONES - DON'T MIND","BASTILLE - GOOD GRIEF","MO - FINAL SONG","ELLIE GOULDING - STILL FALLING FOR YOU","OLLY MURS - YOU DON'T KNOW LOVE","CHRISTINE &amp; THE QUEENS - TILTED","FIFTH HARMONY FT FETTY WAP - ALL IN MY HEAD (FLEX)","GNASH FT OLIVIA O'BRIEN - I HATE U I LOVE U","COLDPLAY - HYMN FOR THE WEEKEND","DRAKE - CONTROLLA","ADELE - SEND MY LOVE (TO YOUR NEW LOVER)","SNAKEHIPS FT ZAYN - CRUEL","MIKE POSNER - I TOOK A PILL IN IBIZA","GALANTIS - NO MONEY","M O - WHO DO YOU THINK OF","DNCE - CAKE BY THE OCEAN","CRAIG DAVID &amp; SIGALA - AIN'T GIVING UP","DUA LIPA - HOTTER THAN HELL"};

        //Build Adapter   (android.R.layout.simple_list_item_1)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.ring_item, songs);

        //Configure the list view
        ListView list = (ListView) view.findViewById(R.id.listView);
        list.setAdapter(adapter);
        registerClickCallback(list);

        return view;
    }

    private void registerClickCallback(ListView list) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String message = "Playing " + textView.getText();
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        });
    }


}
