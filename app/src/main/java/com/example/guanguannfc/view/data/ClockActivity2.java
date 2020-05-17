package com.example.guanguannfc.view.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.timeManagement.GetTime;

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
    private String startTime,nowTime,duration,isFirst;
    private Long lstartTime;
    private Boolean iscount=true;
    private GetTime getTime;
    private String TimeInfo;
    private String[] infos;

//    private MyReceiver receiver=null;
//    private IntentFilter filter;
    String userName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        btn_suspend=findViewById(R.id.btn_suspend);
        btn_stop=findViewById(R.id.btn_stop);
        tv_start_time=findViewById(R.id.tv_start_time);
        tv_now_time=findViewById(R.id.tv_now_time);
        tv_duration=findViewById(R.id.tv_duration);

//      获取传入数值
        Intent intent=getIntent();
        userName=intent.getStringExtra("username");
//        isFirst=intent.getStringExtra("isfirst");

//        infos=initAssets();
//        读取开始时间
        infos=read();
        startTime=(infos[infos.length-1].split(","))[0];
        lstartTime= Long.parseLong((infos[infos.length-1].split(","))[1]);

        tv_start_time.setText(startTime);






//        Toast.makeText(this,"开始计时",Toast.LENGTH_LONG).show();
        Toast.makeText(this,userName,Toast.LENGTH_LONG).show();



    }

    public void click(View v){
        int id=v.getId();
        switch (id){
            case R.id.btn_stop:

                break;
            case R.id.btn_share:
                break;

        }
    }

    public void onBackPressed() {

        Intent intent = new Intent();
//        intent.putExtra("result","计时继续");
//        if (isFirst.equals("true")){
//            intent.putExtra("result","1");
//            this.setResult(1,intent);
//            this.finish();
//        }
//        else {
            intent.putExtra("result","2");
            this.setResult(2,intent);
            finish();
//        }
    }




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
