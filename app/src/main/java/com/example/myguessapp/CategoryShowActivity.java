package com.example.myguessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CategoryShowActivity extends AppCompatActivity {

    Button readyButton;

    Game currentGame;
    TextView topCategory;
    TextView bottomCategory;
    TextView header;
    String category1;
    String category2;
    int level;
    ProgressBar levelShower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_show);

        Intent intent = getIntent();
        currentGame = (Game) intent.getSerializableExtra("game");

        topCategory = (TextView) findViewById(R.id.category1guess);
        bottomCategory = (TextView) findViewById(R.id.category2guess);
        header = (TextView) findViewById(R.id.categoryShowHeader);

        category1 = currentGame.getRound().getCurrentCategories().get(0).toString();
        category2 = currentGame.getRound().getCurrentCategories().get(1).toString();

        String headerMessage = "Team " + currentGame.getActiveTeamName() + " turn!";
        header.setText(headerMessage);

        level = currentGame.getRound().getCorrectAnswer();
        levelShower = (ProgressBar) findViewById(R.id.levelGuesser);

        topCategory.setText(category1);
        bottomCategory.setText(category2);
        levelShower.setProgress(level);

        //Button handler
        readyButton = (Button) findViewById(R.id.readyButton);

        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test1", currentGame.toString());
                Intent intent = new Intent(CategoryShowActivity.this, GuessActivity.class);
                intent.putExtra("game", currentGame);
                startActivity(intent);
            }
        });
    }
}