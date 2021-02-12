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
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyViewModel model = ViewModelProviders.of(this).get(MyViewModel.class);

        tvNumber = findViewById(R.id.tv_number);
//        loadNumber();

        tvNumber.setOnClickListener(v -> {
            tvNumber.setText("10");
        });

        if(flag) {
            flag = false;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    tvNumber.setText(String.valueOf(viewToLongPlusOne(tvNumber)));
                    Log.d("...", tvNumber.getText().toString());
                    mHandler.postDelayed(this, 1000);
                }
            });
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("KEY", tvNumber.getText().toString());
        outState.putBoolean("Bool", b);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvNumber.setText(savedInstanceState.getString("KEY","0"));
        b = savedInstanceState.getBoolean("Bool", true);

    }

    private long viewToLongPlusOne(TextView view){
        return Long.parseLong(view.getText().toString()) + 1;
    }
}