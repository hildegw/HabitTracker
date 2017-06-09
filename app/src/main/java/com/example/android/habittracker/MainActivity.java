package com.example.android.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import data.DiveDbHelper;

public class MainActivity extends AppCompatActivity {

    DiveDbHelper mDiveDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //open helper instance to access DB
        mDiveDbHelper = new DiveDbHelper(this);
    }

    private void insertDive() {

    }

    private void readDive() {

    }
}
