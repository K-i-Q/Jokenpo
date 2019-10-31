package com.example.jokenpo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int jogador = new Random().nextInt();
    public static int counterComputerWinners = 0;
    public static int counterPlayerWinners = 0;
    public static int counterTies = 0;
    public String latitude;
    public String longitude;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = "" + location.getLongitude();
                latitude = "" + location.getLatitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        ActivityCompat.requestPermissions(this, permissions, 1);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, locationListener);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("jokenpo");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren() ){
                    Integer jogadorRemoto = data.child("jogador").getValue(Integer.class);
                    Integer escolha = data.child("escolha").getValue(Integer.class);

                    ImageButton btnPlayer = findViewById(R.id.btnPlayer);
                    ImageButton btnComputer = findViewById(R.id.btnComputer);
                    if (jogadorRemoto.equals(jogador)){
                        btnPlayer.setImageResource(escolha);
                    }else{
                        btnComputer.setImageResource(escolha);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void choose(View view){
        ImageButton btnPlayer = findViewById(R.id.btnPlayer);

        Jogada jogada = new Jogada();
        jogada.setJogador(jogador);
        jogada.setLatitude(latitude);
        jogada.setLongitude(longitude);


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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("jokenpo");
        myRef.push().setValue(jogada);
    }
    public void play(View view){

        ImageButton btnComputer = findViewById(R.id.btnComputer);
        ImageButton btnPlayer = findViewById(R.id.btnPlayer);
        ImageView trofeu = findViewById(R.id.imgTrofeu);

        int random = new Random().nextInt(3);

        TextView textInputResult = findViewById(R.id.textInputResult);
        TextView textInputCounterComputer = findViewById(R.id.textInputCounterComputer);
        TextView textInputCounterPlayer = findViewById(R.id.textInputCounterPlayer);
        TextView textInputDraw = findViewById(R.id.textInputDraw);


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
            counterTies++;
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
        textInputDraw.setText(String.valueOf(counterTies));

        if (counterPlayerWinners == 10){
            trofeu.setVisibility(View.VISIBLE);
        }


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
        ImageView trofeu = findViewById(R.id.imgTrofeu);

        if(view.getId() == R.id.btnReset){
            btnComputer.setImageResource(R.drawable.vazio);
            btnPlayer.setImageResource(R.drawable.vazio);
            textInputResult.setText("");
            textInputCounterPlayer.setText("");
            textInputCounterComputer.setText("");
            counterComputerWinners = 0;
            counterPlayerWinners = 0;
            counterTies = 0;
            trofeu.setVisibility(View.INVISIBLE);
        }
    }
}
