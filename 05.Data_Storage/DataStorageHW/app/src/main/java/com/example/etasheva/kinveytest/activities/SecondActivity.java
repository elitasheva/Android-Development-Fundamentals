package com.example.etasheva.kinveytest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.etasheva.kinveytest.R;
import com.example.etasheva.kinveytest.RecyclerViewAdapter;
import com.example.etasheva.kinveytest.models.LaptopSqlite;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle data = getIntent().getExtras();
        ArrayList<LaptopSqlite> result = data.getParcelableArrayList("result");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, result);
        recyclerView.setAdapter(adapter);
    }

}

