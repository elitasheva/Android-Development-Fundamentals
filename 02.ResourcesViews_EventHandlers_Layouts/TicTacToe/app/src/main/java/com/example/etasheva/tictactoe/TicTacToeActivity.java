package com.example.etasheva.tictactoe;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicTacToeActivity extends Activity {

    private TicTacToeGame mTicTacToeGame;
    private Button[] mButtons;
    private boolean mPlayerFirst;
    private boolean mGameOver;

    private TextView mPlayerCount;
    private TextView mComputerCount;
    private TextView mTieCount;
    private TextView mInfo;

    private int mPlayerCounter;
    private int mComputerCounter;
    private int mTieCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        this.mTicTacToeGame = new TicTacToeGame();
        this.mButtons = new Button[this.mTicTacToeGame.getBoardSize()];
        this.mPlayerFirst = true;
        this.mGameOver = false;
        this.initializeButtons();
        this.initializeTextViews();
        this.startNewGame();
    }

    public void onButtonClicked(View view) {
        int location = this.getPosition(view);
        if (!this.mGameOver) {
            if (this.mButtons[location].isEnabled()) {
                setMove(this.mTicTacToeGame.getPlayerChar(), location);

                int winner = this.mTicTacToeGame.checkForWinner();
                if (winner == 0) {
                    this.mInfo.setText(R.string.turn_computer);
                    int move = this.mTicTacToeGame.getComputerMove();
                    this.setMove(this.mTicTacToeGame.getComputerChar(), move);
                    winner = this.mTicTacToeGame.checkForWinner();
                }

                if (winner == 0) {
                    this.mInfo.setText(R.string.turn_player);
                }else if (winner == 1){
                    this.mInfo.setText(R.string.result_tie);
                    this.mTieCounter++;
                    this.mTieCount.setText(String.valueOf(this.mTieCounter));
                    this.mGameOver = true;
                }else if (winner == 2){
                    this.mInfo.setText(R.string.result_player_win);
                    this.mPlayerCounter++;
                    this.mPlayerCount.setText(String.valueOf(this.mPlayerCounter));
                    this.mGameOver = true;
                }else {
                    this.mInfo.setText(R.string.result_android_win);
                    this.mComputerCounter++;
                    this.mComputerCount.setText(String.valueOf(this.mComputerCounter));
                    this.mGameOver = true;
                }
            }
        }
    }

    public void onNewGameClicked(View view) {
        this.startNewGame();
    }

    public void onExitGameClicked(View view) {
        TicTacToeActivity.this.finish();
    }

    private int getPosition(View view) {
       switch (view.getId()){
           case R.id.one:
               return 0;
           case R.id.two:
               return 1;
           case R.id.three:
               return 2;
           case R.id.four:
               return 3;
           case R.id.five:
               return 4;
           case R.id.six:
               return 5;
           case R.id.seven:
               return 6;
           case R.id.eight:
               return 7;
           case R.id.nine:
               return 8;
           default:
               return -1;
       }
    }

    private void startNewGame() {
        this.mTicTacToeGame.clearBoard();
        for (int i = 0; i < mButtons.length; i++) {
            mButtons[i].setText("");
            mButtons[i].setEnabled(true);
        }

        if (this.mPlayerFirst) {
            this.mInfo.setText(R.string.player_first);
            this.mPlayerFirst = false;
        } else {
            this.mInfo.setText(R.string.turn_computer);
            int move = this.mTicTacToeGame.getComputerMove();
            setMove(this.mTicTacToeGame.getComputerChar(), move);
            this.mPlayerFirst = true;
        }

        this.mGameOver = false;
    }

    private void setMove(char player, int location) {
        this.mTicTacToeGame.setMove(player, location);
        this.mButtons[location].setEnabled(false);
        this.mButtons[location].setText(String.valueOf(player));
        if (player == this.mTicTacToeGame.getComputerChar()) {
            this.mButtons[location].setTextColor(Color.RED);
        } else {
            this.mButtons[location].setTextColor(Color.GREEN);
        }
    }

    private void initializeTextViews() {
        this.mPlayerCount = (TextView) this.findViewById(R.id.playerCount);
        this.mComputerCount = (TextView) this.findViewById(R.id.computerCount);
        this.mTieCount = (TextView) this.findViewById(R.id.tieCount);
        this.mInfo = (TextView) this.findViewById(R.id.info);

        this.mPlayerCount.setText(String.valueOf(this.mPlayerCounter));
        this.mComputerCount.setText(String.valueOf(this.mComputerCounter));
        this.mTieCount.setText(String.valueOf(this.mTieCounter));
    }

    private void initializeButtons() {
        this.mButtons[0] = (Button) this.findViewById(R.id.one);
        this.mButtons[1] = (Button) this.findViewById(R.id.two);
        this.mButtons[2] = (Button) this.findViewById(R.id.three);
        this.mButtons[3] = (Button) this.findViewById(R.id.four);
        this.mButtons[4] = (Button) this.findViewById(R.id.five);
        this.mButtons[5] = (Button) this.findViewById(R.id.six);
        this.mButtons[6] = (Button) this.findViewById(R.id.seven);
        this.mButtons[7] = (Button) this.findViewById(R.id.eight);
        this.mButtons[8] = (Button) this.findViewById(R.id.nine);
    }

}
