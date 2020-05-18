package com.example.guanguannfc.view.data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.dataVisualization.Allactivity;
import com.example.guanguannfc.controller.timeManagement.GetTime;
import com.example.guanguannfc.view.HomePageActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class ClockActivity2 extends AppCompatActivity {
    private Button btn_suspend,btn_stop;
    private TextView tv_start_time,tv_now_time,tv_distance,tv_duration;
    private String startTime,nowTime,duration,isFirst,userName,actType,actName;
    private Long lstartTime,date,endTime;
    private Boolean iscount;
    private GetTime getTime;
    private String TimeInfo;
    private String[] infos;
    private Allactivity allactivity;


    LocalBroadcastManager lbm;
    ClockService.MyBinder binder;




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
        setContentView(R.layout.activity_clock);

        btn_suspend=findViewById(R.id.btn_suspend);
        btn_stop=findViewById(R.id.btn_stop);
        tv_start_time=findViewById(R.id.tv_start_time);
        tv_now_time=findViewById(R.id.tv_now_time);
        tv_duration=findViewById(R.id.tv_duration);
        allactivity = new Allactivity(this);
        actType = "吃饭";
        actName = "吃中饭";
        date=1111111l;

        iscount=true;
//      获取传入数值
        Intent mainIntent=getIntent();
        userName=mainIntent.getStringExtra("username");

//      获取service数值
        Intent intent = new Intent(this,ClockService.class);
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
        lbm = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("clock");
        lbm.registerReceiver(receiver,intentFilter);
//        Toast.makeText(this,userName,Toast.LENGTH_LONG).show();



    }

    public void click(View v){
        int id=v.getId();
        switch (id){
            case R.id.btn_stop:
                Toast.makeText(this,"计时结束",Toast.LENGTH_LONG).show();
                iscount = false;
                HomePageActivity.isCount=false;
                binder.stopTimer();
                endTime=getTime.getStartTime();
                allactivity.insertdata(userName,actType,actName,date, lstartTime,endTime);
                Toast.makeText(this,userName+"-"+actType+"-"+actName+"-"+date+"-"+lstartTime+"-"+endTime,Toast.LENGTH_LONG).show();

//                if (isSuccess) {
//                    Toast.makeText(this,"计时结束",Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(this,"数据记录失败",Toast.LENGTH_LONG).show();
//                }
                break;
            case R.id.btn_share:
                break;

        }
    }

    public void onBackPressed() {
        if(iscount){
            Intent intent = new Intent();
            intent.putExtra("result","计时继续");
            this.setResult(2,intent);
            this.finish();
        }
        else {
            Intent intent = new Intent();
            intent.putExtra("result","计时结束");
            this.setResult(2,intent);
            this.finish();
        }
    }


    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            lstartTime = intent.getLongExtra("lstarttime",0);
            startTime = intent.getStringExtra("starttime");
            nowTime = intent.getStringExtra("nowtime");
            duration = getTime.countTime(lstartTime);
            tv_start_time.setText(startTime);
            tv_now_time.setText(nowTime);
            tv_duration.setText(duration);
        }
    };

    public String[] read(){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
            in=openFileInput("data");
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null){
                content.append(line);
                content.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader!=null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        String str = content.toString();
        String[] arr = str.split("\n");
        return arr;
    }




}
