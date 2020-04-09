package com.example.guanguannfc.controller.dataManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.print.*;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.timeManagement.GetTime;

public class DateTestActivity extends AppCompatActivity {

    private Button mbtn_test;
    private TextView mtext_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datatest);

        mbtn_test = findViewById(R.id.btn_test);
        mtext_test = findViewById(R.id.data_test);

        mbtn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time_01 = GetTime.getNowTime();
                mtext_test.setText(time_01);
            }
        });
    }
}
