package com.example.myguessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button goToCreateGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToCreateGame = (Button) findViewById(R.id.playButton);

        goToCreateGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateGameActivity.class);
                startActivity(i);
            }
        });
    }



    public void goToInstructions(View v) {
        Intent i = new Intent(this, InstructionsActivity.class);
        startActivity(i);
    }


}