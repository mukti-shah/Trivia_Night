package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChoicePage extends AppCompatActivity {

    ImageButton btn_1, btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_page);

        btn_1 = findViewById(R.id.game_1);
        btn_2 = findViewById(R.id.game_2);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoicePage.this, StartGame.class);
                startActivity(intent);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoicePage.this, SelectGame.class);
                startActivity(intent);
            }
        });
    }
}