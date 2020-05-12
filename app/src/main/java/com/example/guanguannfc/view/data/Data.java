package com.example.guanguannfc.view.data;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.guanguannfc.R;

import com.example.guanguannfc.controller.dataVisualization.EchartOptionUtil;
import com.example.guanguannfc.controller.dataVisualization.EchartView;
import com.example.guanguannfc.controller.timeManagement.GetTime;
import com.example.guanguannfc.view.loginAndLogon.LoginActivity;

import com.example.guanguannfc.controller.dataVisualization.datadisplay;
import com.example.guanguannfc.controller.dataVisualization.Allactivity;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Data extends AppCompatActivity {



//    private Datashow frag_datashow = new Datashow();
//    private DataShow frag_datashow = new DataShow();


    private Spinner spinner_times,spinner_types,spinner_acts,spinner_sorts;
    private ConstraintLayout lay_datashow,lay_actshow,lay_time,lay_personset;
    private Button bt_starttime,bt_endtime,bt_acttype,bt_confirmtime,bt_person,bt_manage,bt_quit;
    private TextView tv_prompt,tv_noInfo,tv_noActInfo;
    private String userName,txt_timeType,txt_showType,txt_startTime,txt_endTime,txt_actType;
    public String txt_showActType,txt_sortType;
    private String[] allActName;
    private String[][] actAndTime;
    private Object[] ob_dataShow;
    private Object echart_act[];
    private Object echart_time[];
    private String[][] ob_actShow;
    private String[][] actInfo;
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
    private Object[] x = new Object[]{
            "Mon", "Tue", "Wed", "Thu", "Fri"
    };
    private Object[] y = new Object[]{
            820, 932, 901, 934, 1290
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
//        获取用户名
        Bundle bundle = this.getIntent().getExtras();

        userName=bundle.getString("userName");


        Toast.makeText(Data.this,"用户名"+userName,Toast.LENGTH_LONG).show();
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
//        bt_person=findViewById(R.id.button_personset);
//        bt_manage=findViewById(R.id.button_manage);
        bt_quit=findViewById(R.id.button_quit);
        tv_prompt=findViewById(R.id.text_prompt);
        tv_noInfo=findViewById(R.id.text_noInfo);
        tv_noActInfo=findViewById(R.id.text_noActInfo);

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
//                    Log.i("gy","time获取到数据"+txt_timeType+""+txt_showType);



                    if (ob_dataShow!=null){

                        tv_noInfo.setVisibility(View.GONE);
                        actAndTime=(String[][])ob_dataShow[0];

                        if (txt_showType.equals("列表")) {
                            actlist.setVisibility(View.VISIBLE);
                            Log.i("gy","获取到数据"+txt_timeType+""+txt_showType);

                            initDataShow(actAndTime);
                            actlist.setAdapter(dataShowAdapter);
                        }
                        else {
                            myWebView.setVisibility(View.VISIBLE);
                            refreshEChart();
                            int len = actAndTime.length;
                            echart_act = new Object[len];
                            echart_time= new Object[len];
                            for(int i =0;i<len;i++){
                                echart_act[i]=actAndTime[i][0];
                                echart_time[i]=((float[]) ob_dataShow[1])[i];
                            }
                            if (txt_showType.equals("柱状图")){
                                Log.i("gy","获取到数据"+txt_timeType+""+txt_showType);
                                myWebView.refreshEchartsWithOption(EchartOptionUtil.getBarChartOptions(echart_act,echart_time));
                            }
                            else if (txt_showType.equals("折线图")){
                                Log.i("gy","获取到数据"+txt_timeType+""+txt_showType);
                                myWebView.refreshEchartsWithOption(EchartOptionUtil.getLineChartOptions(echart_act, echart_time));
                            }
                        }



                    }
                    else{
                        Log.i("gy","没有获取到数据");
                        actlist.setVisibility(View.INVISIBLE);
                        myWebView.setVisibility(View.INVISIBLE);
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

                if (position==0){
                    myWebView.setVisibility(View.INVISIBLE);
                    actlist.setVisibility(View.VISIBLE);

                }
                else{
                    myWebView.setVisibility(View.VISIBLE);
                    actlist.setVisibility(View.INVISIBLE);

                }
                ob_dataShow=dd.Datadplay(userName,txt_startTime,txt_endTime,txt_actType,txt_showType);
                if (ob_dataShow!=null){
                    if (position==0){
                        actAndTime=(String[][])ob_dataShow[0];
                        initDataShow(actAndTime);
                        actlist.setAdapter(dataShowAdapter);
                    }
                    else {
                        refreshEChart();
                        int len = actAndTime.length;
                        echart_act = new Object[len];
                        echart_time= new Object[len];
                        for(int i =0;i<len;i++){
                            echart_act[i]=actAndTime[i][0];
                            echart_time[i]=((float[]) ob_dataShow[1])[i];
                        }

                        Log.i("gy","act:"+echart_act[0]+"time:"+echart_time[0]);

                        if(position == 1){
                            myWebView.refreshEchartsWithOption(EchartOptionUtil.getBarChartOptions(echart_act,echart_time));

                        }
                        if(position == 2){
                            myWebView.refreshEchartsWithOption(EchartOptionUtil.getLineChartOptions(echart_act, echart_time));

                        }
                    }

                }
                else{
                    actlist.setVisibility(View.INVISIBLE);
                    myWebView.setVisibility(View.INVISIBLE);
                    tv_noInfo.setVisibility(View.VISIBLE);
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
                    lv_allactlist.setVisibility(View.VISIBLE);
                    tv_noActInfo.setVisibility(View.GONE);
                    initActShow(ob_actShow);
                    lv_allactlist.setAdapter(actShowAdapter);
                }
                else {
                    lv_allactlist.setVisibility(View.GONE);
                    tv_noActInfo.setVisibility(View.VISIBLE);
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

    private void refreshEChart(){
        Log.i("gy","刷新图表");
        echart_time=new Object[0];
        echart_act=new Object[0];

    }



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

//设置载入页面自适应手机屏幕，居中显示
        WebSettings mWebSettings = myWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
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
//                        txt_startTime=starttime;
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
//                        txt_endTime=endtime;
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

                        return false;
                    }
                });
                break;
            case R.id.button_time_confirm:
                txt_startTime=(String) bt_starttime.getText();
                txt_endTime=(String) bt_endtime.getText();

                lay_time.setVisibility(View.INVISIBLE);
                ob_dataShow=dd.Datadplay(userName,txt_startTime,txt_endTime,txt_actType,txt_showType);
//                Toast.makeText(Data.this,userName+txt_startTime+txt_endTime+txt_actType+txt_showType,Toast.LENGTH_LONG).show();

                if (ob_dataShow!=null){

                    actlist.setVisibility(View.VISIBLE);
                    tv_noInfo.setVisibility(View.GONE);
                    actAndTime=(String[][])ob_dataShow[0];
                    if (txt_showType.equals("列表")) {
                        Log.i("gy","获取到数据"+txt_timeType+""+txt_showType);

                        initDataShow(actAndTime);
                        actlist.setAdapter(dataShowAdapter);
                    }
                    else {
                        refreshEChart();
                        int len = actAndTime.length;
                        echart_act = new Object[len];
                        echart_time= new Object[len];
                        for(int i =0;i<len;i++){
                            echart_act[i]=actAndTime[i][0];
                            echart_time[i]=((float[]) ob_dataShow[1])[i];
                        }
                        if (txt_showType.equals("柱状图")){
                            Log.i("gy","获取到数据"+txt_timeType+""+txt_showType);
                            myWebView.refreshEchartsWithOption(EchartOptionUtil.getBarChartOptions(echart_act,echart_time));
                        }
                        else if (txt_showType.equals("折线图")){
                            Log.i("gy","获取到数据"+txt_timeType+""+txt_showType);
                            myWebView.refreshEchartsWithOption(EchartOptionUtil.getLineChartOptions(echart_act, echart_time));
                        }
                    }


                }
                else{
                    tv_noInfo.setVisibility(View.VISIBLE);
                    actlist.setVisibility(View.INVISIBLE);
                    myWebView.setVisibility(View.INVISIBLE);
                }
//                initDataShow(datas);
//                actlist.setAdapter(dataShowAdapter);

                break;
//            case R.id.button_personset:
//                if(lay_personset.getVisibility()==View.VISIBLE){
//                    lay_personset.setVisibility(View.INVISIBLE);
//                }
//                else{
//                    lay_personset.setVisibility(View.VISIBLE);
//
//                }
//                break;
//            case R.id.button_manage:
//
//                Intent intent1 = new Intent();
//                intent1.setClass(Data.this, BoxmanagementActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putString("userName",userName);
//                intent1.putExtras(bundle);
//                startActivity(intent1);
//                break;
            case R.id.button_quit:
                Intent intent2 = new Intent();
                intent2.setClass(Data.this, LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.text_prompt:
                Intent intent3 = new Intent();
                intent3.setClass(Data.this, ClockActivity.class);
                intent3.putExtra("username",userName);
                startActivityForResult (intent3, 1);

                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                String result = data.getStringExtra("result");
                Toast.makeText(this,result,Toast.LENGTH_LONG).show();
        }
    }



}



