package com.example.guanguannfc.view.data;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.guanguannfc.R;

import com.example.guanguannfc.controller.dataVisualization.EchartOptionUtil;
import com.example.guanguannfc.controller.dataVisualization.EchartView;
import com.example.guanguannfc.controller.timeManagement.GetTime;
import com.example.guanguannfc.view.loginAndLogon.LoginActivity;

import com.example.guanguannfc.view.management.BoxmanagementActivity;
import com.example.guanguannfc.controller.dataVisualization.datadisplay;
import com.example.guanguannfc.controller.dataVisualization.Allactivity;


import java.io.Console;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Data extends AppCompatActivity {



//    private Datashow frag_datashow = new Datashow();
//    private DataShow frag_datashow = new DataShow();

    private WebView webView;
    private Spinner spinner_times,spinner_types,spinner_acts,spinner_sorts;
    private ConstraintLayout lay_datashow,lay_actshow,lay_time,lay_personset;
    private Button bt_starttime,bt_endtime,bt_acttype,bt_confirmtime,bt_person,bt_manage,bt_quit;
    private TextView tv_prompt,tv_noInfo;
    private String userName,txt_timeType,txt_showType,txt_startTime,txt_endTime,txt_actType;
    public String txt_showActType,txt_sortType;
    private String[] allActName;
    private String[][] actAndTime;
    private Object[] ob_dataShow;
    private String[][] ob_actShow;
    private String[][] actInfo;
    private Object[] echart_act;
    private Object[] echart_time;
    private EchartView myWebView;
    private datadisplay dd=new datadisplay(this);
    private Allactivity allactivity=new Allactivity(this);
    private GetTime getTime=new GetTime();
    private List<DataShow> dataShowList = new ArrayList<DataShow>();
    private ListView actlist;
    private DataShowAdapter dataShowAdapter;
    private List<ActShow> actShowList = new ArrayList<ActShow>();
    private ListView lv_allactlist = null;
    private ActShowAdapter actShowAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Bundle bundle = this.getIntent().getExtras();
        userName=bundle.getString("userName");
//        Toast.makeText(Data.this,"用户名"+userName,Toast.LENGTH_LONG).show();
        initView();



        actlist.setAdapter(dataShowAdapter);
        lv_allactlist.setAdapter(actShowAdapter);



//        initDataShow(datas);
//        initDataShow();
        initSpinner();
        initWebView();







    }


    private void initView(){
        actlist=findViewById(R.id.listview_actlist);
        lv_allactlist=findViewById(R.id.lv_allacts);
        dataShowAdapter = new DataShowAdapter(Data.this,R.layout.datashow_item,dataShowList);
        actShowAdapter = new ActShowAdapter(Data.this,R.layout.actshow_item,actShowList);
        myWebView=findViewById(R.id.webview_acts);
        spinner_times=findViewById(R.id.spinner_time);
        spinner_types=findViewById(R.id.spinner_type);
        spinner_acts=findViewById(R.id.spinner_acttype);
        spinner_sorts=findViewById(R.id.spinner_sort);
        lay_datashow=findViewById(R.id.layout_show);
        lay_actshow=findViewById(R.id.layout_allact);
        lay_actshow.setVisibility(View.INVISIBLE);
        lay_time=findViewById(R.id.layout_chosetime);
        lay_time.setVisibility(View.INVISIBLE);
        lay_personset=findViewById(R.id.layout_person);
        lay_personset.setVisibility(View.INVISIBLE);
        bt_starttime=findViewById(R.id.button_starttime);
        bt_endtime=findViewById(R.id.button_endtime);
        bt_acttype=findViewById(R.id.button_acttype);
        bt_confirmtime=findViewById(R.id.button_time_confirm);
        bt_person=findViewById(R.id.button_personset);
        bt_manage=findViewById(R.id.button_manage);
        bt_quit=findViewById(R.id.button_quit);
        tv_prompt=findViewById(R.id.text_prompt);
        tv_noInfo=findViewById(R.id.text_noInfo);
        txt_actType="";
        txt_showActType="全部";
        txt_sortType="最新活动在前";
        txt_timeType="今日";
        txt_showType="列表";
        txt_startTime=getTime.getBeginTime("本日");
        txt_endTime=txt_startTime;




        allActName=allactivity.allacttype(userName);
        ArrayAdapter<String> actAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,allActName);
        spinner_acts.setAdapter(actAdapter);

    }

    private void initSpinner(){

        spinner_times.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String timeType = Data.this.getResources().getStringArray(R.array.times)[position];
                txt_timeType=timeType;
//                tv_noInfo.setVisibility(View.GONE);
//                actlist.setVisibility(View.VISIBLE);

                if (position==3){
                    lay_time.setVisibility(View.VISIBLE);
                }
                else {
                   if(position==0){
                        txt_startTime=getTime.getBeginTime("本日");
                        txt_endTime=getTime.getBeginTime("本日");
//                        Toast.makeText(Data.this,txt_startTime,Toast.LENGTH_LONG).show();

                    }
                    else if(position==1){
                        txt_startTime=getTime.getBeginTime("本周");
                        txt_endTime=getTime.getBeginTime("本日");
//                        Toast.makeText(Data.this,txt_startTime,Toast.LENGTH_LONG).show();
                    }
                    else if(position==2){
                        txt_startTime=getTime.getBeginTime("本月");
                        txt_endTime=getTime.getBeginTime("本日");
//                        Toast.makeText(Data.this,txt_startTime,Toast.LENGTH_LONG).show();
                    }
//                    Toast.makeText(Data.this,txt_startTime+txt_endTime,Toast.LENGTH_LONG).show();

                    ob_dataShow=dd.Datadplay(userName,txt_startTime,txt_endTime,txt_actType,txt_showType);
//                    Log.i("gy",ob_dataShow[0].toString());

                    if (ob_dataShow!=null){
//                        tv_noInfo.setVisibility(View.GONE);
                        actlist.setVisibility(View.VISIBLE);
                        tv_noInfo.setVisibility(View.GONE);
                        actAndTime=(String[][])ob_dataShow[0];
                        initDataShow(actAndTime);
                        actlist.setAdapter(dataShowAdapter);
//                        String url=(String)ob_dataShow[1];
//                        myWebView.loadUrl(url);
                        Log.i("gy","获取到数据");

                    }
                    else{
                        Log.i("gy","没有获取到数据");
                        actlist.setVisibility(View.INVISIBLE);
                        tv_noInfo.setVisibility(View.VISIBLE);
//                        actlist.setVisibility(View.INVISIBLE);
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String showType = Data.this.getResources().getStringArray(R.array.types)[position];
//                Toast.makeText(Data.this,""+position,Toast.LENGTH_LONG).show();
                txt_showType=showType;
                Log.i("gy","showType:"+txt_showType);
                ob_dataShow=dd.Datadplay(userName,txt_startTime,txt_endTime,txt_actType,txt_showType);
                String[][] array=(String[][])ob_dataShow[0];

                if (position==0){
                    webView.setVisibility(View.INVISIBLE);
                    actlist.setVisibility(View.VISIBLE);

                }
                else{
                    webView.setVisibility(View.VISIBLE);
                    actlist.setVisibility(View.INVISIBLE);

                }

                if (ob_dataShow!=null){
                    actAndTime=(String[][])ob_dataShow[0];
                    initDataShow(actAndTime);
                    actlist.setAdapter(dataShowAdapter);
//                    String url=(String)ob_dataShow[1];
//                    myWebView.loadUrl(url);
                    int len=array.length;
                    echart_act=new Object[len];
                    echart_time=new Object[len];
                    for(int i = 0;i<len;i++){
                        echart_act[i]=array[i][0];
                        echart_time[i]=Long.parseLong(array[i][2]);
                    }
                    if(position==1){
//                        条状图
                        myWebView.setWebViewClient(new WebViewClient(){
                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);
                                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
                                myWebView.refreshEchartsWithOption(EchartOptionUtil.getBarChartOptions(echart_act, echart_time));
                            }
                        });
                    }
                    if(position==2) {
//                        折线图
                        myWebView.setWebViewClient(new WebViewClient() {
                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);
                                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
                                myWebView.refreshEchartsWithOption(EchartOptionUtil.getLineChartOptions(echart_act, echart_time));
                            }
                        });
                    }

                }
                else{
                    actlist.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_acts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String showActType = parent.getItemAtPosition(position).toString();
                txt_showActType=showActType;
//                Toast.makeText(Data.this,txt_showActType+txt_sortType,Toast.LENGTH_LONG).show();

                ob_actShow=allactivity.sortedactivity(userName,txt_showActType,txt_sortType);

                if (ob_actShow !=null){
                    initActShow(ob_actShow);
                    lv_allactlist.setAdapter(actShowAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        spinner_sorts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sortType = parent.getItemAtPosition(position).toString();
                txt_sortType=sortType;
//                Toast.makeText(Data.this,txt_sortType,Toast.LENGTH_LONG).show();

                ob_actShow=allactivity.sortedactivity(userName,txt_showActType,txt_sortType);

                if (ob_actShow !=null){
                    initActShow(ob_actShow);
                    lv_allactlist.setAdapter(actShowAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        处理两次点击同一个选项
        spinner_times.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    Class<?> clazz = AdapterView.class;
                    Field field = clazz.getDeclaredField("mOldSelectedPosition");
                    field.setAccessible(true);
                    field.setInt(spinner_times,AdapterView.INVALID_POSITION);
                } catch(Exception e){
                    e.printStackTrace();
                }
                return false;
            }
        });





    }

