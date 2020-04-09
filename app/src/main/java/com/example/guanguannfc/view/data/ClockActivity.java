package com.example.guanguannfc.view.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.guanguannfc.R;

public class ClockActivity extends AppCompatActivity {
    private Button btn_suspend,btn_stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        btn_suspend=findViewById(R.id.btn_suspend);
        btn_stop=findViewById(R.id.btn_stop);

    }

    public void click(View v){
        int id=v.getId();
        switch (id){
            case R.id.btn_suspend:
                Toast.makeText(this,"计时已暂停",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_stop:
                Toast.makeText(this,"计时结束",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(ClockActivity.this, Data.class);
                startActivity(intent);
                break;
        }
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
