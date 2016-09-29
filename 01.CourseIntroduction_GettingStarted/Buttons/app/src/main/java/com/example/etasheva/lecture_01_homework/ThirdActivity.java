package com.example.etasheva.lecture_01_homework;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends Activity {

    private TextView mFirstTextView;
    private TextView mSecondTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        this.mFirstTextView = (TextView) findViewById(R.id.textView2);
        this.mSecondTextView = (TextView) findViewById(R.id.textView3);

        if (getIntent() != null){
            String[] data = getIntent().getStringArrayExtra("TEXT_VIEW_DATA");
            this.mFirstTextView.setText(String.format("Last clicked component id: %s", data[0]));
            this.mSecondTextView.setText(String.format("Last clicked component name: %s", data[1]));
        }
    }
}
