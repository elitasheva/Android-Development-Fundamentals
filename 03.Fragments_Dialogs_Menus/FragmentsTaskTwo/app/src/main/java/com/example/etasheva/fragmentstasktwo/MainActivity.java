package com.example.etasheva.fragmentstasktwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.example.etasheva.fragmentstasktwo.fragments.MadridFragment;
import com.example.etasheva.fragmentstasktwo.fragments.MainFragment;
import com.example.etasheva.fragmentstasktwo.fragments.ParisFragment;
import com.example.etasheva.fragmentstasktwo.fragments.RomeFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.TownInfoClicked {


    private MainFragment mMainFragment;
    private ParisFragment mParisFragment;
    private RomeFragment mRomeFragment;
    private MadridFragment mMadridFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Home Page");
        this.mMainFragment = new MainFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, this.mMainFragment).commit();
    }


    @Override
    public void onTownInfoClicked(View view) {
        //switch buttons and get what to load
        if (view.getId() == R.id.button_paris) {
            this.setTitle("Paris");
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.mParisFragment = new ParisFragment();
            this.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, this.mParisFragment).commit();
        }
        if (view.getId() == R.id.button_rome) {
            this.setTitle("Rome");
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.mRomeFragment = new RomeFragment();
            this.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, this.mRomeFragment).commit();
        }

        if (view.getId() == R.id.button_madrid) {
            this.setTitle("Madrid");
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.mMadridFragment = new MadridFragment();
            this.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, this.mMadridFragment).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                onBackArrowPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackArrowPressed(){
        this.setTitle("Home Page");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        this.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, this.mMainFragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        this.setTitle("Home Page");
//        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        this.getFragmentManager().beginTransaction().replace(R.id.main_container, this.mMainFragment).commit();
    }
}
