package com.example.infinityloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private TextView tvNumber;
    private SharedPreferences sPref;
    private long number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumber = findViewById(R.id.tv_number);
        number = 0;
        loadNumber();
        tvNumber.setOnClickListener(v -> number = -1);
        mHandler.post(tikTak);
    }

    private final Runnable tikTak = new Runnable() {
        @Override
        public void run() {
            Log.d("...", tvNumber.getText().toString());
            mHandler.postDelayed(this, 1000);
            number += 1000;
            setTvNumber();
        }
    };

    private void saveNumber(){
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putLong("Time", System.currentTimeMillis());
        editor.putLong("Number",number);
        Log.d("...", "Сохранили число " + tvNumber.getText().toString());
        editor.apply();
    }

    private void loadNumber(){
        sPref = getPreferences(MODE_PRIVATE);
        long pastTime = sPref.getLong("Time",System.currentTimeMillis());
        number = sPref.getLong("Number", 0);
        number += System.currentTimeMillis()-pastTime;
        setTvNumber();
    }

    @Override
    public boolean deleteSharedPreferences(String name) {
        return super.deleteSharedPreferences(name);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveNumber();
        mHandler.removeCallbacks(tikTak);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadNumber();
        mHandler.post(tikTak);
    }

    private void setTvNumber(){
        tvNumber.setText(String.valueOf(number/1000));
    }
}