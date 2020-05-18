package com.example.guanguannfc.view;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.nfcManagement.BaseNfcActivity;
import com.example.guanguannfc.controller.nfcManagement.NFCManage;
import com.example.guanguannfc.controller.timeManagement.GetTime;
import com.example.guanguannfc.view.data.ClockActivity;
import com.example.guanguannfc.view.data.ClockService;
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

    public static String userName,actType,actName;
    private RelativeLayout main_body;
    private LinearLayout main_bottom_bar;
    private RelativeLayout bottom_bar_1_btn,bottom_bar_2_btn,bottom_bar_3_btn,bottom_bar_4_btn;
    private TextView bottom_bar_text_1,bottom_bar_text_2,bottom_bar_text_3,bottom_bar_text_4;
    private TextView tv_userName,tv_prompt;
    private ImageView bottom_bar_image_1,bottom_bar_image_2,bottom_bar_image_3,bottom_bar_image_4;
    private ConstraintLayout ctl_person,lay_actshow;
    private ConstraintLayout.LayoutParams layoutParams;
    private LinearLayout ll_container;
    private ImageView img_person,img_add;
    private String mTagText;
    private GetTime getTime;
    private ListView lv_add;
    private View popupView;
//    添加好友
    private AddFriendDialog addFriendDialog;
    private String addName;
    PopupWindow addWindow;
    
    private DataFragment dataFragment;
    private PushFragment pushFragment;
    private ManageFragment manageFragment;
    private FrendFragment frendFragment;

//    计时
   public static boolean isCount=false;
   ClockService.MyBinder binder;
   Handler handler;
   Button btn_start;

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            binder = (ClockService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

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
        initDialog();



        Intent intent = new Intent(this,ClockService.class);
        bindService(intent,conn,Context.BIND_AUTO_CREATE);
        handler = new Handler();


    }

    @Override
    protected void onStart() {
        super.onStart();
//            View dataView = dataFragment.getView();
//            tv_prompt = dataView.findViewById(R.id.text_prompt);
//            lay_actshow=dataView.findViewById(R.id.layout_allact);
//            layoutParams = (ConstraintLayout.LayoutParams) lay_actshow.getLayoutParams();

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

//        子fragment
        pushFragment = new PushFragment();
        dataFragment = new DataFragment();
        manageFragment = new ManageFragment();
        frendFragment = new FrendFragment();

//        button
        btn_start=findViewById(R.id.startCount);

//        添加
        img_add=findViewById(R.id.img_add);
        popupView = HomePageActivity.this.getLayoutInflater().inflate(R.layout.item_addwindow, null);
        lv_add = (ListView) popupView.findViewById(R.id.lv_add);
    }

//    创建弹窗
    private void initDialog(){
        addFriendDialog = new AddFriendDialog(HomePageActivity.this);
//        重写按钮响应
        addFriendDialog.setCancel(new AddFriendDialog.IOnCancelListener() {
            @Override
            public void onCancel(AddFriendDialog dialog) {

            }
        });
        addFriendDialog.setConfirm(new AddFriendDialog.IOnConfirmListener() {
            @Override
            public void onConfirm(AddFriendDialog dialog) {

                addName=addFriendDialog.getEditText().getText().toString();
                Toast.makeText(HomePageActivity.this,"请求已发送",Toast.LENGTH_LONG).show();

            }
        });
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
                bundle2.putBoolean("isCount",isCount);
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

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binder.starTimer();
                Intent intent = new Intent(HomePageActivity.this, ClockActivity.class);
                intent.putExtra("username",userName);

                startActivityForResult(intent,2);
            }
        });

        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/5/17 构建一个popupwindow的布局

                lv_add.setAdapter(new ArrayAdapter<String>(HomePageActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.add)));
                // TODO: 2016/5/17 创建PopupWindow对象，指定宽度和高度
                addWindow = new PopupWindow(popupView, 270, 380);
                // TODO: 2016/5/17 设置动画
                addWindow.setAnimationStyle(R.style.popup_window_anim);
                // TODO: 2016/5/17 设置背景颜色
                addWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
                // TODO: 2016/5/17 设置可以获取焦点
                addWindow.setFocusable(true);
                // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
                addWindow.setOutsideTouchable(true);
                // TODO：更新popupwindow的状态
                addWindow.update();
                // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
                addWindow.showAsDropDown(img_add, 0, 35);
            }
        });


            lv_add.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i){
                        case 0:
                            addFriendDialog.show();
                            addWindow.dismiss();
                            break;
                    }
                }
            });




    }

    private void setMain() {
        //getSupportFragmentManager() -> beginTransaction() -> add -> (R.id.main_boy,显示课程 new CourseFragment()
        Bundle bundle = new Bundle();
        bundle.putString("username",userName);
        bundle.putBoolean("isCount",isCount);
        dataFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_body,dataFragment).commit();
        setSelectStatus(1);

    }

    @Override
    public void onResume() {
        super.onResume();
//        Toast.makeText(HomePageActivity.this,"onResume"+isCount,Toast.LENGTH_LONG).show();
    }

    @SuppressLint("MissingSuperCall")
    public void onNewIntent(Intent intent) {
        mTagText = NFCManage.readNfcTag(intent);
        String isNFCExist = NFCManage.isNFCExist(mTagText);
        if (isNFCExist==null){
            isCount=true;
//            跳转传值
            actType="学习";
            actName="学英语";
            binder.starTimer();
            Intent startIntent = new Intent(HomePageActivity.this, ClockActivity.class);
            startIntent.putExtra("username",userName);
            startActivityForResult(startIntent,2);


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
            case 2:
                String result = data.getStringExtra("result");
                if (result.equals("计时继续")){
                    isCount = true;
                }
                else {
                    isCount = false;
                }
                break;
//                Toast.makeText(HomePageActivity.this,result,Toast.LENGTH_LONG).show();

            //                Toast.makeText(HomePageActivity.this,result,Toast.LENGTH_LONG).show();
        }
    }

}
