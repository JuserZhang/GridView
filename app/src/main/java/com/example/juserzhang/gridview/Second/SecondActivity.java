package com.example.juserzhang.gridview.Second;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;

import com.example.juserzhang.gridview.R;

public class SecondActivity extends AppCompatActivity {
        private Chronometer timer;
        @Override
        protected void onCreate( Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_second);
            timer = (Chronometer) findViewById(R.id.timer);


        }

        public void btnClick(View view) {
            timer.setBase(SystemClock.elapsedRealtime());//计时器清零
            int hour = (int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 1000 / 60);
            timer.setFormat("0"+String.valueOf(hour)+":%s");
            timer.start();
        }
        public void btnReset(View view){

            timer.setBase(SystemClock.elapsedRealtime());//计时器清零
            timer.stop();
        }
        public void stopClick(View view) {
            timer.stop();
        }
    }
