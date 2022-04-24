package com.example.mathlearninggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button_add, button_multi, button_div, button_sub, button_mixed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_add = findViewById(R.id.button_make_a_wish);
        button_div = findViewById(R.id.button_time_trial);
        button_multi = findViewById(R.id.button_take_chances);
        button_mixed = findViewById(R.id.button_mixed);
        button_sub = findViewById(R.id.button_no_mistake);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OptionQuiz.class);
                intent.putExtra("task", "addition");
                startActivity(intent);
            }
        });

        button_sub.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OptionQuiz.class);
            intent.putExtra("task", "subtraction");
            startActivity(intent);
        });

        button_multi.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OptionQuiz.class);
            intent.putExtra("task", "multiplication");
            startActivity(intent);
        });

        button_div.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OptionQuiz.class);
            intent.putExtra("task", "division");
            startActivity(intent);
        });

        button_mixed.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OptionQuiz.class);
            intent.putExtra("task", "mixed");
            startActivity(intent);
        });


    }


}