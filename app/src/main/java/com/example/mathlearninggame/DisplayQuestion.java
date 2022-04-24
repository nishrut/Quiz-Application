package com.example.mathlearninggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class DisplayQuestion extends AppCompatActivity {

    Button button_submit;
    TextView textView_question, textViewLives;
    EditText editTextNumber;
    Random random = new Random();
    int number1, number2;
    int rand = 0;
    String checkTask;
    String checkTask2;
    String checkOption;
    int user_wish;
    int score = 0;
    int user_lives = 3;
    boolean user_no_mistake_answer = true;
    ArrayList<String> historyQuestion = new ArrayList<>();
    ArrayList<String> historyAnswer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_question);

        long startTime = System.currentTimeMillis();


//        button_next = findViewById(R.id.button_next);
        button_submit = findViewById(R.id.button_submit);
        textView_question = findViewById(R.id.textView_question);
        editTextNumber = findViewById(R.id.editTextNumber);
        textViewLives = findViewById(R.id.textViewLives);

        String task = getIntent().getStringExtra("task");
        String option = getIntent().getStringExtra("option");

        if (option.equals("make_a_wish")) {
            makeAWish();
        } else if (option.equals("no_mistake")) {
            checkOption = option;
            checkTask2 = task;
            noMistake();
        } else if (option.equals("take_chances")) {
            textViewLives.setText(user_lives + " Lives");
            Log.d("mobi", option + " option");
            checkOption = "take_chances";
            checkTask2 = "mixed";
            takeChances();
        }


        if (task.equals("addition"))
            checkTask = "addition";
        else if (task.equals("subtraction"))
            checkTask = "subtraction";
        else if (task.equals("multiplication"))
            checkTask = "multiplication";
        else if (task.equals("division"))
            checkTask = "division";
        else if (task.equals("mixed")) {
            checkTask = "mixed";
        }

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextNumber.getText().toString().length() <= 0) {
                    textView_question.append("\n\nPlease enter the valid option");

                } else {

                    if (checkOption.equals("take_chances")) {
                        if (user_lives == 0) {
                            long difference = System.currentTimeMillis() - startTime;
                            Intent home = new Intent(DisplayQuestion.this, MainActivity.class);
                            startActivity(home);
                            Intent finalIntent = new Intent(DisplayQuestion.this, FinalResult.class);
                            finalIntent.putExtra("score", score);
                            finalIntent.putExtra("time", difference);
                            finalIntent.putExtra("historyQuestion", historyQuestion);
                            finalIntent.putExtra("historyAnswer", historyAnswer);
                            startActivity(finalIntent);
                        } else {
                            Log.d("mobi", checkOption);
                            textViewLives.setText(user_lives + " Lives");
                            button_submit.setText("Submit answer");
                            float user_answer = Float.parseFloat(editTextNumber.getText().toString());
                            float actual_answer = 1;
                            if (checkTask.equals("addition")) {
                                actual_answer = number1 + number2;
                                additionQuestion();
                            } else if (checkTask.equals("subtraction")) {
                                if (number1 >= number2)
                                    actual_answer = number1 - number2;
                                else
                                    actual_answer = number2 - number1;
                                subtractionQuestion();
                            } else if (checkTask.equals("multiplication")) {
                                actual_answer = number1 * number2;
                                multiplicationQuestion();
                            } else if (checkTask.equals("division")) {
                                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                                actual_answer = Float.parseFloat(decimalFormat.format((float) number1 / number2));
                                divisionQuestion();
                            } else if (checkTask.equals("mixed")) {
                                mixedQuestion();
                            }
                            if (user_answer == actual_answer) {
                                historyAnswer.add(user_answer + " : (Correct)");
                                textView_question.setText("Correct Answer!!");
                                score++;
                                textViewLives.setText(user_lives + " Lives");

                            } else {
                                historyAnswer.add(user_answer + " : (Wrong)");
                                user_lives--;
                                textView_question.setText("Incorrect Answer");
                                textViewLives.setText(user_lives + " Lives");
                            }
                        }
                    } else if (checkOption.equals("make_a_wish")) {
                        Log.d("mobi", checkOption);

                        user_wish = Integer.parseInt(editTextNumber.getText().toString());
                        checkOption = "completed_make_a_wish";
                        button_submit.setText("Submit answer");

                    } else if (checkOption.equals("completed_make_a_wish")) {
                        Log.d("mobi", checkOption);

                        if (user_wish > 1) {
                            float user_answer = Float.parseFloat(editTextNumber.getText().toString());
                            float actual_answer = 1;
                            if (checkTask.equals("addition")) {
                                actual_answer = number1 + number2;
                                additionQuestion();
                            } else if (checkTask.equals("subtraction")) {
                                if (number1 >= number2)
                                    actual_answer = number1 - number2;
                                else
                                    actual_answer = number2 - number1;
                                subtractionQuestion();
                            } else if (checkTask.equals("multiplication")) {
                                actual_answer = number1 * number2;
                                multiplicationQuestion();
                            } else if (checkTask.equals("division")) {
                                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                                actual_answer = Float.parseFloat(decimalFormat.format((float) number1 / number2));
                                divisionQuestion();
                            } else if (checkTask.equals("mixed")) {
                                mixedQuestion();
                            }
                            if (user_answer == actual_answer) {
                                historyAnswer.add(user_answer + " : (Correct)");
                                textView_question.setText("Correct Answer!!");
                                score++;

                            } else {
                                historyAnswer.add(user_answer + " : (Wrong)");
                                textView_question.setText("Incorrect Answer");
                            }
                            user_wish--;
                        } else {

                            long difference = System.currentTimeMillis() - startTime;
                            Intent home = new Intent(DisplayQuestion.this, MainActivity.class);
                            startActivity(home);
                            Intent finalIntent = new Intent(DisplayQuestion.this, FinalResult.class);
                            finalIntent.putExtra("score", score);
                            finalIntent.putExtra("time", difference);
                            finalIntent.putExtra("historyQuestion", historyQuestion);
                            finalIntent.putExtra("historyAnswer", historyAnswer);
                            startActivity(finalIntent);
                        }

                    } else if (checkOption.equals("no_mistake")) {
                        Log.d("mobi", checkOption);

                        Log.d("mobi", checkTask);
                        button_submit.setText("Submit answer");
                        float user_answer = Float.parseFloat(editTextNumber.getText().toString());
                        float actual_answer = 1;
                        if (checkTask.equals("addition")) {
                            Log.d("mobi", "In the no_task else if addition");
                            actual_answer = number1 + number2;
                            if (actual_answer != user_answer) {
                                long difference = System.currentTimeMillis() - startTime;
                                Intent home = new Intent(DisplayQuestion.this, MainActivity.class);
                                startActivity(home);
                                Intent finalIntent = new Intent(DisplayQuestion.this, FinalResult.class);
                                finalIntent.putExtra("score", score);
                                finalIntent.putExtra("time", difference);
                                finalIntent.putExtra("historyQuestion", historyQuestion);
                                finalIntent.putExtra("historyAnswer", historyAnswer);
                                startActivity(finalIntent);
                            }
                            additionQuestion();
                        } else if (checkTask.equals("subtraction")) {
                            if (number1 >= number2)
                                actual_answer = number1 - number2;
                            else
                                actual_answer = number2 - number1;
                            if (actual_answer != user_answer) {
                                historyAnswer.add(user_answer + " : (Wrong)");
                                long difference = System.currentTimeMillis() - startTime;
                                Intent home = new Intent(DisplayQuestion.this, MainActivity.class);
                                startActivity(home);
                                Intent finalIntent = new Intent(DisplayQuestion.this, FinalResult.class);
                                finalIntent.putExtra("score", score);
                                finalIntent.putExtra("time", difference);
                                finalIntent.putExtra("historyQuestion", historyQuestion);
                                finalIntent.putExtra("historyAnswer", historyAnswer);
                                startActivity(finalIntent);
                            } else {
                                historyAnswer.add(user_answer + " : (Correct)");
                            }
                            subtractionQuestion();
                        } else if (checkTask.equals("multiplication")) {
                            actual_answer = number1 * number2;
                            if (actual_answer != user_answer) {
                                historyAnswer.add(user_answer + " : (Wrong)");
                                long difference = System.currentTimeMillis() - startTime;
                                Intent home = new Intent(DisplayQuestion.this, MainActivity.class);
                                startActivity(home);
                                Intent finalIntent = new Intent(DisplayQuestion.this, FinalResult.class);
                                finalIntent.putExtra("score", score);
                                finalIntent.putExtra("time", difference);
                                finalIntent.putExtra("historyQuestion", historyQuestion);
                                finalIntent.putExtra("historyAnswer", historyAnswer);
                                startActivity(finalIntent);
                            } else {
                                historyAnswer.add(user_answer + " : (Correct)");
                            }
                            multiplicationQuestion();
                        } else if (checkTask.equals("division")) {
                            DecimalFormat decimalFormat = new DecimalFormat("0.00");
                            actual_answer = Float.parseFloat(decimalFormat.format((float) number1 / number2));
                            if (actual_answer != user_answer) {
                                historyAnswer.add(user_answer + " : (Wrong)");
                                long difference = System.currentTimeMillis() - startTime;
                                Intent home = new Intent(DisplayQuestion.this, MainActivity.class);
                                startActivity(home);
                                Intent finalIntent = new Intent(DisplayQuestion.this, FinalResult.class);
                                finalIntent.putExtra("score", score);
                                finalIntent.putExtra("time", difference);
                                finalIntent.putExtra("historyQuestion", historyQuestion);
                                finalIntent.putExtra("historyAnswer", historyAnswer);
                                startActivity(finalIntent);
                            } else {
                                historyAnswer.add(user_answer + " : (Correct)");
                            }
                            divisionQuestion();
                        } else if (checkTask.equals("mixed")) {
                            mixedQuestion();
                        }
                        if (user_answer == actual_answer) {
                            textView_question.setText("Correct Answer!!");
                            score++;

                        } else {
                            textView_question.setText("Incorrect Answer");
                            long difference = System.currentTimeMillis() - startTime;
                            Intent home = new Intent(DisplayQuestion.this, MainActivity.class);
                            startActivity(home);
                            Intent finalIntent = new Intent(DisplayQuestion.this, FinalResult.class);
                            finalIntent.putExtra("score", score);
                            finalIntent.putExtra("time", difference);
                            finalIntent.putExtra("historyQuestion", historyQuestion);
                            finalIntent.putExtra("historyAnswer", historyAnswer);
                            startActivity(finalIntent);
                        }


                    }

                    editTextNumber.setText("");
//                    button_submit.setVisibility(View.GONE);
                }

                if (task.equals("addition"))
                    additionQuestion();
                else if (task.equals("subtraction"))
                    subtractionQuestion();
                else if (task.equals("multiplication"))
                    multiplicationQuestion();
                else if (task.equals("division"))
                    divisionQuestion();
                else if (task.equals("mixed")) {
                    mixedQuestion();
                }

            }
        });

    }

    public void additionQuestion() {
        checkTask = "addition";
        number1 = random.nextInt(20);
        number2 = random.nextInt(20);
        historyQuestion.add(number1 + "+" + number2 + " = ");
        textView_question.setText("What is " + number1 + " + " + number2 + "?");
    }

    public void subtractionQuestion() {
        checkTask = "subtraction";
        number1 = random.nextInt(20);
        number2 = random.nextInt(20);

        // if n1 is also equal to n2
        if (number1 >= number2) {
            textView_question.setText("What is " + number1 + " - " + number2 + "?");
            historyQuestion.add(number1 + "-" + number2 + " = ");

        } else {
            historyQuestion.add(number2 + "-" + number1 + " = ");
            textView_question.setText("What is " + number2 + " - " + number1 + "?");
        }
    }

    public void multiplicationQuestion() {
        checkTask = "multiplication";
        number1 = random.nextInt(20);
        number2 = random.nextInt(20);
        historyQuestion.add(number1 + "x" + number2 + " = ");
        textView_question.setText("What is " + number1 + " x " + number2 + "?");
    }

    public void divisionQuestion() {
        checkTask = "division";
        do {
            number1 = random.nextInt(20);
            number2 = random.nextInt(20);
        } while (number2 == 0 || number1 == 0 || number1 < number2);
        historyQuestion.add(number1 + "/" + number2 + " = ");
        textView_question.setText("What is " + number1 + "/" + number2 + "?");

    }

    public void mixedQuestion() {
        rand = random.nextInt(4);
        if (rand == 0) {
            checkTask = "addition";
            additionQuestion();
        } else if (rand == 1) {
            checkTask = "subtraction";
            subtractionQuestion();
        } else if (rand == 2) {
            checkTask = "multiplication";
            multiplicationQuestion();
        } else {
            checkTask = "division";
            divisionQuestion();
        }

    }


    public void makeAWish() {
        checkOption = "make_a_wish";
        button_submit.setText("Submit");

//        button_next.setVisibility(View.GONE);

        textView_question.setText("How many questions do you want?");

    }

    public void noMistake() {
        checkOption = "no_mistake";
        if (checkTask2.equals("addition"))
            additionQuestion();
        else if (checkTask2.equals("subtraction"))
            subtractionQuestion();
        else if (checkTask2.equals("multiplication"))
            multiplicationQuestion();
        else if (checkTask2.equals("division"))
            divisionQuestion();
        else if (checkTask2.equals("mixed")) {
            mixedQuestion();
        }
    }

    public void takeChances() {
        checkOption = "take_chances";
        mixedQuestion();
    }

}
//package com.example.mathlearninggame;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import java.text.DecimalFormat;
//import java.util.Random;
//
//public class DisplayQuestion extends AppCompatActivity {
//
//    Button button_submit;
//    TextView textView_question;
//    EditText editTextNumber;
//    Random random = new Random();
//    int number1, number2;
//    int rand = 0;
//    String checkTask;
//    String checkOption;
//    int user_wish;
//    int score = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_question);
//
////        button_next = findViewById(R.id.button_next);
//        button_submit = findViewById(R.id.button_submit);
//        textView_question = findViewById(R.id.textView_question);
//        editTextNumber = findViewById(R.id.editTextNumber);
//
//        String task = getIntent().getStringExtra("task");
//        String option = getIntent().getStringExtra("option");
//
//        if (option.equals("make_a_wish")) {
//            makeAWish();
//        } else if (option.equals("no_mistake")) {
//            checkTask = task;
//        }
//
//
////        if (task.equals("addition"))
////            additionQuestion();
////        else if (task.equals("subtraction"))
////            subtractionQuestion();
////        else if (task.equals("multiplication"))
////            multiplicationQuestion();
////        else if (task.equals("division"))
////            divisionQuestion();
////        else if (task.equals("mixed")) {
////            mixedQuestion();
////        }
//        if (task.equals("addition"))
//            checkTask = "addition";
//        else if (task.equals("subtraction"))
//            checkTask = "subtraction";
//        else if (task.equals("multiplication"))
//            checkTask = "multiplication";
//        else if (task.equals("division"))
//            checkTask = "division";
//        else if (task.equals("mixed")) {
//            checkTask = "mixed";
//        }
////
////        button_next.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                button_submit.setVisibility(View.VISIBLE);
////                if (task.equals("addition"))
////                    additionQuestion();
////                else if (task.equals("subtraction"))
////                    subtractionQuestion();
////                else if (task.equals("multiplication"))
////                    multiplicationQuestion();
////                else if (task.equals("division"))
////                    divisionQuestion();
////                else if (task.equals("mixed")) {
////                    mixedQuestion();
////                }
////
////            }
////        });
//
//        button_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (editTextNumber.getText().toString().length() <= 0) {
//                    textView_question.append("\n\nPlease enter the valid option");
//
//                } else {
//                    if (checkOption.equals("make_a_wish")) {
//                        user_wish = Integer.parseInt(editTextNumber.getText().toString());
//                        checkOption = "completed_make_a_wish";
//                        button_submit.setText("Submit answer");
//
//                    } else if (checkOption.equals("completed_make_a_wish")) {
//                        if (user_wish > 1) {
//                            float user_answer = Float.parseFloat(editTextNumber.getText().toString());
//                            float actual_answer = 1;
//                            if (checkTask.equals("addition")) {
//                                actual_answer = number1 + number2;
//                                additionQuestion();
//                            } else if (checkTask.equals("subtraction")) {
//                                if (number1 >= number2)
//                                    actual_answer = number1 - number2;
//                                else
//                                    actual_answer = number2 - number1;
//                                subtractionQuestion();
//                            } else if (checkTask.equals("multiplication")) {
//                                actual_answer = number1 * number2;
//                                multiplicationQuestion();
//                            } else if (checkTask.equals("division")) {
//                                DecimalFormat decimalFormat = new DecimalFormat("0.00");
//                                actual_answer = Float.parseFloat(decimalFormat.format((float) number1 / number2));
//                                divisionQuestion();
//                            } else if (checkTask.equals("mixed")) {
//                                mixedQuestion();
//                            }
//                            if (user_answer == actual_answer) {
//                                textView_question.setText("Correct Answer!!");
//                                score++;
//
//                            } else {
//                                textView_question.setText("Incorrect Answer");
//                            }
//                            user_wish--;
//                        } else {
//                            Intent home = new Intent(DisplayQuestion.this, MainActivity.class);
//                            startActivity(home);
//                            Intent finalIntent = new Intent(DisplayQuestion.this, FinalResult.class);
//                            finalIntent.putExtra("score", score);
//                            startActivity(finalIntent);
//                        }
//
//                    } else {
//                        float user_answer = Float.parseFloat(editTextNumber.getText().toString());
//                        float actual_answer = 1;
//                        if (checkTask.equals("addition")) {
//                            actual_answer = number1 + number2;
//                            additionQuestion();
//                        } else if (checkTask.equals("subtraction")) {
//                            if (number1 >= number2)
//                                actual_answer = number1 - number2;
//                            else
//                                actual_answer = number2 - number1;
//                            subtractionQuestion();
//                        } else if (checkTask.equals("multiplication")) {
//                            actual_answer = number1 * number2;
//                            multiplicationQuestion();
//                        } else if (checkTask.equals("division")) {
//                            DecimalFormat decimalFormat = new DecimalFormat("0.00");
//                            actual_answer = Float.parseFloat(decimalFormat.format((float) number1 / number2));
//                            divisionQuestion();
//                        } else if (checkTask.equals("mixed")) {
//                            mixedQuestion();
//                        }
//                        if (user_answer == actual_answer) {
//                            textView_question.setText("Correct Answer!!");
//
//                        } else {
//                            textView_question.setText("Incorrect Answer");
//                        }
//                    }
//
//                    editTextNumber.setText("");
////                    button_submit.setVisibility(View.GONE);
//                }
//
//                if (task.equals("addition"))
//                    additionQuestion();
//                else if (task.equals("subtraction"))
//                    subtractionQuestion();
//                else if (task.equals("multiplication"))
//                    multiplicationQuestion();
//                else if (task.equals("division"))
//                    divisionQuestion();
//                else if (task.equals("mixed")) {
//                    mixedQuestion();
//                }
//
//            }
//        });
//
//    }
//
//    public void additionQuestion() {
//        checkTask = "addition";
//        number1 = random.nextInt(20);
//        number2 = random.nextInt(20);
//        textView_question.setText("What is " + number1 + " + " + number2 + "?");
//    }
//
//    public void subtractionQuestion() {
//        checkTask = "subtraction";
//        number1 = random.nextInt(20);
//        number2 = random.nextInt(20);
//
//        // if n1 is also equal to n2
//        if (number1 >= number2)
//            textView_question.setText("What is " + number1 + " - " + number2 + "?");
//        else
//            textView_question.setText("What is " + number2 + " - " + number1 + "?");
//
//    }
//
//    public void multiplicationQuestion() {
//        checkTask = "multiplication";
//        number1 = random.nextInt(20);
//        number2 = random.nextInt(20);
//
//        textView_question.setText("What is " + number1 + " x " + number2 + "?");
//    }
//
//    public void divisionQuestion() {
//        checkTask = "division";
//        do {
//            number1 = random.nextInt(20);
//            number2 = random.nextInt(20);
//        } while (number2 == 0 || number1 == 0 || number1 < number2);
//        textView_question.setText("What is " + number1 + "/" + number2 + "?");
//
//    }
//
//    public void mixedQuestion() {
//        rand = random.nextInt(4);
//        if (rand == 0)
//            additionQuestion();
//        else if (rand == 1)
//            subtractionQuestion();
//        else if (rand == 2)
//            multiplicationQuestion();
//        else
//            divisionQuestion();
//
//    }
//
//
//    public void makeAWish() {
//        checkOption = "make_a_wish";
//        button_submit.setText("Submit");
//
////        button_next.setVisibility(View.GONE);
//
//        textView_question.setText("How many questions do you want?");
//
//    }
//
//    public void noMistake() {
//        checkOption = "no_mistake";
//        if (checkTask.equals("addition"))
//            additionQuestion();
//        else if (checkTask.equals("subtraction"))
//            subtractionQuestion();
//        else if (checkTask.equals("multiplication"))
//            multiplicationQuestion();
//        else if (checkTask.equals("division"))
//            divisionQuestion();
//        else if (checkTask.equals("mixed")) {
//            mixedQuestion();
//        }
//    }
//
//
//}

