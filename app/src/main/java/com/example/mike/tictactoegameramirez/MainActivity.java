package com.example.mike.tictactoegameramirez;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TicTacToe tttGame;
    private Button[][] buttons;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tttGame = new TicTacToe();

        buildGuiByCode();

    }

    public void buildGuiByCode() {

        //Get width on device screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / TicTacToe.SIDE;

        //Create Layout manager as grid layout
        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(TicTacToe.SIDE);
        gridLayout.setRowCount(TicTacToe.SIDE + 1);

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

        status = new TextView(this);
        GridLayout.Spec rowSpec = GridLayout.spec(TicTacToe.SIDE, 1);
        GridLayout.Spec columnSpec = GridLayout.spec(0, TicTacToe.SIDE);
        GridLayout.LayoutParams lpStatus = new GridLayout.LayoutParams(rowSpec, columnSpec);
        status.setLayoutParams(lpStatus);

        //Set status characteristics
        status.setWidth(TicTacToe.SIDE * w);
        status.setHeight(w);
        status.setGravity(Gravity.CENTER);
        status.setBackgroundColor(Color.GREEN);
        status.setTextSize((int) (w * .15));
        status.setText((tttGame.result()));
        gridLayout.addView(status);

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
            status.setBackgroundColor(Color.RED);
            enableButtons(false);
            status.setText((tttGame.result()));
            showNewGameDialog();
        }
    }

    public void resetButtons() {

        for (int row = 0; row < TicTacToe.SIDE; row++) {

            for (int col = 0; col < TicTacToe.SIDE; col++) {

                buttons[row][col].setText("");
            }
        }
    }

    public void showNewGameDialog() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("This is fun!!!");
        alert.setMessage("Play again?");
        PlayDialog playAgain = new PlayDialog();

        alert.setPositiveButton("YES", playAgain);
        alert.setNegativeButton("NO", playAgain);
        alert.show();
    }


    public void enableButtons(boolean enabled) {
        for (int row = 0; row < TicTacToe.SIDE; row++) {
            for (int col = 0; col < TicTacToe.SIDE; col++) {
                buttons[row][col].setEnabled(enabled);
            }
        }
    }


    private class PlayDialog implements DialogInterface.OnClickListener {

        public void onClick(DialogInterface dialog , int id) {

            if(id == -1)
            {
                tttGame.resetGame();
                enableButtons(true);
                resetButtons();
                status.setBackgroundColor(Color.GREEN);
                status.setText(tttGame.result());
            }

            else if(id == -2)
            {
                MainActivity.this.finish();
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
