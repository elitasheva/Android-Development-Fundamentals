package com.example.etasheva.tictactoe;

import java.util.Random;

public class TicTacToeGame {

    private static final int BOARD_SIZE = 9;
    private static final char PLAYER_CHAR = 'X';
    private static final char COMPUTER_CHAR = '0';
    private static final char EMPTY_CHAR = ' ';
    private char[] mBoard;
    private Random mRnd;

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public char getComputerChar() {
        return COMPUTER_CHAR;
    }

    public char getPlayerChar() {
        return PLAYER_CHAR;
    }

    public TicTacToeGame() {
        this.mBoard = new char[BOARD_SIZE];
        this.mRnd = new Random();
        this.initializeBoard();
    }

    public void clearBoard() {
        this.initializeBoard();
    }

    public void setMove(char player, int location) {
        this.mBoard[location] = player;
    }

    public int getComputerMove() {

        //try to win -> check if there are already two symbols where the third will make the computer to win
        for (int i = 0; i < getBoardSize(); i++) {
            if (this.mBoard[i] != PLAYER_CHAR && this.mBoard[i] != COMPUTER_CHAR) {
                char current = this.mBoard[i];
                this.mBoard[i] = COMPUTER_CHAR;

                if (checkForWinner() == 3) {
                    setMove(COMPUTER_CHAR, i);
                    return i;
                } else {
                    this.mBoard[i] = current;
                }
            }
        }

        //try to block the user -> check if there are two symbols where the third will make the user to win
        for (int i = 0; i < getBoardSize(); i++) {
            if (this.mBoard[i] != PLAYER_CHAR && this.mBoard[i] != COMPUTER_CHAR) {
                char current = this.mBoard[i];
                this.mBoard[i] = PLAYER_CHAR;
                if (checkForWinner() == 2) {
                    setMove(COMPUTER_CHAR, i);
                    return i;
                } else {
                    this.mBoard[i] = current;
                }
            }
        }

        //set new symbol on the random position
        int move;
        do {
            move = mRnd.nextInt(getBoardSize());
        } while (this.mBoard[move] == PLAYER_CHAR || this.mBoard[move] == COMPUTER_CHAR);

        setMove(COMPUTER_CHAR, move);
        return move;
    }

    public int checkForWinner() {

        //check rows
        for (int i = 0; i <= 6; i += 3) {
            if (this.mBoard[i] == PLAYER_CHAR && this.mBoard[i + 1] == PLAYER_CHAR && this.mBoard[i + 2] == PLAYER_CHAR) {
                return 2;
            }

            if (this.mBoard[i] == COMPUTER_CHAR && this.mBoard[i + 1] == COMPUTER_CHAR && this.mBoard[i + 2] == COMPUTER_CHAR) {
                return 3;
            }
        }

        //check columns
        for (int i = 0; i <= 2; i++) {
            if (this.mBoard[i] == PLAYER_CHAR && this.mBoard[i + 3] == PLAYER_CHAR && this.mBoard[i + 6] == PLAYER_CHAR) {
                return 2;
            }

            if (this.mBoard[i] == COMPUTER_CHAR && this.mBoard[i + 3] == COMPUTER_CHAR && this.mBoard[i + 6] == COMPUTER_CHAR) {
                return 3;
            }
        }

        //check diagonals
        if ((this.mBoard[0] == PLAYER_CHAR && this.mBoard[4] == PLAYER_CHAR && this.mBoard[8] == PLAYER_CHAR) ||
                (this.mBoard[2] == PLAYER_CHAR && this.mBoard[4] == PLAYER_CHAR && this.mBoard[6] == PLAYER_CHAR)) {
            return 2;
        }

        if ((this.mBoard[0] == COMPUTER_CHAR && this.mBoard[4] == COMPUTER_CHAR && this.mBoard[8] == COMPUTER_CHAR) ||
                (this.mBoard[2] == COMPUTER_CHAR && this.mBoard[4] == COMPUTER_CHAR && this.mBoard[6] == COMPUTER_CHAR)) {
            return 3;
        }

        //check for empty cell for possible move
        for (int i = 0; i < getBoardSize(); i++) {
            if (this.mBoard[i] != PLAYER_CHAR && this.mBoard[i] != COMPUTER_CHAR){
                return 0;
            }
        }

        //if the result is tie
        return 1;
    }

    private void initializeBoard() {

        for (int i = 0; i < getBoardSize(); i++) {
            this.mBoard[i] = EMPTY_CHAR;

        }
    }
}
