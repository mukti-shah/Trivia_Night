package com.example.myapplication_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    TextView tvPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        int points = getIntent().getExtras().getInt("points");

        tvPoints = findViewById(R.id.tvPoints);

        tvPoints.setText("Points: " + points);

    }


    public void home(View view) {
        Intent intent = new Intent(GameOver.this, ChoicePage.class);
        startActivity(intent);
    }
}
