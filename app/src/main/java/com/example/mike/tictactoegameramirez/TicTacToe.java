package com.example.mike.tictactoegameramirez;

/**
 * Created by Mike on 7/12/2017.
 */

public class TicTacToe {

    public static final int SIDE = 3;
    private int turn;
    private int[][] game;

    public TicTacToe() {
        game = new int[SIDE][SIDE];
        resetGame();
    }

    public int play(int row, int col) {
        int currentTurn = turn;
        if (row >= 0 && col >= 0 && row < SIDE && col < SIDE && game[row][col] == 0) {
            game[row][col] = turn;
            if (turn == 1) {
                turn = 2;
            } else {
                turn = 1;
            }

            return currentTurn;
        } else return 0;
    }

    public int whoWon()
}
