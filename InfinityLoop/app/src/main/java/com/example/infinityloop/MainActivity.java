package com.example.infinityloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private TextView tvNumber;
    private long startTime = -1;
    private long pastTime = 0;
    private long delay = 0;
    private boolean b = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumber = findViewById(R.id.tv_number);
        startTime = getPreferences(MODE_PRIVATE).getLong("Number", System.currentTimeMillis());
        getPreferences(MODE_PRIVATE).edit().putLong("Number", startTime).apply();

        tvNumber.setOnClickListener(v -> {
            startTime = System.currentTimeMillis();
            getPreferences(MODE_PRIVATE).edit().putLong("Number", startTime).apply();
        });
    }

    private final Runnable tikTak = new Runnable() {
        @Override
        public void run() {
            if (b) {
                b = false;
                pastTime = System.currentTimeMillis() - 1000L;
            }
            delay = 1000L - (System.currentTimeMillis() - pastTime - delay);
            mHandler.postDelayed(this, 1000L + delay);
            tvNumber.setText(String.valueOf((System.currentTimeMillis() - startTime) / 1000));
            pastTime = System.currentTimeMillis();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(tikTak);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.post(tikTak);
    }
}