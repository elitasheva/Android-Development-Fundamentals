package com.example.etasheva.lecture_01_homework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {

    private TextView mTextView;
    private int mFirstCount;
    private int mSecondCount;
    private int mThirdCount;
    private Button mClickedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        this.mTextView = (TextView) findViewById(R.id.textView);
    }

    public void onBtnClicked(View view){

        String text = "";
        switch (view.getId()) {
            case R.id.button4:
                this.mClickedButton = (Button) view.findViewById(R.id.button4);
                if (this.mFirstCount == 1) {
                    text = this.mClickedButton.getText().toString();
                } else {
                    text = String.valueOf(this.mClickedButton.getId());
                }
                this.mFirstCount++;
                break;

            case R.id.button5:
                this.mClickedButton = (Button) view.findViewById(R.id.button5);
                if (this.mSecondCount == 1) {
                    text = this.mClickedButton.getText().toString();
                } else {
                    text = String.valueOf(this.mClickedButton.getId());
                }
                this.mSecondCount++;
                break;

            case R.id.button6:
                this.mClickedButton = (Button) view.findViewById(R.id.button6);

                if (this.mThirdCount == 1) {
                    text = this.mClickedButton.getText().toString();
                } else {
                    text = String.valueOf(this.mClickedButton.getId());
                }
                this.mThirdCount++;
                break;
        }

        this.mTextView.setText(text);
    }

    public void onTextViewClicked(View view) {

        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
        String[] data = new String[2];
        if (this.mClickedButton != null){
            data[0] = String.valueOf(this.mClickedButton.getId());
            data[1] = this.mClickedButton.getText().toString();
        }else {
            data[0] = "No button clicked";
            data[1] = "No button clicked";
        }
        intent.putExtra("TEXT_VIEW_DATA", data);
        startActivity(intent);
    }
}
