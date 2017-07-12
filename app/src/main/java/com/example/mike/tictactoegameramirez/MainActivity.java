package com.example.mike.tictactoegameramirez;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    private TicTacToe tttGame;
    private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buildGuiByCode() {

        //Get width on device screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / TicTacToe.SIDE;

        //Create Layout manager as grid layout
        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(TicTacToe.SIDE);
        gridLayout.setRowCount(TicTacToe.SIDE);

        //Create buttons and add to layout
        buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];

        //Button handler
        ButtonHandler bh = new ButtonHandler();

        for (int row = 0; row < TicTacToe.SIDE; row++) {
            for (int col = 0; col < TicTacToe.SIDE; col++) {
                buttons[row][col] = new Button(this);
                buttons[row][col].setTextSize((int) (w * .2));
                buttons[row][col].setOnClickListener(bh);
                gridLayout.addView(buttons[row][col], w, w);
            }
        }

        setContentView(gridLayout);

    }

    public void update(int row, int col) {
        int play = tttGame.play(row, col);
        if (play == 1) {
            buttons[row][col].setText("X");
        } else if (play == 2) {
            buttons[row][col].setText("O");
        }
        if (tttGame.isGameOver()) {
            enableButtons(false);
        }
    }

    public void enableButtons(boolean enabled) {
        for (int row = 0; row < TicTacToe.SIDE; row++) {
            for (int col = 0; col < TicTacToe.SIDE; col++) {
                buttons[row][col].setEnabled(enabled);
            }
        }
    }

    //Button handler class
    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            for (int row = 0; row < TicTacToe.SIDE; row++) {
                for (int col = 0; col < TicTacToe.SIDE; col++) {
                    if (v == buttons[row][col]) {
                        update(row, col);
                    }
                }
            }
        }
    }

}
