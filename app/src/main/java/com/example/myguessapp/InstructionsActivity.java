package com.example.myguessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
    }

    public void toMainMenu(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}