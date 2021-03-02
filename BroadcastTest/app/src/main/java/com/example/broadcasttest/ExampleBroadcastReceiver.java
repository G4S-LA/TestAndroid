package com.example.broadcasttest;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ExampleBroadcastReceiver extends BroadcastReceiver {
    @SuppressLint("ShowToast")
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("QWE".equals(intent.getAction())) {
            Toast.makeText(context, intent.getStringExtra("text"), Toast.LENGTH_SHORT).show();
        }
    }
}
