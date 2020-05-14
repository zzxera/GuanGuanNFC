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
import com.example.guanguannfc.controller.dataVisualization.Allactivity;
import com.example.guanguannfc.controller.timeManagement.GetTime;
import com.example.guanguannfc.view.HomePageActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class ClockActivity extends AppCompatActivity {
    private Button btn_suspend,btn_stop;
    private TextView tv_start_time,tv_now_time,tv_distance,tv_duration;
    private String startTime,nowTime,duration,isFirst;
    private Long lstartTime,date,endTime;
    private GetTime getTime;
    private Boolean iscount;
    String userName,actType,actName;
    private String TimeInfo;
    private String[] infos;
    private Allactivity allactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        Intent intent=getIntent();
        userName=intent.getStringExtra("username");
        date=1111111l;
//        isFirst=intent.getStringExtra("isfirst");
        iscount=true;

        btn_suspend=findViewById(R.id.btn_suspend);
        btn_stop=findViewById(R.id.btn_stop);
        tv_start_time=findViewById(R.id.tv_start_time);
        tv_now_time=findViewById(R.id.tv_now_time);
        tv_duration=findViewById(R.id.tv_duration);

        allactivity = new Allactivity(this);

        infos=read();
        startTime=(infos[infos.length-1].split(","))[0];
        lstartTime= Long.parseLong((infos[infos.length-1].split(","))[1]);
        actType=(infos[infos.length-1].split(","))[2];
        actName=(infos[infos.length-1].split(","))[3];
        tv_start_time.setText(startTime);


//        Toast.makeText(this,"开始计时",Toast.LENGTH_LONG).show();
//        Toast.makeText(this,userName,Toast.LENGTH_LONG).show();

        handler.post(UpdateThread);



    }

    public void click(View v){
        int id=v.getId();
        switch (id){
//            case R.id.btn_suspend:
//                if (iscount){
//                    handler.removeCallbacks(UpdateThread);
//                    iscount=false;
//                    btn_suspend.setText("继续计时");
//                    Toast.makeText(this,"计时已暂停",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    handler.post(UpdateThread);
//                    iscount=true;
//                    btn_suspend.setText("暂停计时");
//                    Toast.makeText(this,"计时继续",Toast.LENGTH_SHORT).show();
//                }
//                break;
            case R.id.btn_stop:
                handler.removeCallbacks(UpdateThread);
                iscount=false;
                HomePageActivity.isCount=false;
                endTime=getTime.getStartTime();
                boolean isSuccess=allactivity.insertdata(userName,actType,actName,date, lstartTime,endTime);
                if (isSuccess) {
                    Toast.makeText(this,"计时结束",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(this,"数据记录失败",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void onBackPressed() {
        handler.removeCallbacks(UpdateThread);
        if(iscount){
            handler.removeCallbacks(UpdateThread);
            Intent intent = new Intent();
            intent.putExtra("result","计时继续");
            this.setResult(1,intent);
            this.finish();
        }
        else {
            handler.removeCallbacks(UpdateThread);
            Intent intent = new Intent();
            intent.putExtra("result","计时结束");
            this.setResult(1,intent);
            this.finish();
        }

    }


    @Override
    protected void onDestroy() {
        //结束服务
//        stopService(new Intent(ClockActivity.this, ClockService.class));
        super.onDestroy();
        handler.removeCallbacks(UpdateThread);

    }

//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        iscount=true;
//    }

    Handler handler = new Handler() {
        @Override
        /**handler接收到UpdateProgressBar的SendMessage方法传递来的消息后
         * 当从消息队列里面取出消息时会执行此方法*/
        public void handleMessage(Message msg) {
            /**从传递过来的msg中得到arg1，并把这个值设置成当前的进度条*/
//            tv_now_time.setText(msg.getData().getString("nowtime"));
//            tv_duration.setText(msg.getData().getString("duration"));
            /**把UpdateThread线程加入线程队列*/
            handler.post(UpdateThread);
        }
    };
    Runnable UpdateThread = new Runnable() {
        int bar = 0;
        @Override
        public void run() {
            System.out.println("Run UpdateThread!");
            nowTime=getTime.getNowTime();
            duration=getTime.countTime(lstartTime);
            bar += 1;
            tv_now_time.setText(nowTime);
            tv_duration.setText(duration);
            /**得到一个消息对象
             * 该消息对象由UpdateProgressBar的obtainMessage提供
             * */

                Bundle bundle = new Bundle();
                bundle.putString("nowtime",nowTime);  //往Bundle中存放数据
                bundle.putString("duration",duration);
                Message msg = handler.obtainMessage();
                msg.setData(bundle);
//            msg.arg2 = bar;
                try{
                    /**设置当前的线程睡眠1000ms*/
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.sendMessage(msg);


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
