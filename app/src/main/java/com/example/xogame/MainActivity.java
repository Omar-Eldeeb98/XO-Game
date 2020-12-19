package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button [][] buttons = new Button[3][3] ;  // the array buttons => 9 buttons of the game.
    private boolean player1_turn = true;


    private int round_count;

    private int player1_points;
    private int player2_points;

    private TextView textView_player1;
    private TextView textView_player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        textView_player1 = (TextView) findViewById( R.id.t_v_p_1 );
        textView_player2 = (TextView) findViewById( R.id.t_v_p_2 );

        for (int i = 0 ; i<3 ; i++)
        {
            for (int j = 0 ; j<3 ; j++ )
            {
                String button_id = "btn_" + i + j;
                int res_id = getResources().getIdentifier( button_id  , "id" , getPackageName());
                buttons[i][j] = findViewById( res_id );
                buttons[i][j].setOnClickListener( this );
            }
        }

        Button button_reset = (Button) findViewById( R.id.btn_Reset);
        button_reset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetGame();



            }
        } );


    }

    @Override
    public void onClick(View v) {
        if (!((Button) v ).getText().toString().equals( "" ))
        {
            return;
        }
        if (player1_turn)
        {
            ((Button)v).setText( "X" );
            ((Button)v).setTextSize( 60 );
            ((Button)v).setTextColor(   Color.parseColor("#B63309"));

        }


        else
        {
            ((Button)v).setText( "O" );
            ((Button)v).setTextSize( 60 );
            ((Button)v).setTextColor(   Color.parseColor("#693CB8"));

        }

        round_count++;

        if (check_winner())
        {
            if (player1_turn)
            {
                player1_wins();
            }
            else
            {
                player2_wins();
            }
        }
        else if (round_count == 9 )
        {
            draw();
        }
        else
        {
            player1_turn = !player1_turn;
        }
    }



    private boolean check_winner()
    {
        String[][] field = new String[3][3];
        for (int i = 0 ; i<3 ; i++)
        {
            for (int j = 0 ; j<3 ; j++ )
            {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i=0;i<3;i++)
        {
            if (field[i][0].equals( field[i][1] )
                    &&field[i][0].equals( field[i][2] )
                    && !field[i][0].equals(""))
            {
                return true;

            }
        }

        for (int i=0;i<3;i++)
        {
            if (field[0][i].equals( field[1][i] )
                    &&field[0][i].equals( field[2][i] )
                    && !field[0][i].equals(""))
            {
                return true;

            }
        }

        if (field[0][0].equals( field[1][1] )
                &&field[0][0].equals( field[2][2] )
                && !field[0][0].equals(""))
        {
            return true;

        }


        if (field[0][2].equals( field[1][1] )
                &&field[0][2].equals( field[2][0] )
                && !field[0][2].equals(""))
        {
            return true;

        }

        return false;

    }

    private void draw()
    {

        Toast.makeText( this , "Draw!" , Toast.LENGTH_SHORT).show();

        Runnable r = new Runnable() {
            @Override
            public void run() {

                resetBord();
            }
        };

        Handler h =new Handler();
        h.postDelayed(r,1000);


    }

    private void player1_wins()
    {

        player1_points++;
        Toast.makeText( this , "Player 1 Wins!" , Toast.LENGTH_SHORT).show();
        updatePointText();

        Runnable r = new Runnable() {
            @Override
            public void run() {

                resetBord();
            }
        };

        Handler h =new Handler();
        h.postDelayed(r,1000);



    }
    private void player2_wins()
    {
        player2_points++;
        Toast.makeText( this , "Player 2 Wins!" , Toast.LENGTH_SHORT).show();
        updatePointText();

        Runnable r = new Runnable() {
            @Override
            public void run() {

                resetBord();
            }
        };

        Handler h =new Handler();
        h.postDelayed(r,1000);



    }
    private void updatePointText()
    {
        textView_player1.setText( "PLAYER 1: " + player1_points );
        textView_player2.setText( "PLAYER 2: " + player2_points );

    }
    private void resetBord()
    {

        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                buttons[i][j].setText( "" );
            }

        }

        round_count = 0;
        player1_turn = true;

    }

    private void resetGame()
    {
        player1_points = 0;
        player2_points = 0;
        updatePointText();
        resetBord();
        Toast.makeText( this , "Game Is Restarted!" , Toast.LENGTH_SHORT).show();
    }


}




