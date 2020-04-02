package com.example.guanguannfc.view.management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;

public class Searchgoods extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchgoods);
    }

    public void Displaygoods(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Displaygoods.class);
        startActivity(intent);
    }
}