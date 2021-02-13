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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumber = findViewById(R.id.tv_number);
        loadNumber();
        tvNumber.setOnClickListener(v -> tvNumber.setText("0"));
        mHandler.post(tikTak);
    }

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
        SharedPreferences.Editor editor = sPref.edit();
        editor.putLong("Time", System.currentTimeMillis());
        editor.putString("Number",tvNumber.getText().toString());
        Log.d("...", "Сохранили число " + tvNumber.getText().toString());
        editor.apply();
    }

    private void loadNumber(){
        sPref = getPreferences(MODE_PRIVATE);
        long pastTime = sPref.getLong("Time",System.currentTimeMillis());
        String number = sPref.getString("Number", "0");
        Log.d("...", "Загрузили число " + number);
        Log.d("...", "pstTime = " + pastTime);
        Log.d("...", "currentTime = " + System.currentTimeMillis());
        tvNumber.setText(String.valueOf( (Long.parseLong(number) + (System.currentTimeMillis()-pastTime)/1000)));
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
}