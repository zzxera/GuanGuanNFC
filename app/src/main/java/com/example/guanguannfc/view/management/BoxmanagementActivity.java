package com.example.guanguannfc.view.management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;

public class BoxmanagementActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boxmanagement);
    }

    public void Searchgoods(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, SearchgoodsActivity.class);
        startActivity(intent);
    }
    public void Displaygoods(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplaygoodsActivity.class);
        startActivity(intent);
    }
    public void Changebox(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ChangeboxActivity.class);
        startActivity(intent);
    }
    public void Boxtip(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, BoxtipActivity.class);
        startActivity(intent);
    }
    public void Timemanagement(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, TimemanagementActivity.class);
        startActivity(intent);
    }
}