//    private void initDataShow(){
//        for(int i=0;i<dataType.length;i++){
//            DataShow dataShow = new DataShow(dataType[i],dataTime[i]);
//            dataShowList.add(dataShow);
//        }
//    }
    private void initDataShow(String[][] array){
        tv_noInfo.setVisibility(View.GONE);
        dataShowList.clear();
        for(int i=0;i<array.length;i++){
            DataShow dataShow = new DataShow(array[i][0],array[i][1]);
            dataShowList.add(dataShow);
        }
    }

    private void initActShow(String[][] array){
        actShowList.clear();
        for(int i=0;i<array.length;i++){
            ActShow actShow = new ActShow(array[i]);
            actShowList.add(actShow);
        }
    }

    private void initWebView(){
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                } else {
                    view.loadUrl(request.toString());
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        //进行webwiev的一堆设置
        // 开启本地文件读取（默认为true，不设置也可以）
        myWebView.getSettings().setAllowFileAccess(true);
        //开启脚本支持
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("file:///android_asset/echart/myechart.html");
    }

    public void click(View v){
        int id=v.getId();
        switch (id) {
            case R.id.text_show:
                lay_datashow.setVisibility(View.VISIBLE);
                lay_actshow.setVisibility(View.INVISIBLE);
                break;
            case R.id.text_allact:
                lay_datashow.setVisibility(View.INVISIBLE);
                lay_actshow.setVisibility(View.VISIBLE);
                break;
            case R.id.button_starttime:
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String starttime =  year + "-" + (month + 1) + "-" + dayOfMonth ;
                        txt_startTime=starttime;
                        bt_starttime.setText(starttime);
                    }
                }
                        , calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.button_endtime:
                Calendar calendar1 = Calendar.getInstance();
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String endtime =  year + "-" + (month + 1) + "-" + dayOfMonth ;
                        txt_endTime=endtime;
                        bt_endtime.setText(endtime);
                    }
                }
                        , calendar1.get(Calendar.YEAR)
                        , calendar1.get(Calendar.MONTH)
                        , calendar1.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.button_acttype:
                PopupMenu actmenu=new PopupMenu(Data.this,v);
                actmenu.inflate(R.menu.actmenu);
                actmenu.show();
                actmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String acttype=(String)menuItem.getTitle();
                        bt_acttype.setText(acttype);
                        if(acttype.equals("全部")){
                            txt_actType="";
                        }
                        else {
                            txt_actType=acttype;

                        }
