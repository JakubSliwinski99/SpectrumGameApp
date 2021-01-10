package com.example.myguessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    ProgressBar correctBar;
    ProgressBar guessBar;
    int guessLevel;
    int correctLevel;
    Game currentGame;

    int gainedPoints;
    TextView congrats;
    TextView bottomMessage;

    Button nextTurnButton;

    String nextTurn = "Next Turn";
    String playAgain = "Play again";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        currentGame = (Game) intent.getSerializableExtra("game");

        correctBar = (ProgressBar) findViewById(R.id.correctBar);
        guessBar = (ProgressBar) findViewById(R.id.guessBar);
        guessLevel = intent.getIntExtra("guessedLevel", 0);
        correctLevel = currentGame.getCorrectAnswer();

        correctBar.setProgress(correctLevel);
        guessBar.setProgress(guessLevel);

        //Points calculations

        gainedPoints = currentGame.calculatePoints(guessLevel);
        currentGame.updatePointsToActiveTeam(gainedPoints);
        currentGame.updateTeamValues();


        //Congratulations text

        congrats = (TextView) findViewById(R.id.congratsText);
        String congratsMessage = "Congratulations team " + currentGame.getActiveTeamName() + " scored " + Integer.toString(gainedPoints) + " points!";
        String currentPoints = " You now have " + currentGame.getActiveTeamPoints() + " points!";
        congrats.setText(congratsMessage + currentPoints);

        //End game Check
        bottomMessage = (TextView) findViewById(R.id.bottomMessage);

        if(currentGame.endGameCheck()) {
            nextTurnButton = (Button) findViewById(R.id.nextTurnButton);
            String endGameMessage = "GAME OVER! Team " + currentGame.getActiveTeamName() + " won!";
            bottomMessage.setText(endGameMessage);
            nextTurnButton.setText("Play again");
            nextTurnButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (ResultActivity.this, MainActivity.class);
                    startActivity(intent);
                    System.gc();
                }
            });

        } else {
            nextTurnButton = (Button) findViewById(R.id.nextTurnButton);
            bottomMessage.setText(R.string.next_round_text);

            nextTurnButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentGame.changeActiveTeam();
                    currentGame.drawNewCategories();
                    currentGame.drawNewCorrectAnswer();
                    Log.d("test3", currentGame.toString());

                    Intent intent = new Intent(ResultActivity.this, CategoryShowActivity.class);
                    intent.putExtra("game", currentGame);
                    startActivity(intent);
                }
            });
        }
    }
}