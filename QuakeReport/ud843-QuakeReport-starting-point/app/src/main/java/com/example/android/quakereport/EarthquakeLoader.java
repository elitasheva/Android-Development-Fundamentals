package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    private String[] urls;

    public EarthquakeLoader(Context context, String... urls) {
        super(context);
        this.urls = urls;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.e(LOG_TAG, "Start background task");
        if (urls.length < 1 || urls[0] == null) {
            return null;
        }
        String currentURL = urls[0];
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(currentURL);
        return earthquakes;
    }

}
