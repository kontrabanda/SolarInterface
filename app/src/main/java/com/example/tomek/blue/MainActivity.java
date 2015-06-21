package com.example.tomek.blue;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    BlueController blueController;
    Button sendButton;
    Button getButton;
    TextView textView;
    private boolean isTimerStopped = true;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = getApplicationContext();
        blueController = new BlueController(ctx);
        isTimerStopped = false;
        startTimer();
        //TODO dodac wszystkietext view z opisem (warstwa prezentacji)
        sendButton = (Button) findViewById(R.id.SendButton);
        getButton = (Button) findViewById(R.id.GetButton);
        textView = (TextView) findViewById(R.id.tvPD);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setTextInTextView();
            }
        });
    }

    private CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            setTextInTextView();
            startTimer();
        }
    };

    private void startTimer(){
        if(!isTimerStopped)
            countDownTimer.start();
    }

    public void stopTimer(){
        isTimerStopped = true;
    }

    private void setTextInTextView(){
        textView.setText(blueController.getAccelerationValue());
//        blueController.getValues();
    }
}
