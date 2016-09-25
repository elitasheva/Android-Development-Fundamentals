package com.example.etasheva.fragmentsapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.etasheva.fragmentsapp.fragments.HomeFragment;
import com.example.etasheva.fragmentsapp.fragments.NewsFragment;
import com.example.etasheva.fragmentsapp.fragments.TitlesFragment;
import com.example.etasheva.fragmentsapp.models.Article;


public class MainActivity extends AppCompatActivity implements TitlesFragment.DataExchange {

    private TitlesFragment mTitlesFragment;
    private NewsFragment mNewsFragment;
    private HomeFragment mHomeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar custom_toolbar = (Toolbar) this.findViewById(R.id.custom_toolbar);
        this.setSupportActionBar(custom_toolbar);
        this.getSupportActionBar().setTitle(R.string.title_toolbar);
        this.getSupportActionBar().setIcon(R.drawable.toolbar_icon);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.mTitlesFragment = new TitlesFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.title_container, this.mTitlesFragment).commit();

        this.mHomeFragment = new HomeFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.news_container, this.mHomeFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.custom_menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_refresh:
                Toast.makeText(this,"Refresh",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_print:
                Toast.makeText(this,"Print",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_settings:
                Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                this.getSupportFragmentManager().beginTransaction().replace(R.id.news_container, this.mHomeFragment).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onArticleSelected (Article article) {
        this.mNewsFragment = new NewsFragment();
        this.mNewsFragment.updateArticleView(article);
        this.getSupportFragmentManager().beginTransaction().replace(R.id.news_container, this.mNewsFragment).commit();

    }
}
