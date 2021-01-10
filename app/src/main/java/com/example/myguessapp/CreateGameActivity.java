package com.example.myguessapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myguessapp.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateGameActivity extends AppCompatActivity {

    // Point cap selector variables
    Button selectPointCapButton;
    TextView pointCapText;
    String[] pointCaps;
    String selectedCap;
    int finalPointCap;

    //Team names variables
    Button start;
    String team1;
    String team2;

    //Categories
    List categories;

    private int selectPointCapInt(String selectedString) {
        String s = selectedString;
        int result;
        switch (s) {
            case "10 points":
                result = 10;
                break;
            case "15 points":
                result = 15;
            break;
            case "20 points":
                result =  20;
            break;
            default:
                result =  12;
        }
        return result;
    }

    //CSV handler

    private List generateCategoriesList() throws IOException {
        InputStream is = getResources().openRawResource(R.raw.categories);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        ArrayList<String> tempCategories = new ArrayList<String>();

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                String[] pair = line.split(",");
                for(String cat : pair) {
                    tempCategories.add(cat);
                }
            }
        } catch (IOException e) {
            Log.wtf("ErrorReadingFile", "Something went wrong with parsing csv");
        }

        return (List) tempCategories;
    }

    //Set categories
    void setCategories(Game game) throws IOException {
        List categories = generateCategoriesList();
        int roof = categories.size() / 2;
        Random r = new Random();
        int index = r.nextInt(roof) * 2;
        Round temp = new Round(categories, categories.get(index).toString(), categories.get(index + 1).toString());
        game.setRound(temp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        // Point cap selector handler

        selectPointCapButton = (Button) findViewById(R.id.selectCapButton);
        pointCapText = (TextView) findViewById(R.id.selectedCapText);
        pointCaps = getResources().getStringArray(R.array.point_caps);

        selectPointCapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateGameActivity.this);
                builder.setTitle(R.string.select_cup);
                builder.setSingleChoiceItems(pointCaps, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedCap = pointCaps[which];
                        pointCapText.setText(selectedCap);
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // Start button handler

        start = (Button) findViewById(R.id.startButton);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team1 = ((EditText)findViewById(R.id.team1Name)).getText().toString();
                team2 = ((EditText)findViewById(R.id.team2Name)).getText().toString();
                finalPointCap = selectPointCapInt(selectedCap);

                Game newGame = new Game (team1, team2, finalPointCap);
                try {
                    setCategories(newGame);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("test0", newGame.toString());

                Intent intent = new Intent(CreateGameActivity.this, CategoryShowActivity.class);
                intent.putExtra("game", newGame);
                startActivity(intent);
            }
        });

    }
}