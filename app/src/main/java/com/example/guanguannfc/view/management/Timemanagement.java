package com.example.guanguannfc.view.management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;

public class Timemanagement extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_time);
    }

    public void Changeactivity(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Changeactivity.class);
        startActivity(intent);
    }
    public void Boxmanagement(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Boxmanagement.class);
        startActivity(intent);
    }
}