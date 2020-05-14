package com.example.guanguannfc.view;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.Toast;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.nfcManagement.BaseNfcActivity;
import com.example.guanguannfc.controller.nfcManagement.NFCManage;
import com.example.guanguannfc.controller.timeManagement.GetTime;
import com.example.guanguannfc.view.data.ClockActivity;
import com.example.guanguannfc.view.data.Data;
import com.example.guanguannfc.view.data.DataFragment;
import com.example.guanguannfc.view.friends.FrendFragment;
import com.example.guanguannfc.view.loginAndLogon.LoginActivity;
import com.example.guanguannfc.view.mainInterface.PushFragment;
import com.example.guanguannfc.view.management.ManageFragment;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class HomePageActivity extends BaseNfcActivity implements View.OnClickListener {

    private String userName;
    private RelativeLayout main_body;
    private LinearLayout main_bottom_bar;
    private RelativeLayout bottom_bar_1_btn,bottom_bar_2_btn,bottom_bar_3_btn,bottom_bar_4_btn;
    private TextView bottom_bar_text_1,bottom_bar_text_2,bottom_bar_text_3,bottom_bar_text_4;
    private TextView tv_userName,tv_prompt;
    private ImageView bottom_bar_image_1,bottom_bar_image_2,bottom_bar_image_3,bottom_bar_image_4;
    private ConstraintLayout ctl_person,lay_actshow;
    private ConstraintLayout.LayoutParams layoutParams;
    private LinearLayout ll_container;
    private ImageView img_person;
    private String mTagText,actType,actName;
    private GetTime getTime;
    
    private DataFragment dataFragment;
    PushFragment pushFragment;
    ManageFragment manageFragment;
    FrendFragment frendFragment;

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

    @Override
    protected void onStart() {
        super.onStart();
        View dataView = dataFragment.getView();
        tv_prompt = dataView.findViewById(R.id.text_prompt);
        lay_actshow=dataView.findViewById(R.id.layout_allact);
        layoutParams = (ConstraintLayout.LayoutParams) lay_actshow.getLayoutParams();
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

        pushFragment = new PushFragment();
        dataFragment = new DataFragment();
        manageFragment = new ManageFragment();
        frendFragment = new FrendFragment();

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
                Bundle bundle = new Bundle();
                bundle.putString("username",userName);
                pushFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,pushFragment).commit();
                setSelectStatus(0);
                break;
            case R.id.bottom_bar_2_btn:
                Bundle bundle2 = new Bundle();
                bundle2.putString("username",userName);
                dataFragment.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,dataFragment).commit();
                setSelectStatus(1);
                break;
            case R.id.bottom_bar_3_btn:
                
                Bundle bundle3 = new Bundle();
                bundle3.putString("username",userName);
                manageFragment.setArguments(bundle3);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,manageFragment).commit();
                setSelectStatus(2);
                break;
            case R.id.bottom_bar_4_btn:
                Bundle bundle4 = new Bundle();
                bundle4.putString("username",userName);
                frendFragment.setArguments(bundle4);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,frendFragment).commit();
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
        Bundle bundle = new Bundle();
        bundle.putString("username",userName);
        dataFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_body,dataFragment).commit();
        setSelectStatus(1);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @SuppressLint("MissingSuperCall")
    public void onNewIntent(Intent intent) {
        mTagText = NFCManage.readNfcTag(intent);
        String isNFCExist = NFCManage.isNFCExist(mTagText);
        if (isNFCExist==null){
            actType="吃饭";
            actName="吃中饭";
            WriteSysFile();//调用函数
            Intent testIntent = new Intent(HomePageActivity.this, ClockActivity.class);
            testIntent.putExtra("username",userName);
            startActivityForResult (testIntent, 1);
                tv_prompt.setVisibility(View.VISIBLE);
            layoutParams.setMargins(0, 200, 0, 0);

//            Toast.makeText(this,"计时",Toast.LENGTH_LONG).show();
        }
    }

    public void WriteSysFile() {
        String startTime = getTime.getNowTime();
        Long lstartTime = getTime.getStartTime();
//        String write ="写入数据";

        //public void save(String EditText){//inputText为传入的要保存的数据
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = this.openFileOutput("data", Context.MODE_APPEND);//"data"为文件名，第二个参数为文件操作模式：文件已经存在，就往文件里面追加类容，不从新创建文件。
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(startTime+","+lstartTime+","+actType+","+actName+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                String result = data.getStringExtra("result");
                if (result.equals("计时继续")){
                    tv_prompt.setVisibility(View.VISIBLE);
                    layoutParams.setMargins(0, 200, 0, 0);
                }
                else {
                    tv_prompt.setVisibility(View.GONE);
                    layoutParams.setMargins(0, 100, 0, 0);
                }
//                Toast.makeText(HomePageActivity.this,result,Toast.LENGTH_LONG).show();
                break;
        }
    }

}
