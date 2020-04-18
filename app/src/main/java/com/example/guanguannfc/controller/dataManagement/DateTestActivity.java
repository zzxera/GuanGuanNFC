package com.example.guanguannfc.controller.dataManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.print.*;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.timeManagement.GetTime;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DateTestActivity extends AppCompatActivity {

    public static final String TAG = "MyActivity";
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
                Log.d(TAG, "当前时间 时间戳: " + System.currentTimeMillis());
                Log.d(TAG, "当前时间 完整时间 : " + GetTime.timeStampToDate(System.currentTimeMillis()));
                Log.d(TAG, "当前时间 year: " + GetTime.getYearByTimeStamp(System.currentTimeMillis()));
                Log.d(TAG, "当前时间 month: " + GetTime.getMonthByTimeStamp(System.currentTimeMillis()));
                Log.d(TAG, "当前时间 day: " + GetTime.getDayByTimeStamp(System.currentTimeMillis()));
                Log.d(TAG, "当前时间 Hour: " + GetTime.getHourByTimeStamp(System.currentTimeMillis()));
                Log.d(TAG, "当前时间 minute: " + GetTime.getMinuteByTimeStamp(System.currentTimeMillis()));
                Log.d(TAG, "当前时间 Second: " + GetTime.getSecondByTimeStamp(System.currentTimeMillis()));
                Log.d(TAG, "当前时间 week: " + GetTime.getWeekByTimeStamp(System.currentTimeMillis()));
                Log.d(TAG, "当前时间 今日开始时间戳: " + GetTime.getBeginTime("本日"));
                Log.d(TAG, "当前时间 本周开始时间戳: " + GetTime.getBeginTime("本周"));
                Log.d(TAG, "当前时间 本月开始时间戳: " + GetTime.getBeginTime("本月"));
//                Log.d(TAG, "当前时间 二维数组时间: " + GetTime.transString(System.currentTimeMillis())[1][1]);
                //二维数组不能输出不知为何。
                Log.d(TAG, "当前时间 得到时间戳: " + GetTime.getStringToDate("2020-4-13 22-00-00", "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }
}



