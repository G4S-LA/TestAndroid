package com.example.recycleviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private final List<Object> list = Arrays.asList(3, Arrays.asList(13,765,1,34,56,432,34),32,76,34,65,42, Arrays.asList(32,543,8,765,5,67),654,34,Arrays.asList(0,86,34,56,54,3));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView numbersList = findViewById(R.id.rv_numbers);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        numbersList.setLayoutManager(linearLayoutManager);

        NumbersAdapter numbersAdapter = new NumbersAdapter(list, this);
        numbersList.setAdapter(numbersAdapter);
    }
}