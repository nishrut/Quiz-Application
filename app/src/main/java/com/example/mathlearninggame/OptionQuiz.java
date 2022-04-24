package com.example.mathlearninggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionQuiz extends AppCompatActivity {

    Button button_make_a_wish, button_no_mistake, button_take_chances, button_time_trial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_quiz);

        button_make_a_wish = findViewById(R.id.button_make_a_wish);
        button_no_mistake = findViewById(R.id.button_no_mistake);
        button_take_chances = findViewById(R.id.button_take_chances);
        button_time_trial = findViewById(R.id.button_time_trial);

        String choiceFromMainActivity = getIntent().getStringExtra("task");

        button_make_a_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionQuiz.this, DisplayQuestion.class);
                intent.putExtra("option", "make_a_wish");
                intent.putExtra("task", choiceFromMainActivity);
                startActivity(intent);
            }
        });


        button_no_mistake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionQuiz.this, DisplayQuestion.class);
                intent.putExtra("option", "no_mistake");
                intent.putExtra("task", choiceFromMainActivity);
                startActivity(intent);
            }
        });

        button_take_chances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionQuiz.this, DisplayQuestion.class);
                intent.putExtra("option", "take_chances");
                intent.putExtra("task", choiceFromMainActivity);
                startActivity(intent);
            }
        });


    }
}