/*
*
* package com.example.mathlearninggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;

public class DisplayQuestion extends AppCompatActivity {

    Button button_submit;
    TextView textView_question;
    EditText editTextNumber;
    Random random = new Random();
    int number1, number2;
    int rand = 0;
    String checkTask;
    String checkOption;
    int user_wish;
    int score = 0;
    boolean user_no_mistake_answer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_question);

//        button_next = findViewById(R.id.button_next);
        button_submit = findViewById(R.id.button_submit);
        textView_question = findViewById(R.id.textView_question);
        editTextNumber = findViewById(R.id.editTextNumber);

        String task = getIntent().getStringExtra("task");
        String option = getIntent().getStringExtra("option");

        if (option.equals("make_a_wish")) {
            makeAWish();
        } else if (option.equals("no_mistake")) {
            checkOption = option;
            checkTask = task;
            noMistake();
        }


//        if (task.equals("addition"))
//            additionQuestion();
//        else if (task.equals("subtraction"))
//            subtractionQuestion();
//        else if (task.equals("multiplication"))
//            multiplicationQuestion();
//        else if (task.equals("division"))
//            divisionQuestion();
//        else if (task.equals("mixed")) {
//            mixedQuestion();
//        }
        if (task.equals("addition"))
            checkTask = "addition";
        else if (task.equals("subtraction"))
            checkTask = "subtraction";
        else if (task.equals("multiplication"))
            checkTask = "multiplication";
        else if (task.equals("division"))
            checkTask = "division";
        else if (task.equals("mixed")) {
            checkTask = "mixed";
        }
//
//        button_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                button_submit.setVisibility(View.VISIBLE);
//                if (task.equals("addition"))
//                    additionQuestion();
//                else if (task.equals("subtraction"))
//                    subtractionQuestion();
//                else if (task.equals("multiplication"))
//                    multiplicationQuestion();
//                else if (task.equals("division"))
//                    divisionQuestion();
//                else if (task.equals("mixed")) {
//                    mixedQuestion();
//                }
//
//            }
//        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextNumber.getText().toString().length() <= 0) {
                    textView_question.append("\n\nPlease enter the valid option");

                } else {
                    if (checkOption.equals("make_a_wish")) {
                        user_wish = Integer.parseInt(editTextNumber.getText().toString());
                        checkOption = "completed_make_a_wish";
                        button_submit.setText("Submit answer");

                    } else if (checkOption.equals("completed_make_a_wish")) {
                        if (user_wish > 1) {
                            float user_answer = Float.parseFloat(editTextNumber.getText().toString());
                            float actual_answer = 1;
                            if (checkTask.equals("addition")) {
                                actual_answer = number1 + number2;
                                additionQuestion();
                            } else if (checkTask.equals("subtraction")) {
                                if (number1 >= number2)
                                    actual_answer = number1 - number2;
                                else
                                    actual_answer = number2 - number1;
                                subtractionQuestion();
                            } else if (checkTask.equals("multiplication")) {
                                actual_answer = number1 * number2;
                                multiplicationQuestion();
                            } else if (checkTask.equals("division")) {
                                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                                actual_answer = Float.parseFloat(decimalFormat.format((float) number1 / number2));
                                divisionQuestion();
                            } else if (checkTask.equals("mixed")) {
                                mixedQuestion();
                            }
                            if (user_answer == actual_answer) {
                                textView_question.setText("Correct Answer!!");
                                score++;

                            } else {
                                textView_question.setText("Incorrect Answer");
                            }
                            user_wish--;
                        } else {
                            Intent home = new Intent(DisplayQuestion.this, MainActivity.class);
                            startActivity(home);
                            Intent finalIntent = new Intent(DisplayQuestion.this, FinalResult.class);
                            finalIntent.putExtra("score", score);
                            startActivity(finalIntent);
                        }

                    } else if (checkOption.equals("no_mistake")) {
                        button_submit.setText("Submit answer");
                        float user_answer = Float.parseFloat(editTextNumber.getText().toString());
                        float actual_answer = 1;
                        if (checkTask.equals("addition")) {
                            actual_answer = number1 + number2;
                            additionQuestion();
                        } else if (checkTask.equals("subtraction")) {
                            if (number1 >= number2)
                                actual_answer = number1 - number2;
                            else
                                actual_answer = number2 - number1;
                            subtractionQuestion();
                        } else if (checkTask.equals("multiplication")) {
                            actual_answer = number1 * number2;
                            multiplicationQuestion();
                        } else if (checkTask.equals("division")) {
                            DecimalFormat decimalFormat = new DecimalFormat("0.00");
                            actual_answer = Float.parseFloat(decimalFormat.format((float) number1 / number2));
                            divisionQuestion();
                        } else if (checkTask.equals("mixed")) {
                            mixedQuestion();
                        }
                        if (user_answer == actual_answer) {
                            textView_question.setText("Correct Answer!!");
                            score++;

                        } else {
                            textView_question.setText("Incorrect Answer");
                            Intent home = new Intent(DisplayQuestion.this, MainActivity.class);
                            startActivity(home);
                            Intent finalIntent = new Intent(DisplayQuestion.this, FinalResult.class);
                            finalIntent.putExtra("score", score);
                            startActivity(finalIntent);
                        }


                    } else {
                        float user_answer = Float.parseFloat(editTextNumber.getText().toString());
                        float actual_answer = 1;
                        if (checkTask.equals("addition")) {
                            actual_answer = number1 + number2;
                            additionQuestion();
                        } else if (checkTask.equals("subtraction")) {
                            if (number1 >= number2)
                                actual_answer = number1 - number2;
                            else
                                actual_answer = number2 - number1;
                            subtractionQuestion();
                        } else if (checkTask.equals("multiplication")) {
                            actual_answer = number1 * number2;
                            multiplicationQuestion();
                        } else if (checkTask.equals("division")) {
                            DecimalFormat decimalFormat = new DecimalFormat("0.00");
                            actual_answer = Float.parseFloat(decimalFormat.format((float) number1 / number2));
                            divisionQuestion();
                        } else if (checkTask.equals("mixed")) {
                            mixedQuestion();
                        }
                        if (user_answer == actual_answer) {
                            textView_question.setText("Correct Answer!!");

                        } else {
                            textView_question.setText("Incorrect Answer");
                        }
                    }

                    editTextNumber.setText("");
//                    button_submit.setVisibility(View.GONE);
                }

                if (task.equals("addition"))
                    additionQuestion();
                else if (task.equals("subtraction"))
                    subtractionQuestion();
                else if (task.equals("multiplication"))
                    multiplicationQuestion();
                else if (task.equals("division"))
                    divisionQuestion();
                else if (task.equals("mixed")) {
                    mixedQuestion();
                }

            }
        });

    }

    public void additionQuestion() {
        checkTask = "addition";
        number1 = random.nextInt(20);
        number2 = random.nextInt(20);
        textView_question.setText("What is " + number1 + " + " + number2 + "?");
    }

    public void subtractionQuestion() {
        checkTask = "subtraction";
        number1 = random.nextInt(20);
        number2 = random.nextInt(20);

        // if n1 is also equal to n2
        if (number1 >= number2)
            textView_question.setText("What is " + number1 + " - " + number2 + "?");
        else
            textView_question.setText("What is " + number2 + " - " + number1 + "?");

    }

    public void multiplicationQuestion() {
        checkTask = "multiplication";
        number1 = random.nextInt(20);
        number2 = random.nextInt(20);

        textView_question.setText("What is " + number1 + " x " + number2 + "?");
    }

    public void divisionQuestion() {
        checkTask = "division";
        do {
            number1 = random.nextInt(20);
            number2 = random.nextInt(20);
        } while (number2 == 0 || number1 == 0 || number1 < number2);
        textView_question.setText("What is " + number1 + "/" + number2 + "?");

    }

    public void mixedQuestion() {
        rand = random.nextInt(4);
        if (rand == 0)
            additionQuestion();
        else if (rand == 1)
            subtractionQuestion();
        else if (rand == 2)
            multiplicationQuestion();
        else
            divisionQuestion();

    }


    public void makeAWish() {
        checkOption = "make_a_wish";
        button_submit.setText("Submit");

//        button_next.setVisibility(View.GONE);

        textView_question.setText("How many questions do you want?");

    }

    public void noMistake() {
        checkOption = "no_mistake";
        if (checkTask.equals("addition"))
            additionQuestion();
        else if (checkTask.equals("subtraction"))
            subtractionQuestion();
        else if (checkTask.equals("multiplication"))
            multiplicationQuestion();
        else if (checkTask.equals("division"))
            divisionQuestion();
        else if (checkTask.equals("mixed")) {
            mixedQuestion();
        }
    }

}
*
*
* */