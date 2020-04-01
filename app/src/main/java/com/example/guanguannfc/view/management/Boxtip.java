package com.example.guanguannfc.view.management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;

public class Boxtip extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tip_box);
    }

    public void Addbox(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Addbox.class);
        startActivity(intent);
    }
}