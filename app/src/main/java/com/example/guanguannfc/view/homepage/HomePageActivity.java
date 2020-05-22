package com.example.guanguannfc.view.homepage;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.LayoutInflater;
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
import com.example.guanguannfc.controller.dataManagement.ThingManage;
import com.example.guanguannfc.controller.dataVisualization.Allactivity;
import com.example.guanguannfc.controller.nfcManagement.BaseNfcActivity;
import com.example.guanguannfc.controller.nfcManagement.NFCManage;
import com.example.guanguannfc.controller.timeManagement.GetTime;
import com.example.guanguannfc.view.data.ClockActivity;
import com.example.guanguannfc.view.data.ClockService;
import com.example.guanguannfc.view.data.DataFragment;
import com.example.guanguannfc.view.friends.FrendFragment;
import com.example.guanguannfc.view.friends.FriendRequestActivity;
import com.example.guanguannfc.view.loginAndLogon.LoginActivity;
import com.example.guanguannfc.view.mainInterface.PushFragment;
import com.example.guanguannfc.view.management.ManageFragment;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

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

    List<OneTextItem> oneTextItemList = new ArrayList<OneTextItem>();
    OneTextAdapter oneTextAdapter;

//    计时
   public static boolean isCount=false;
   ClockService.MyBinder binder;
   Handler handler;
   Button btn_start;

//   添加盒子
    private NFCManage nfcManage;
    private ThingManage thingManage;
    private boolean isAddBox=false;
    Dialog addBoxDialog,addGoodDialog,scanNFCDialog;
    private String boxName,boxPosition,goodName,goodNum;
    View addBoxView,addGoodView,scanNFCView;
    private ArrayList<String> goodsName=new ArrayList<String>();
    private ArrayList<Integer> goodsNum=new ArrayList<Integer>();


    private ListView lv_goods;
    private GoodAdapter goodAdapter;
    private List<GoodItem> goodItemList  = new ArrayList<GoodItem>();

//    添加活动
    private boolean isAddAct=false;
    Dialog addActDialog;
    View addActView;
    private String addActType,addActName;
    private String[] allActs;
    private Allactivity allactivity;

//    擦除NFC
    private boolean isDelete=false;



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
        oneTextAdapter = new OneTextAdapter(this,R.layout.item_onetext,oneTextItemList);


//       NFC
        nfcManage = new NFCManage(userName,this);
        thingManage = new ThingManage(userName,this);
        goodAdapter = new GoodAdapter(this,R.layout.item_datashow,goodItemList);
        allactivity = new Allactivity(this);
        String[] arry =  allactivity.allacttype(userName);
        allActs = new String[arry.length-1];
        for (int t=1;t<arry.length;t++){
            allActs[t-1]=arry[t];
        }
    }

