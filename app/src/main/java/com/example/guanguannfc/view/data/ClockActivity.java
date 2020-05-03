package com.example.guanguannfc.view.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.timeManagement.GetTime;


public class ClockActivity extends AppCompatActivity {
    private Button btn_suspend,btn_stop;
    private TextView tv_start_time,tv_now_time,tv_distance,tv_duration;
    private GetTime getTime;
    String userName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        btn_suspend=findViewById(R.id.btn_suspend);
        btn_stop=findViewById(R.id.btn_stop);
        tv_start_time=findViewById(R.id.tv_start_time);
        tv_now_time=findViewById(R.id.tv_now_time);
        tv_duration=findViewById(R.id.tv_duration);
        Long startTime=getTime.getStartTime();
        tv_start_time.setText(Long.toString(getTime.getStartTime()));
        tv_now_time.setText(getTime.getNowTime());
        tv_duration.setText(getTime.countTime(startTime));

        Intent intent=getIntent();
        userName=intent.getStringExtra("username");
        Toast.makeText(this,"开始计时",Toast.LENGTH_LONG).show();
//        Toast.makeText(this,userName,Toast.LENGTH_LONG).show();


    }

    public void click(View v){
        int id=v.getId();
        switch (id){
            case R.id.btn_suspend:
                Toast.makeText(this,"计时已暂停",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_stop:
                Intent intent = new Intent();
                intent.putExtra("result","本次计时已结束");
                this.setResult(1,intent);
                this.finish();
                break;
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result","计时继续");
        this.setResult(1,intent);
        this.finish();
    }


    private void delay(int ms){
        try {
            Thread.currentThread();
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
