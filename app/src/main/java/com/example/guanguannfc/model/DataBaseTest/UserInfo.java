package com.example.guanguannfc.model.DataBaseTest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;
import com.example.guanguannfc.model.Dao.DaoUserInfo;

public class UserInfo extends AppCompatActivity implements View.OnClickListener {
    DaoUserInfo mDaoUserInfo = new DaoUserInfo(this);
    Button mButton1,mButton2,mButton3,mButton4;
    EditText password1,password3,password4,username1,username2,username3,username4,mEditText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liuyu_userinfo);
        initView();
        initEvent();
    }

    private void initView() {

        mButton1 = findViewById(R.id.insert);
        mButton2 = findViewById(R.id.delete);
        mButton3 = findViewById(R.id.update);
        mButton4 = findViewById(R.id.query);
        password1 = findViewById(R.id.password1);
        password3 = findViewById(R.id.password3);
        password4 = findViewById(R.id.password4);
        username1 = findViewById(R.id.username1);
        username2 = findViewById(R.id.username2);
        username3 = findViewById(R.id.username3);
        username4 = findViewById(R.id.username4);
        mEditText = findViewById(R.id.booleans);
    }
    private void initEvent() {
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insert:
                mDaoUserInfo.insert(username1.getText().toString(),password1.getText().toString());
                username1.setText("");
                password1.setText("");
                break;
            case R.id.delete:
                mDaoUserInfo.delete(username2.getText().toString());
                username2.setText("");

                break;
            case R.id.update:
                mDaoUserInfo.update(username3.getText().toString(),password3.getText().toString());
                username3.setText("");
                password3.setText("");
                break;
            case R.id.query:


                String str = String.valueOf(mDaoUserInfo.loadQuery(username4.getText().toString(),password4.getText().toString()));
                mEditText.setText(str);
                username4.setText("");
                password4.setText("");
                break;
        }

    }
}
