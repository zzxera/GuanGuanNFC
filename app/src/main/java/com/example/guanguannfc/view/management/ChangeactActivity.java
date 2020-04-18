package com.example.guanguannfc.view.management;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;

public class ChangeactActivity extends AppCompatActivity {
    private PopupWindow mPopWindow;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeact);
        Button btn1 =findViewById(R.id.btn_changeactname);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
        Button btn2 =findViewById(R.id.btn_addact);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow2();
            }
        });

    }
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(ChangeactActivity.this).inflate(R.layout.activity_changeactname, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        //显示PopupWindow
        View rootview = LayoutInflater.from(ChangeactActivity.this).inflate(R.layout.activity_changeact, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }



    private void showPopupWindow2() {
        //设置contentView
        View contentView = LayoutInflater.from(ChangeactActivity.this).inflate(R.layout.activity_addact, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        //显示PopupWindow
        View rootview = LayoutInflater.from(ChangeactActivity.this).inflate(R.layout.activity_changeact, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }
    public void Changename(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ChangenameActivity.class);
        startActivity(intent);
    }
}
