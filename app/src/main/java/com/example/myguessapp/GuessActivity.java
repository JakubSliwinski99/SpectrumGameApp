package com.example.myguessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class GuessActivity extends AppCompatActivity {

    Button guessButton;

    Game currentGame;
    TextView topCategory;
    TextView bottomCategory;
    TextView header;
    String category1;
    String category2;
    int levelGuess;

    SeekBar levelGuesser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        Intent intent = getIntent();
        currentGame = (Game) intent.getSerializableExtra("game");

        topCategory = (TextView) findViewById(R.id.category1guess);
        bottomCategory = (TextView) findViewById(R.id.category2guess);
        header = (TextView) findViewById(R.id.categoryShowHeader);

        category1 = currentGame.getRound().getCurrentCategories().get(0).toString();
        category2 = currentGame.getRound().getCurrentCategories().get(1).toString();
        topCategory.setText(category1);
        bottomCategory.setText(category2);

        String headerMessage = "Team " + currentGame.getActiveTeamName() + " turn!";
        header.setText(headerMessage);

        //Button handler
        guessButton = (Button) findViewById(R.id.guessButton);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test2", currentGame.toString());
                levelGuesser = (SeekBar) findViewById(R.id.guessSlider);
                levelGuess = levelGuesser.getProgress();
                Intent intent = new Intent(GuessActivity.this, ResultActivity.class);
                intent.putExtra("game", currentGame);
                intent.putExtra("guessedLevel", levelGuess);
                startActivity(intent);
            }
        });
    }
}