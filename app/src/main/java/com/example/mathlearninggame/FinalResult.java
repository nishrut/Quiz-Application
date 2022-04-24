package com.example.mathlearninggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FinalResult extends AppCompatActivity {

    TextView result;
    Button button_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        result = findViewById(R.id.textView_result);
        button_home = findViewById(R.id.button_home);

        ArrayList<String> historyQuestion = (ArrayList<String>) getIntent().getSerializableExtra("historyQuestion");
        ArrayList<String> historyAnswer = (ArrayList<String>) getIntent().getSerializableExtra("historyAnswer");

        int score = getIntent().getIntExtra("score", 0);
        long time = getIntent().getLongExtra("time", 0);

        result.setText("---!!GAME OVER!!---\n\nYour Score: " + score + "\nTime taken: " + time / 1000 + " Seconds\n");
        Log.d("mobi", historyQuestion.toString() + " historyQuestion");
        Log.d("mobi", historyAnswer.toString() + " historyAnswer");
        for (int i = 0; i < historyAnswer.size(); i++) {
            result.append(historyQuestion.get(i) + historyAnswer.get(i) + "\n");
        }

        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalResult.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}