//    创建弹窗
    private void initDialog(){
        addFriendDialog = new AddFriendDialog(HomePageActivity.this);
//        重写按钮响应
        addFriendDialog.setCancel(new AddFriendDialog.IOnCancelListener() {
            @Override
            public void onCancel(AddFriendDialog dialog) {
                addFriendDialog.getName().setText("");
                addFriendDialog.getRemark().setText("");
            }
        });
        addFriendDialog.setConfirm(new AddFriendDialog.IOnConfirmListener() {
            @Override
            public void onConfirm(AddFriendDialog dialog) {
                addName=addFriendDialog.getName().getText().toString();
                addFriendDialog.getName().setText("");
                addFriendDialog.getRemark().setText("");
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
                bundle3.putString("getboxname","");
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

                startActivityForResult(intent,1);
            }
        });

        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/5/17 构建一个popupwindow的布局

//                lv_add.setAdapter(new ArrayAdapter<String>(HomePageActivity.this, R.layout.item_onetext, getResources().getStringArray(R.array.add)));
                String[] al = getResources().getStringArray(R.array.add);
                for (int i = 0;i<al.length;i++){
                    OneTextItem oneTextItem = new OneTextItem(al[i]);
                    oneTextItemList.add(oneTextItem);
                }
                lv_add.setAdapter(oneTextAdapter);

                // TODO: 2016/5/17 创建PopupWindow对象，指定宽度和高度
                addWindow = new PopupWindow(popupView, 350, 600);
                // TODO: 2016/5/17 设置动画
                addWindow.setAnimationStyle(R.style.popup_window_anim);
                // TODO: 2016/5/17 设置背景颜色
                addWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#333333")));
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
//                            添加好友
                            addFriendDialog.show();
                            addWindow.dismiss();
                            break;
                        case 1:
//                            查看好友请求
                            Intent intent = new Intent();
                            intent.setClass(HomePageActivity.this, FriendRequestActivity.class);
                            intent.putExtra("username",userName);
                            startActivity(intent);
                            addWindow.dismiss();
                            break;
                        case 2:
//                            添加活动
                            addWindow.dismiss();

                            LayoutInflater inflater_act=LayoutInflater.from(HomePageActivity.this);
                            addActView=inflater_act.inflate(R.layout.item_addact,null);//引用自定义布局
                            AlertDialog.Builder builder_act=new AlertDialog.Builder(HomePageActivity.this);
                            builder_act.setView( addActView );
                            addActDialog=builder_act.create();//创建对话框
                            addActDialog.show();//显示对话框

                            //                            选择活动大类

                            addActView.findViewById(R.id.et_actType).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    final EditText et_actType = addActView.findViewById(R.id.et_actType);

                                    AlertDialog.Builder builder2=new AlertDialog.Builder(HomePageActivity.this);
                                    builder2.setTitle("选择活动类型");
                                    builder2.setSingleChoiceItems(allActs, 1, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
//                                            Toast.makeText( HomePageActivity.this, allActs[which], Toast.LENGTH_SHORT ).show();
                                            et_actType.setText(allActs[which]);

                                            dialog.dismiss();
                                        }
                                    });
                                    builder2.setCancelable(false);
                                    builder2.show();
                                }
                            });
//                          确认添加活动
                            addActView.findViewById(R.id.btn_confirmAdd).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EditText et_actName = addActView.findViewById(R.id.et_actName);
                                    EditText et_actType = addActView.findViewById(R.id.et_actType);
                                    addActType = et_actType.getText().toString();
                                    addActName = et_actName.getText().toString();

                                    if (addActType.equals("")){
                                        Toast.makeText( HomePageActivity.this, "活动类型不能为空", Toast.LENGTH_SHORT ).show();
                                    }
                                    else if (addActName.equals("")){
                                        Toast.makeText( HomePageActivity.this, "活动名称不能为空", Toast.LENGTH_SHORT ).show();
                                    }
                                    else {
                                        isAddAct = true;
//                                        Toast.makeText( HomePageActivity.this, isAddAct+"", Toast.LENGTH_SHORT ).show();
                                        addActDialog.dismiss();//点击按钮对话框消失
                                        LayoutInflater inflater=LayoutInflater.from(HomePageActivity.this);
                                        scanNFCView=inflater.inflate(R.layout.activity_nfcbox,null);//引用自定义布局
                                        AlertDialog.Builder builder=new AlertDialog.Builder(HomePageActivity.this);
                                        builder.setView( scanNFCView );
                                        scanNFCDialog=builder.create();//创建对话框
                                        scanNFCDialog.show();//显示对话框
                                        scanNFCDialog.setCanceledOnTouchOutside(false);
                                        scanNFCDialog.setCancelable(false);
                                        scanNFCView.findViewById(R.id.btn_box_nfc).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                isAddAct = false;
                                                scanNFCDialog.dismiss();
                                                addActDialog.show();
//                                                Toast.makeText( HomePageActivity.this, isAddAct+"", Toast.LENGTH_SHORT ).show();
                                            }
                                        });
                                    }
                                }
                            });





                            break;
                        case 3:
