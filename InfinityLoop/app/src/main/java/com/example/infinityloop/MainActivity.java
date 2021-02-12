package com.example.infinityloop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private TextView tvNumber;
    private SharedPreferences sPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumber = findViewById(R.id.tv_number);
        loadNumber();
        mHandler.post(tikTak);
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        outState.putString("KEY", tvNumber.getText().toString());
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        tvNumber.setText(savedInstanceState.getString("KEY"));
//    }

    private long viewToLongPlusOne(TextView view){
        return Long.parseLong(view.getText().toString()) + 1;
    }

    private final Runnable tikTak = new Runnable() {
        @Override
        public void run() {
            Log.d("...", tvNumber.getText().toString());
            mHandler.postDelayed(this, 1000);
            tvNumber.setText(String.valueOf(viewToLongPlusOne(tvNumber)));
        }
    };

    private void saveNumber(){
        sPref = getPreferences(MODE_PRIVATE);
        editor = sPref.edit();
        editor.putLong("Time", System.currentTimeMillis());
        editor.apply();
    }

    private void loadNumber(){
        sPref = getPreferences(MODE_PRIVATE);
        long pastTime = sPref.getLong("Time",System.currentTimeMillis());
        tvNumber.setText(String.valueOf( Long.parseLong(tvNumber.getText().toString()) + System.currentTimeMillis()-pastTime));
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
        mHandler.post(tikTak);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.remove("Time");
        editor.apply();
    }
}