//                        switch (menuItem.getItemId()){
//                            case R.id.all:
//                                bt_acttype.setText("全部");
//                                break;
//                            case R.id.work:
//                                bt_acttype.setText("工作");
//                                break;
//                        }
                        return false;
                    }
                });
                break;
            case R.id.button_time_confirm:
                lay_time.setVisibility(View.INVISIBLE);
                ob_dataShow=dd.Datadplay(userName,txt_startTime,txt_endTime,txt_actType,txt_showType);
//                Toast.makeText(Data.this,userName+txt_startTime+txt_endTime+txt_actType+txt_showType,Toast.LENGTH_LONG).show();

                if (ob_dataShow!=null){
                    actlist.setVisibility(View.VISIBLE);
                    tv_noInfo.setVisibility(View.GONE);
                    actAndTime=(String[][])ob_dataShow[0];
//                    Toast.makeText(Data.this,actAndTime.length,Toast.LENGTH_LONG).show();
                    initDataShow(actAndTime);
                    actlist.setAdapter(dataShowAdapter);
                    String url=(String)ob_dataShow[1];
                    myWebView.loadUrl(url);

                }
                else{
                    tv_noInfo.setVisibility(View.VISIBLE);
                    actlist.setVisibility(View.INVISIBLE);
                }
//                initDataShow(datas);
//                actlist.setAdapter(dataShowAdapter);

                break;
            case R.id.button_personset:
                if(lay_personset.getVisibility()==View.VISIBLE){
                    lay_personset.setVisibility(View.INVISIBLE);
                }
                else{
                    lay_personset.setVisibility(View.VISIBLE);

                }
                break;
            case R.id.button_manage:

                Intent intent1 = new Intent();
                intent1.setClass(Data.this, BoxmanagementActivity.class);
                startActivity(intent1);
                break;
            case R.id.button_quit:
                Intent intent2 = new Intent();
                intent2.setClass(Data.this, LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.text_prompt:
                Intent intent3 = new Intent();
                intent3.setClass(Data.this, ClockActivity.class);
                startActivity(intent3);

                break;

        }

        }


    }



