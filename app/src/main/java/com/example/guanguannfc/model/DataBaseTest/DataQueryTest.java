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
    Button mButton1,mButton2,mButton3;
    EditText mEditText1,mEditText2;
    DaoActSta mActSta = new DaoActSta(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liuyu_dataquerytest);
        initView();
        initEvent();
    }

    private void initView() {
        mButton1 = findViewById(R.id.act_type_time);
        mButton2 = findViewById(R.id.all_activity);
        mButton3 = findViewById(R.id.all_type);
        mEditText1 = findViewById(R.id.act_type);
        mEditText2 = findViewById(R.id.all_time);
    }

    private void initEvent() {

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.act_type_time:
//                Long aLong = new Long(mActSta.queryActType("aaa","学习",0l,1586686207110l));
////                15866667400000
//                mEditText2.setText(aLong.toString());
//                break;
            case R.id.all_activity:
                List list = mActSta.queryByLengthDesc("aaa");
                for(int i=0;i<list.size();i++){

                    Log.v(i+"",list.get(i).toString());
                }
                break;
            case R.id.all_type:
                List list1 = mActSta.queryActType("aaa",0l,1586686207110l);
                for(int i=0;i<list1.size();i++){

                    Log.v(i+"",list1.get(i).toString());
                }
                break;
        }
    }
}
