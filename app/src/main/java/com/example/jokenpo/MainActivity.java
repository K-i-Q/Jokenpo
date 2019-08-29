package com.example.jokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static int counterComputerWinners = 0;
    public static int counterPlayerWinners = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void choose(View view){
        ImageButton btnPlayer = findViewById(R.id.btnPlayer);

        if(view.getId() == R.id.btnPaper){
            btnPlayer.setImageResource(R.drawable.papel);
            btnPlayer.setTag("papel");
        }
        if(view.getId() == R.id.btnRock){
            btnPlayer.setImageResource(R.drawable.pedra);
            btnPlayer.setTag("pedra");
        }
        if(view.getId() == R.id.btnScissors){
            btnPlayer.setImageResource(R.drawable.tesoura);
            btnPlayer.setTag("tesoura");
        }
    }
    public void play(View view){

        ImageButton btnComputer = findViewById(R.id.btnComputer);
        ImageButton btnPlayer = findViewById(R.id.btnPlayer);

        int random = new Random().nextInt(3);

        TextView textInputResult = findViewById(R.id.textInputResult);
        TextView textInputCounterComputer = findViewById(R.id.textInputCounterComputer);
        TextView textInputCounterPlayer = findViewById(R.id.textInputCounterPlayer);


        if(view.getId() == R.id.btnStart){
            switch (random){
                case 0:
                    btnComputer.setImageResource(R.drawable.papel);
                    btnComputer.setTag("papel");
                    break;
                case 1:
                    btnComputer.setImageResource(R.drawable.pedra);
                    btnComputer.setTag("pedra");
                    break;
                case 2:
                    btnComputer.setImageResource(R.drawable.tesoura);
                    btnComputer.setTag("tesoura");
                    break;
                default:
                    break;
            }
        }

        if(btnComputer.getTag() ==  "tesoura" && btnPlayer.getTag() ==  "papel"){
            //computer win
            textInputResult.setText("Computer WIN");
            counterComputerWinners++;
        }
        if(btnComputer.getTag() ==  "papel" && btnPlayer.getTag() ==  "pedra"){
            //computer win
            textInputResult.setText("Computer WIN");
            counterComputerWinners++;
        }
        if(btnComputer.getTag() ==  "pedra" && btnPlayer.getTag() ==  "tesoura"){
            //computer win
            textInputResult.setText("Computer WIN");
            counterComputerWinners++;
        }

        if(btnComputer.getTag() ==  btnPlayer.getTag() ){
            //Empate
            textInputResult.setText("DRAW");
        }

        if(btnComputer.getTag() ==  "pedra" && btnPlayer.getTag() ==  "papel"){
            //player win
            textInputResult.setText("You WIN");
            counterPlayerWinners++;
        }
        if(btnComputer.getTag() ==  "tesoura" && btnPlayer.getTag() ==  "pedra"){
            //player win
            textInputResult.setText("You WIN");
            counterPlayerWinners++;
        }
        if(btnComputer.getTag() ==  "papel" && btnPlayer.getTag() ==  "tesoura"){
            //player win
            textInputResult.setText("You WIN");
            counterPlayerWinners++;
        }

        textInputCounterComputer.setText(String.valueOf(counterComputerWinners));
        textInputCounterPlayer.setText(String.valueOf(counterPlayerWinners));


    }
    public void clear(View view){

        TextView textInputResult = findViewById(R.id.textInputResult);
        ImageButton btnComputer = findViewById(R.id.btnComputer);
        ImageButton btnPlayer = findViewById(R.id.btnPlayer);

        if(view.getId() == R.id.btnClear){
            btnComputer.setImageResource(R.drawable.vazio);
            btnPlayer.setImageResource(R.drawable.vazio);
            textInputResult.setText("");
        }
    }
    public void reset(View view){

        TextView textInputResult = findViewById(R.id.textInputResult);
        TextView textInputCounterComputer = findViewById(R.id.textInputCounterComputer);
        TextView textInputCounterPlayer = findViewById(R.id.textInputCounterPlayer);
        ImageButton btnComputer = findViewById(R.id.btnComputer);
        ImageButton btnPlayer = findViewById(R.id.btnPlayer);

        if(view.getId() == R.id.btnReset){
            btnComputer.setImageResource(R.drawable.vazio);
            btnPlayer.setImageResource(R.drawable.vazio);
            textInputResult.setText("");
            textInputCounterPlayer.setText("");
            textInputCounterComputer.setText("");
            counterComputerWinners = 0;
            counterPlayerWinners = 0;
        }
    }
}