//                            添加盒子
                            addWindow.dismiss();

                            LayoutInflater inflater=LayoutInflater.from(HomePageActivity.this);
                            addBoxView=inflater.inflate(R.layout.item_addbox,null);//引用自定义布局
                            AlertDialog.Builder builder=new AlertDialog.Builder(HomePageActivity.this);
                            builder.setView( addBoxView );
                            addBoxDialog=builder.create();//创建对话框
                            addBoxDialog.show();//显示对话框

                            goodItemList.clear();
                            lv_goods=addBoxView.findViewById(R.id.lv_things);

//                            addBoxDialog.setCanceledOnTouchOutside(false);
//                            添加物品
                            addBoxView.findViewById(R.id.btn_addThing).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    LayoutInflater inflater=LayoutInflater.from(HomePageActivity.this);
                                    addGoodView=inflater.inflate(R.layout.item_addthings,null);//引用自定义布局
                                    AlertDialog.Builder builder=new AlertDialog.Builder(HomePageActivity.this);
                                    builder.setView( addGoodView );
                                    addGoodDialog=builder.create();//创建对话框
                                    addGoodDialog.show();//显示对话框

//                                    确认按钮
                                    addGoodView.findViewById(R.id.btn_confirmGood).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            EditText et1=addGoodView.findViewById(R.id.et_goodName);
                                            EditText et2=addGoodView.findViewById(R.id.et_goodNum);
                                            goodsName.add(et1.getText().toString());
                                            goodsNum.add(Integer.valueOf(et2.getText().toString()) );

                                            String[] arr={et1.getText().toString(),et2.getText().toString()};

                                            GoodItem goodItem = new GoodItem(arr);
                                            goodItemList.add(goodItem);
                                            lv_goods.setAdapter(goodAdapter);
                                            addGoodDialog.dismiss();
                                        }
                                    });
                                }
                            });
//                          确定添加
                            addBoxView.findViewById(R.id.btn_confirmAdd).setOnClickListener( new View.OnClickListener() {//获取布局里面按钮
                                @Override
                                public void onClick(View v) {
                                    EditText et1= addBoxView.findViewById(R.id.et_boxName);
                                    EditText et2= addBoxView.findViewById(R.id.et_boxPosition);
                                    boxName=et1.getText().toString();
                                    boxPosition=et2.getText().toString();

                                    if (boxName.equals("")){
                                        Toast.makeText( HomePageActivity.this, "盒子名称不能为空", Toast.LENGTH_SHORT ).show();
                                    }
                                    else if (boxPosition.equals("")){
                                        Toast.makeText( HomePageActivity.this, "盒子位置不能为空", Toast.LENGTH_SHORT ).show();

                                    }
                                    else {
                                        isAddBox = true;
                                        addBoxDialog.dismiss();//点击按钮对话框消失
                                        LayoutInflater inflater=LayoutInflater.from(HomePageActivity.this);
                                        scanNFCView=inflater.inflate(R.layout.activity_nfcbox,null);//引用自定义布局
                                        AlertDialog.Builder builder=new AlertDialog.Builder(HomePageActivity.this);
                                        builder.setView( scanNFCView );
                                        scanNFCDialog=builder.create();//创建对话框
                                        scanNFCDialog.show();//显示对话框
                                        scanNFCDialog.setCanceledOnTouchOutside(false);
                                        scanNFCDialog.setCancelable(false);

                                        scanNFCView.findViewById(R.id.btn_box_nfc).setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                isAddBox = false;
                                                scanNFCDialog.dismiss();
                                                addBoxDialog.show();
//                                                Toast.makeText( HomePageActivity.this, isAddBox+"", Toast.LENGTH_SHORT ).show();
                                            }
                                        });

//                                        Toast.makeText( HomePageActivity.this, isAddBox+"", Toast.LENGTH_SHORT ).show();
                                    }



