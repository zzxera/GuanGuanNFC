package com.example.guanguannfc.model.DataBaseTest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;
import com.example.guanguannfc.model.DataBaseTest.UserInfo;
import com.example.guanguannfc.model.Initialization;


/**
 * 数据表操作方法测试类
 */
public class LiuYuTest extends AppCompatActivity implements View.OnClickListener {
    Button mButton1,mButton2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liuyutest);
        initView();
        initEvent();

    }

    private void initView() {
        mButton1 = findViewById(R.id.btn1);
        mButton2 = findViewById(R.id.btn2);

        Initialization.initialization(this);
//        FakeData fakeData = new FakeData(this);
//        fakeData.insert();

    }

    private void initEvent() {
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                Intent intent = new Intent(this, UserInfo.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                intent = new Intent(this,DataQueryTest.class);
                startActivity(intent);
                break;

        }
    }
}
