package com.example.guanguannfc.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;
import com.example.guanguannfc.model.broadcastTest.TestController;
import com.example.guanguannfc.view.homepage.HomePageActivity;

public class TestActivity extends AppCompatActivity implements View.OnClickListener, TestController.Message {
    //登录按钮
    private Button loadBtn;
    //用户名
    private TextView userText;
    //密码
    private TextView pwdText;
    private ImageView loadImg;
    //包裹ImageView
    private RelativeLayout mRelativeLayout;
    private TestController mTestController;


    private Animation animation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liuyu_dataquerytest);
        initView();
        initEvent();
    }
    public void initView(){
        loadBtn = findViewById(R.id.submit);
        userText = findViewById(R.id.username);
        pwdText = findViewById(R.id.password);
        loadImg = findViewById(R.id.load_img);
        mRelativeLayout = findViewById(R.id.rela_img);
        mTestController = new TestController(this);


    }
    public void initEvent(){
       loadBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit :

                //动画效果
                animation = AnimationUtils.loadAnimation(this, R.anim.tip);
                animation.setDuration(500);
                animation.setRepeatCount(8);//动画的反复次数
                animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                loadImg.startAnimation(animation);//開始动画
                mRelativeLayout.setVisibility(View.VISIBLE);

                //业务
                String username = userText.getText().toString();
                String password = pwdText.getText().toString();
                mTestController.load(username,password);



                break;
        }
    }



    //Controller中load方法的回调，即保证网络请求完成以后执行后续方法，保证顺序执行
    //一般来说一个controller请求会对应一个回调方法
    @Override
    public void getLoadMessage(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadImg.clearAnimation();
                mRelativeLayout.setVisibility(View.GONE);
                if ("登陆成功".equals(str)) {
                    Intent intent = new Intent(TestActivity.this, HomePageActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(TestActivity.this, str, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