//
//                                    Toast.makeText( HomePageActivity.this, boxName, Toast.LENGTH_SHORT ).show();
                                }
                            } );

                            break;
                        case 4:
                            addWindow.dismiss();
                            isDelete=true;
                            LayoutInflater inflater_delete=LayoutInflater.from(HomePageActivity.this);
                            scanNFCView=inflater_delete.inflate(R.layout.activity_nfcbox,null);//引用自定义布局
                            AlertDialog.Builder builder_delete=new AlertDialog.Builder(HomePageActivity.this);
                            builder_delete.setView( scanNFCView );
                            scanNFCDialog=builder_delete.create();//创建对话框
                            scanNFCDialog.show();//显示对话框
                            scanNFCDialog.setCanceledOnTouchOutside(false);
                            scanNFCDialog.setCancelable(false);

                            scanNFCView.findViewById(R.id.btn_box_nfc).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    isDelete = false;
                                    scanNFCDialog.dismiss();
//                                                Toast.makeText( HomePageActivity.this, isAddBox+"", Toast.LENGTH_SHORT ).show();
                                }
                            });
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

    private View showDialog(Dialog dialog, View view, int layout){
        LayoutInflater inflater=LayoutInflater.from(HomePageActivity.this);
        view=inflater.inflate(layout,null);//引用自定义布局
        AlertDialog.Builder builder=new AlertDialog.Builder(HomePageActivity.this);
        builder.setView( view );
        dialog=builder.create();//创建对话框
        dialog.show();//显示对话框
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        Toast.makeText(HomePageActivity.this,"onResume"+isCount,Toast.LENGTH_LONG).show();
    }


//    检测NFC
    @SuppressLint("MissingSuperCall")
    public void onNewIntent(Intent intent) {
        Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (isDelete){
//            清空标签
            Boolean success = nfcManage.setNFCNll(detectedTag);
            if (success){
                Toast.makeText(HomePageActivity.this,"擦除成功",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            mTagText = NFCManage.readNfcTag(intent);
            String isNFCExist = NFCManage.isNFCExist(mTagText);
            if (isNFCExist==null){
                if (isAddBox){
                    Boolean success = nfcManage.setNFCNumberForBox(boxName,boxPosition,detectedTag);
                    if (success){
                        for (int i=0;i<goodsName.size();i++){
                            thingManage.addThings(boxName,goodsName.get(i),goodsNum.get(i));
                        }
                        isAddBox = false;
                        scanNFCDialog.dismiss();
                        Toast.makeText(HomePageActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    }


                }
                else if (isAddAct){
                    Boolean success = nfcManage.setNFCNumberForAct(addActType,addActName,detectedTag);
                    if (success){
                        isAddAct = false;
                        scanNFCDialog.dismiss();
                        Toast.makeText(HomePageActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(HomePageActivity.this,"空标签",Toast.LENGTH_SHORT).show();

                }


            }
            else if (isNFCExist.equals("Act")){
//            Toast.makeText(HomePageActivity.this,"Act",Toast.LENGTH_SHORT).show();
                String[] actInfo = nfcManage.nfcForActivity(mTagText);
            isCount=true;
//            跳转传值
//
            actType=allActs[Integer.parseInt(actInfo[1])-1];
            actName=actInfo[0];
                Toast.makeText(HomePageActivity.this,actType+actName,Toast.LENGTH_SHORT).show();

            binder.starTimer();
            Intent startIntent = new Intent(HomePageActivity.this, ClockActivity.class);
            startIntent.putExtra("username",userName);
            startActivityForResult(startIntent,1);
            }
            else if (isNFCExist.equals("Box")){
//            Toast.makeText(HomePageActivity.this,"Box",Toast.LENGTH_SHORT).show();
                String getBoxName = nfcManage.nfcForBox(mTagText);
                Bundle bundle_manage = new Bundle();
                bundle_manage.putString("username",userName);
                bundle_manage.putString("getboxname",getBoxName);
                manageFragment.setArguments(bundle_manage);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,manageFragment).commit();
                setSelectStatus(2);
//            Toast.makeText(HomePageActivity.this,getBoxName,Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(HomePageActivity.this,"无法识别此NFC内容",Toast.LENGTH_SHORT).show();
            }
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
