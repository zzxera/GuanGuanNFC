package com.example.guanguannfc.view.management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;

public class Boxmanagement extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_box);
    }

    public void Searchgoods(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Searchgoods.class);
        startActivity(intent);
    }
    public void Displaygoods(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Displaygoods.class);
        startActivity(intent);
    }
    public void Changebox(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Changebox.class);
        startActivity(intent);
    }
    public void Boxtip(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Boxtip.class);
        startActivity(intent);
    }
    public void Timemanagement(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Timemanagement.class);
        startActivity(intent);
    }
}