package com.example.etasheva.lecture_01_homework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private int mImplicitCount;
    private int mExplicitCount;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mBtn = (Button) findViewById(R.id.button2);
        this.mBtn.setOnClickListener(this);
    }

    public void onButtonClicked(View view) {
        mImplicitCount++;
        Button implicitButton = (Button) view.findViewById(R.id.button);
        implicitButton.setText(String.valueOf(mImplicitCount));
    }

    @Override
    public void onClick(View v) {
        mExplicitCount++;
        Button explicitButton = (Button) v.findViewById(R.id.button2);
        explicitButton.setText(String.valueOf(mExplicitCount));
    }

    public void onStartTaskTwo(View view) {

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
