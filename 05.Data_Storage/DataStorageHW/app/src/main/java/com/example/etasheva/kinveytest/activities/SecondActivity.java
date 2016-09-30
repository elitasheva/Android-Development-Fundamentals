package com.example.etasheva.kinveytest.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.etasheva.kinveytest.LoadDataService;
import com.example.etasheva.kinveytest.R;
import com.example.etasheva.kinveytest.RecyclerViewAdapter;
import com.example.etasheva.kinveytest.models.LaptopSqlite;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private LoadDataService mLoadDataService;
    private Intent mServiceIntent;
    private boolean mIsBinded;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.ctx = this;
        this.mServiceIntent = new Intent(this, LoadDataService.class);
        bindService(this.mServiceIntent,connection, Context.BIND_AUTO_CREATE);

    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LoadDataService.LoadDataServiceBinder binder = (LoadDataService.LoadDataServiceBinder) service;
            mLoadDataService = binder.getService();
            mIsBinded = true;
            ArrayList<LaptopSqlite> result = mLoadDataService.showResult();

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(layoutManager);

            RecyclerViewAdapter adapter = new RecyclerViewAdapter(ctx, result);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBinded = false;
        }
    };


}

