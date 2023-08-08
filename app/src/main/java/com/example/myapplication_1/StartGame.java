package com.example.myapplication_1;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class StartGame extends AppCompatActivity {

    TextView tvTimer;
    TextView tvResult;
    ImageView ivShowImage;
    HashMap<String, Integer> map = new HashMap<>();
    String[] techList = {
            "Argentina",
            "Bangladesh",
            "Brazil",
            "Canada",
            "China",
            "France",
            "Germany",
            "India",
            "Ireland",
            "Japan",
            "Netherlands",
            "Russia",
            "Scotland",
            "Uganda",
            "Ukraine",
            "USA"
    };

    int index;
    Button btn1, btn2, btn3;
    TextView tvPoints;
    int points;
    CountDownTimer countDownTimer;
    long millisUntilFinished;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);

        tvTimer = findViewById(R.id.tvTimer);
        tvResult = findViewById(R.id.tvResult);
        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        tvPoints = findViewById(R.id.tvPoints);

        index = 0;

        map.put(techList[0], R.drawable.argentina);
        map.put(techList[1], R.drawable.bangladesh);
        map.put(techList[2], R.drawable.brazil);
        map.put(techList[3], R.drawable.canada);
        map.put(techList[4], R.drawable.china);
        map.put(techList[5], R.drawable.france);
        map.put(techList[6], R.drawable.germany);
        map.put(techList[7], R.drawable.india);
        map.put(techList[8], R.drawable.ireland);
        map.put(techList[9], R.drawable.japan);
        map.put(techList[10], R.drawable.netherlands);
        map.put(techList[11], R.drawable.russia);
        map.put(techList[12], R.drawable.scotland);
        map.put(techList[13], R.drawable.uganda);
        map.put(techList[14], R.drawable.ukraine);
        map.put(techList[15], R.drawable.usa);

        Collections.shuffle(Arrays.asList(techList));
        millisUntilFinished = 12000;

        points = 0;
        startGame();
    }

    private void startGame() {
        // Initialize millisUntilFinished with 10 seconds.
        millisUntilFinished = 12000;

        tvTimer.setText("" + (millisUntilFinished / 1000) + "s");

        tvPoints.setText(points + " / " + 16);

        generateQuestions(index);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerSelected(view);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerSelected(view);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerSelected(view);
            }
        });



        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                index++;

                if (index > 15){
                    // If true, hide the ImageView and Buttons.
                    ivShowImage.setVisibility(View.GONE);
                    btn1.setVisibility(View.GONE);
                    btn2.setVisibility(View.GONE);
                    btn3.setVisibility(View.GONE);

                    Intent intent = new Intent(StartGame.this, GameOver.class);
                    intent.putExtra("points", points);
                    startActivity(intent);

                } else {

                    countDownTimer = null;
                    startGame();
                }
            }
        }.start();
    }

    private void generateQuestions(int index) {

        String[] techListTemp =  techList.clone();

        String correctAnswer = techList[index];


        List<String> list = new ArrayList<String>(Arrays.asList(techListTemp));
        list.remove(correctAnswer);
        techListTemp = list.toArray(new String[0]);

        Collections.shuffle(Arrays.asList(techListTemp));

        String[] newList = {
                techListTemp[0],
                techListTemp[1],
                correctAnswer
        };


        Collections.shuffle(Arrays.asList(newList));

        btn1.setText(newList[0]);
        btn2.setText(newList[1]);
        btn3.setText(newList[2]);

        ivShowImage.setImageResource(map.get(techList[index]));
    }

    public void nextQuestion(View view) {

        btn1.setBackgroundColor(Color.parseColor("#082847"));
        btn2.setBackgroundColor(Color.parseColor("#082847"));
        btn3.setBackgroundColor(Color.parseColor("#082847"));

        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);

        countDownTimer.cancel();
        index++;

        if (index > 15){

            Intent intent = new Intent(StartGame.this, GameOver.class);
            intent.putExtra("points", points);
            startActivity(intent);

        } else {
            countDownTimer = null;
            tvResult.setText("");
            startGame();
        }
    }

    public void answerSelected(View view) {

        view.setBackgroundColor(Color.parseColor("#17243e"));

        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);

        countDownTimer.cancel();

        String answer = ((Button) view).getText().toString().trim();

        String correctAnswer = techList[index];

        String b1_text, b2_text;

        b1_text = btn1.getText().toString();
        b2_text = btn2.getText().toString();

        if (b1_text.equals(correctAnswer)){
            btn1.setBackgroundColor(Color.parseColor("#6FD904"));
        }else if(b2_text.equals(correctAnswer)){
            btn2.setBackgroundColor(Color.parseColor("#6FD904"));
        }else{
            btn3.setBackgroundColor(Color.parseColor("#6FD904"));
        }

        if(answer.equals(correctAnswer)){
            points++;
            tvPoints.setText(points + " / " + 16);

        } else {
            ((Button) view).setBackgroundColor(Color.parseColor("#E30000"));
        }
    }
}