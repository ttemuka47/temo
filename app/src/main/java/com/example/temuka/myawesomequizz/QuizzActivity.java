package com.example.temuka.myawesomequizz;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class QuizzActivity extends AppCompatActivity {
    public static final String EXTRA_NUMBER = ".com.example.temuka.myawesomequizz";



    Button Answer1, Answer2, Answer3, Answer4, next, finish;
    TextView score, question, timer;
    private Questions mQuestions = new Questions();
    private String mAnswer;
    public int mScore = 0;
    private int mQuestionsLength = mQuestions.mQuestion.length;
    Random r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        r = new Random();

        Answer1 = (Button) findViewById(R.id.Answer1);
        Answer2 = (Button) findViewById(R.id.Answer2);
        Answer3 = (Button) findViewById(R.id.Answer3);
        Answer4 = (Button) findViewById(R.id.Answer4);
        next = (Button) findViewById(R.id.next);
        finish= (Button) findViewById(R.id.finish);
        timer = (TextView) findViewById(R.id.timer);
        final TextView score= (TextView) findViewById(R.id.score);


        question = (TextView) findViewById(R.id.question);
        score.setText(mScore + "/30");
        reverseTimer(1800, timer);

        updateQuestion(r.nextInt(mQuestionsLength));

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(QuizzActivity.this, ResultActivity.class);
                myIntent.putExtra("Username", score.getText().toString());

                startActivity(myIntent);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer1.setBackgroundResource(android.R.drawable.btn_default);
                Answer2.setBackgroundResource(android.R.drawable.btn_default);
                Answer3.setBackgroundResource(android.R.drawable.btn_default);
                Answer4.setBackgroundResource(android.R.drawable.btn_default);
                updateQuestion(r.nextInt(mQuestionsLength));


            }
        });


        Answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Answer1.getText() == mAnswer) {
                    Answer1.setBackgroundColor(Color.GREEN);
                    mScore++;
                    score.setText("Score " + mScore);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Answer1.setBackgroundColor(Color.parseColor("#03A9F4"));
                            updateQuestion(r.nextInt(mQuestionsLength));
                        }
                    }, 1500);

                } else {
                    Answer1.setBackgroundColor(Color.RED);

                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Answer1.setBackgroundResource(android.R.drawable.btn_default);
                        Answer2.setBackgroundResource(android.R.drawable.btn_default);
                        Answer3.setBackgroundResource(android.R.drawable.btn_default);
                        Answer4.setBackgroundResource(android.R.drawable.btn_default);
                        updateQuestion(r.nextInt(mQuestionsLength));
                    }
                }, 1500);

            }
        });
        Answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Answer2.getText() == mAnswer) {
                    Answer2.setBackgroundColor(Color.GREEN);
                    mScore++;
                    score.setText("Score " + mScore);

                } else {
                    Answer2.setBackgroundColor(Color.RED);

                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Answer1.setBackgroundResource(android.R.drawable.btn_default);
                        Answer2.setBackgroundResource(android.R.drawable.btn_default);
                        Answer3.setBackgroundResource(android.R.drawable.btn_default);
                        Answer4.setBackgroundResource(android.R.drawable.btn_default);
                        updateQuestion(r.nextInt(mQuestionsLength));
                    }
                }, 1500);
            }
        });
        Answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Answer3.getText() == mAnswer) {
                    Answer3.setBackgroundColor(Color.GREEN);
                    mScore++;
                    score.setText("Score " + mScore);

                } else {
                    Answer3.setBackgroundColor(Color.RED);

                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Answer1.setBackgroundResource(android.R.drawable.btn_default);
                        Answer2.setBackgroundResource(android.R.drawable.btn_default);
                        Answer3.setBackgroundResource(android.R.drawable.btn_default);
                        Answer4.setBackgroundResource(android.R.drawable.btn_default);
                        updateQuestion(r.nextInt(mQuestionsLength));
                    }
                }, 1500);
            }
        });
        Answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer4.setBackgroundColor(Color.GREEN);
                if (Answer4.getText() == mAnswer) {
                    mScore++;
                    score.setText("Score " + mScore);

                } else {
                    Answer4.setBackgroundColor(Color.RED);

                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Answer1.setBackgroundResource(android.R.drawable.btn_default);
                        Answer2.setBackgroundResource(android.R.drawable.btn_default);
                        Answer3.setBackgroundResource(android.R.drawable.btn_default);
                        Answer4.setBackgroundResource(android.R.drawable.btn_default);
                        updateQuestion(r.nextInt(mQuestionsLength));
                    }
                }, 1500);
            }
        });

    }


    private void updateQuestion(int num) {
        question.setText(mQuestions.getQuestion(num));
        Answer1.setText(mQuestions.getChoice1(num));
        Answer2.setText(mQuestions.getChoice2(num));
        Answer3.setText(mQuestions.getChoice3(num));
        Answer4.setText(mQuestions.getChoice4(num));

        mAnswer = mQuestions.getCorrectAnswer(num);
    }

    public void reverseTimer(int Seconds, final TextView tv) {

        new CountDownTimer(Seconds * 1000 + 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                tv.setText("Completed");
                Intent myIntent = new Intent(QuizzActivity.this, ResultActivity.class);
                startActivity(myIntent);
            }
        }.start();
    }


}







