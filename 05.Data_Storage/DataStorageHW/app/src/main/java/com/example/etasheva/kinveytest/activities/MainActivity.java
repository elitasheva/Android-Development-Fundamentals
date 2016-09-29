package com.example.etasheva.kinveytest.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.etasheva.kinveytest.LoadDataService;
import com.example.etasheva.kinveytest.R;
import com.example.etasheva.kinveytest.models.LaptopSqlite;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mInfoView;
    private Button mLogin;
    private Button mGetInfo;
    private Button mShowResult;
    private Context ctx;
    private LoadDataService mLoadDataService;
    private Intent mServiceIntent;
    private boolean mIsBinded;
    private MainActivity.BroadcastListener mBroadcastListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.ctx = this;
        this.mInfoView = (TextView) this.findViewById(R.id.info);
        this.mLogin = (Button) this.findViewById(R.id.log);
        this.mGetInfo = (Button) this.findViewById(R.id.get_info);
        this.mShowResult = (Button) this.findViewById(R.id.show_result);
       this.mInfoView.setText("My data is stored in Kinvey. Please select first \"Login\" button to login in Kinvey. Then select \"Get info\" button to transfer the data from Kinvey to SQLite. And finally select \"Show result\" button to see the result.");
        this.mServiceIntent = new Intent(this, LoadDataService.class);
        this.startService(this.mServiceIntent);

        mBroadcastListener = new MainActivity.BroadcastListener();
        IntentFilter filter = new IntentFilter();
        filter.addAction(LoadDataService.BROADCAST_ACTION_LOGIN);
        filter.addAction(LoadDataService.BROADCAST_ACTION_GET_INFO);
        registerReceiver(mBroadcastListener, filter);

        bindService(this.mServiceIntent,connection,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void loginButtonClicked(View view) {
        this.mLoadDataService.attemptToLogin();
    }

    public void getInfoButtonClicked(View view) {
        this.mLoadDataService.attemptToGetInfo();
    }

    public void showResultButtonClicked(View view) {
        ArrayList<LaptopSqlite> result = this.mLoadDataService.showResult();
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }

    public void dropTableButtonClicked(View view) {
        this.mLoadDataService.dropTable();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(this.mBroadcastListener);
        if (mIsBinded){
            unbindService(connection);
        }
        stopService(this.mServiceIntent);
        super.onDestroy();
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LoadDataService.LoadDataServiceBinder binder = (LoadDataService.LoadDataServiceBinder) service;
            mLoadDataService = binder.getService();
            mIsBinded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBinded = false;
        }
    };

    private class BroadcastListener extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(LoadDataService.BROADCAST_ACTION_LOGIN)) {
                mInfoView.setText(intent.getStringExtra("info_login"));
            }else if (intent.getAction().equals(LoadDataService.BROADCAST_ACTION_GET_INFO)){
                mInfoView.setText(intent.getStringExtra("info_get_info"));
            }
        }
    }
}
