package com.example.guanguannfc.model.DataBaseTest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;
import com.example.guanguannfc.model.Dao.DaoActSta;
import com.example.guanguannfc.model.GuanContract;

import java.util.List;

public class DataQueryTest extends AppCompatActivity implements View.OnClickListener {
    Button mButton2,mButton3;
    DaoActSta mActSta = new DaoActSta(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liuyu_dataquerytest);
        initView();
        initEvent();
    }

    private void initView() {
        mButton2 = findViewById(R.id.all_activity);
        mButton3 = findViewById(R.id.all_type);

    }

    private void initEvent() {
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.all_activity:
                List list = mActSta.queryByLengthDesc("aaa","工作");
                for(int i=0;i<list.size();i++){

                    Log.v(i+"",list.get(i).toString());
                }
                break;
            case R.id.all_type:
                List list1 = mActSta.queryActType("aaa",0l,1596686207110l);
                for(int i=0;i<list1.size();i++){

                    Log.v(i+"",list1.get(i).toString());
                }
                break;
        }
    }
}
