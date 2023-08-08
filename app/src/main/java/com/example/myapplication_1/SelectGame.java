package com.example.myapplication_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.content.Intent;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SelectGame extends AppCompatActivity {

    int pressCounter = 0;
    int[] noOfLetters = {5,5,4,6,6,8,6,5,8,7,4,5,7,9,6,4};
    int maxPress;

    String[][] keys_1 = {
            {"A","D","O","B","E","T","D"},
            {"A","P","P","L","E","J","D"},
            {"A","U","D","I","C","S","J"},
            {"C","H","A","N","E","L","S"},
            {"D","I","S","N","E","Y","I"},
            {"F","A","C","E","B","O","O","K","D"},
            {"G","I","T","H","U","B","S","H"},
            {"G","U","C","C","I","Y","H"},
            {"M","E","R","C","E","D","E","S","I"},
            {"N","E","T","F","L","I","X","I"},
            {"N","I","K","E","Y","A","K"},
            {"P","E","P","S","I","C","O"},
            {"R","E","D","B","U","L","L","I"},
            {"S","T","A","R","B","U","C","K","S","A","K"},
            {"S","W","I","G","G","Y","Z","O"},
            {"U","B","E","R","O","L","A"}
    };

    int[] index_no = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

    String col = "#082847";

    String[] options = new String[5];

    String[] textAnswer = {
            "ADOBE",
            "APPLE",
            "AUDI",
            "CHANEL",
            "DISNEY",
            "FACEBOOK",
            "GITHUB",
            "GUCCI",
            "MERCEDES",
            "NETFLIX",
            "NIKE",
            "PEPSI",
            "REDBULL",
            "STARBUCKS",
            "SWIGGY",
            "UBER"
    };
    ImageView imgView;

    int image[] = {
            R.drawable.adobe,
            R.drawable.apple,
            R.drawable.audi,
            R.drawable.chanel,
            R.drawable.disney,
            R.drawable.facebook,
            R.drawable.github,
            R.drawable.gucci,
            R.drawable.mercedes,
            R.drawable.netflix,
            R.drawable.nike,
            R.drawable.pepsi,
            R.drawable.redbull1,
            R.drawable.starbucks,
            R.drawable.swiggy,
            R.drawable.uber,
    };

    String answer;
    ImageButton undo;

    Button btn;


    int index=0;
    int points = 0;

    LinearLayout ll;
    EditText txt;

    TextView points_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);


        imgView = findViewById(R.id.imageView);
        ll= findViewById(R.id.option);
        txt = findViewById(R.id.editText);
        undo = findViewById(R.id.undo);

        btn = findViewById(R.id.btn_next);

        points_tv = findViewById(R.id.points);

        Random rand = new Random();

        for (int i = 0; i < index_no.length; i++) {
            int rnd_ind = rand.nextInt(index_no.length);
            int temp = index_no[rnd_ind];
            index_no[rnd_ind] = index_no[i];
            index_no[i] = temp;
        }

        answer = textAnswer[index_no[index]];
        options = keys_1[index_no[index]];
        imgView.setImageResource(image[index_no[index]]);

        Collections.shuffle(Arrays.asList(options));;

        for (String key : options) {
            addView(ll, key, txt);
        }

        maxPress = noOfLetters[index_no[index]];

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pressCounter!=0){

                    pressCounter--;
                    String word = txt.getText().toString();

                    char lastChar = word.charAt(word.length()-1);

                    word= word.substring(0, word.length() - 1);


                    txt.setText("");
                    txt.setText(txt.getText().toString()+word);
                    

                    addView(ll, String.valueOf(lastChar),txt);
                }
            }
        });

    }



    public void addView(LinearLayout parent_lay, String text, EditText editText) {

        TextView textView = new TextView(this);
        
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);

        textView.setTextSize(32);

        textView.setBackgroundColor(Color.parseColor(col));
        textView.setTextColor(Color.parseColor("#ffffff"));


        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pressCounter < maxPress) {
                    if (pressCounter == 0){
                        editText.setText("");}

                    editText.setText(editText.getText().toString() + text);

                    textView.setVisibility(View.GONE) ;

                    pressCounter++;

                    if (pressCounter == maxPress){
                        doValidate();}
                }
            }
        });


        parent_lay.addView(textView);

        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
        textView.setLayoutParams(layoutParams);
    }


    public void doValidate() {
        pressCounter = 0;

        if(txt.getText().toString().equals(answer)) {
            points++;
            points_tv.setText(points + " / " + 16);
            Toast toast = Toast.makeText(this, "Correct Answer", Toast.LENGTH_LONG);
            toast.show();
            (new Handler()).postDelayed(this::nextQuestion, 2000);
        }
        else{
            Toast toast = Toast.makeText(this, "Wrong Answer. Right: "+answer, Toast.LENGTH_LONG);
            toast.show();
            (new Handler()).postDelayed(this::nextQuestion, 2000);
        }

        }


    public void nextQuestion(){
            if (index<15){
                index++;
            }else{
                Intent intent = new Intent(getApplicationContext(), GameOver.class);
                intent.putExtra("points", points);
                startActivity(intent);
            }

            answer = textAnswer[index_no[index]];
            options = keys_1[index_no[index]];
            maxPress = noOfLetters[index_no[index]];

            Collections.shuffle(Arrays.asList(options));
            ll.removeAllViews();

            imgView.setImageResource(image[index_no[index]]);
            for (String key : options) {
                addView(ll, key, txt);
            }

            txt.setText("");
        }
}





