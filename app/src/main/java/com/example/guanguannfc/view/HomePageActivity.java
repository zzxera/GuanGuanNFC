package com.example.guanguannfc.view;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.guanguannfc.R;
import com.example.guanguannfc.view.data.Data;
import com.example.guanguannfc.view.data.DataFragment;
import com.example.guanguannfc.view.friends.FrendFragment;
import com.example.guanguannfc.view.loginAndLogon.LoginActivity;
import com.example.guanguannfc.view.mainInterface.PushFragment;
import com.example.guanguannfc.view.management.ManageFragment;

public class HomePageActivity extends FragmentActivity implements View.OnClickListener {

    private String userName;
    private RelativeLayout main_body;
    private LinearLayout main_bottom_bar;
    private RelativeLayout bottom_bar_1_btn,bottom_bar_2_btn,bottom_bar_3_btn,bottom_bar_4_btn;
    private TextView bottom_bar_text_1,bottom_bar_text_2,bottom_bar_text_3,bottom_bar_text_4;
    private TextView tv_userName;
    private ImageView bottom_bar_image_1,bottom_bar_image_2,bottom_bar_image_3,bottom_bar_image_4;
    private ConstraintLayout ctl_person;
    private LinearLayout ll_container;
    private ImageView img_person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_home_page);
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.homepage_title_bar);
        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null){
            userName=bundle.getString("userName");
        }

//        Toast.makeText(this,"用户名"+userName,Toast.LENGTH_LONG).show();

        initView();
        setMain();
        checkClick();


        img_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctl_person.setVisibility(View.VISIBLE);
                ll_container.setVisibility(View.VISIBLE);
            }
        });
        ll_container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ctl_person.setVisibility(View.INVISIBLE);
                ll_container.setVisibility(View.GONE);
                return false;
            }
        });
    }


    private void initView(){
//        主体
        main_body=findViewById(R.id.main_body);
//       个人设置
        ctl_person=findViewById(R.id.layout_person);
        img_person=findViewById(R.id.img_personSetting);
        ll_container=findViewById(R.id.ll_container);
        tv_userName=findViewById(R.id.text_userName);
        tv_userName.setText(userName);

//        底部导航栏
        main_bottom_bar=findViewById(R.id.main_bottom_bar);
        bottom_bar_1_btn=findViewById(R.id.bottom_bar_1_btn);
        bottom_bar_2_btn=findViewById(R.id.bottom_bar_2_btn);
        bottom_bar_3_btn=findViewById(R.id.bottom_bar_3_btn);
        bottom_bar_4_btn=findViewById(R.id.bottom_bar_4_btn);
        bottom_bar_text_1=findViewById(R.id.bottom_bar_text_1);
        bottom_bar_text_2=findViewById(R.id.bottom_bar_text_2);
        bottom_bar_text_3=findViewById(R.id.bottom_bar_text_3);
        bottom_bar_text_4=findViewById(R.id.bottom_bar_text_4);

        bottom_bar_image_1=findViewById(R.id.bottom_bar_image_1);
        bottom_bar_image_2=findViewById(R.id.bottom_bar_image_2);
        bottom_bar_image_3=findViewById(R.id.bottom_bar_image_3);
        bottom_bar_image_4=findViewById(R.id.bottom_bar_image_4);


        bottom_bar_1_btn.setOnClickListener(this);
        bottom_bar_2_btn.setOnClickListener(this);
        bottom_bar_3_btn.setOnClickListener(this);
        bottom_bar_4_btn.setOnClickListener(this);

    }
//  切换底部样式
    private void setSelectStatus(int index){
        switch (index){
            case 0:
                bottom_bar_text_1.setTextColor(Color.parseColor("#0097F7"));
                bottom_bar_text_2.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_3.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_4.setTextColor(Color.parseColor("#666666"));
                bottom_bar_image_1.setImageResource(R.drawable.img_pushi_selected);
                bottom_bar_image_2.setImageResource(R.drawable.img_data);
                bottom_bar_image_3.setImageResource(R.drawable.img_manage);
                bottom_bar_image_4.setImageResource(R.drawable.img_frend);

                break;
            case 1:
                bottom_bar_text_1.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_2.setTextColor(Color.parseColor("#0097F7"));
                bottom_bar_text_3.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_4.setTextColor(Color.parseColor("#666666"));
                bottom_bar_image_1.setImageResource(R.drawable.img_pushi);
                bottom_bar_image_2.setImageResource(R.drawable.img_data_selected);
                bottom_bar_image_3.setImageResource(R.drawable.img_manage);
                bottom_bar_image_4.setImageResource(R.drawable.img_frend);
                break;
            case 2:
                bottom_bar_text_1.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_2.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_3.setTextColor(Color.parseColor("#0097F7"));
                bottom_bar_text_4.setTextColor(Color.parseColor("#666666"));
                bottom_bar_image_1.setImageResource(R.drawable.img_pushi);
                bottom_bar_image_2.setImageResource(R.drawable.img_data);
                bottom_bar_image_3.setImageResource(R.drawable.img_manage_selected);
                bottom_bar_image_4.setImageResource(R.drawable.img_frend);
                break;
            case 3:
                bottom_bar_text_1.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_2.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_3.setTextColor(Color.parseColor("#666666"));
                bottom_bar_text_4.setTextColor(Color.parseColor("#0097F7"));
                bottom_bar_image_1.setImageResource(R.drawable.img_pushi);
                bottom_bar_image_2.setImageResource(R.drawable.img_data);
                bottom_bar_image_3.setImageResource(R.drawable.img_manage);
                bottom_bar_image_4.setImageResource(R.drawable.img_frend_selected);
                break;
        }
    }


//    点击底部按钮相应
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bottom_bar_1_btn:
                PushFragment fragment1 = new PushFragment();
                Bundle bundle = new Bundle();
                bundle.putString("username",userName);
                fragment1.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,fragment1).commit();
                setSelectStatus(0);
                break;
            case R.id.bottom_bar_2_btn:
                DataFragment fragment2 = new DataFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("username",userName);
                fragment2.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,fragment2).commit();
                setSelectStatus(1);
                break;
            case R.id.bottom_bar_3_btn:
                ManageFragment fragment3 = new ManageFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putString("username",userName);
                fragment3.setArguments(bundle3);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,fragment3).commit();
                setSelectStatus(2);
                break;
            case R.id.bottom_bar_4_btn:
                FrendFragment fragment4 = new FrendFragment();
                Bundle bundle4 = new Bundle();
                bundle4.putString("username",userName);
                fragment4.setArguments(bundle4);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,fragment4).commit();
                setSelectStatus(3);
                break;


        }
    }

    private void checkClick(){
        findViewById(R.id.button_quit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                退出登录
                Intent intent2 = new Intent();
                intent2.setClass(HomePageActivity.this, LoginActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
            }
        });


    }

    private void setMain() {
        //getSupportFragmentManager() -> beginTransaction() -> add -> (R.id.main_boy,显示课程 new CourseFragment()
        DataFragment dataFragment = new DataFragment();
        Bundle bundle = new Bundle();
        bundle.putString("username",userName);
        dataFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_body,dataFragment).commit();
        setSelectStatus(1);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
}
