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

//        startTime=getTime.getStartTime();
//        nowTime=getTime.getNowTime();
//        tv_start_time.setText(nowTime);
//        tv_now_time.setText(getTime.getNowTime());
//        tv_duration.setText(getTime.countTime(getTime.getStartTime()));
//        startTime=ClockService.lStartTime;
//        tv_start_time.setText(ClockService.startTime);




//        Toast.makeText(this,"开始计时",Toast.LENGTH_LONG).show();
        Toast.makeText(this,userName,Toast.LENGTH_LONG).show();
        handler.post(UpdateThread);

//        //注册广播接收器
//        receiver=new MyReceiver();
//        filter=new IntentFilter();
//        filter.addAction("com.example.guanguannfc.view.data.ClockService");
//        registerReceiver(receiver,filter);



    }

    public void click(View v){
        int id=v.getId();
        switch (id){
//            case R.id.btn_suspend:
//                if (iscount){
//                    iscount=false;
//                    btn_suspend.setText("继续计时");
//                    Toast.makeText(this,"计时已暂停",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    iscount=true;
//                    btn_suspend.setText("暂停计时");
//                    Toast.makeText(this,"计时继续",Toast.LENGTH_SHORT).show();
//                }
//                break;
            case R.id.btn_stop:
                handler.removeCallbacks(UpdateThread);
//                Intent intent = new Intent();
//                intent.putExtra("result","本次计时已结束");
//                this.setResult(1,intent);
//                this.finish();
                Toast.makeText(this,"本次计时已结束",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_share:
                handler.post(UpdateThread);

        }
    }

    public void onBackPressed() {
        handler.removeCallbacks(UpdateThread);
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

    @Override
    protected void onDestroy() {
        //结束服务
//        stopService(new Intent(ClockActivity.this, ClockService.class));
        handler.removeCallbacks(UpdateThread);
        super.onDestroy();

    }

//
//    public class MyReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Bundle bundle=intent.getExtras();
//            tv_now_time.setText(bundle.getString("nowTime"));
//            tv_duration.setText(bundle.getString("duration"));
//        }
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

//        int bar = 0;
        @Override
        public void run() {
            System.out.println("Run UpdateThread!");
            nowTime=getTime.getNowTime();
            duration=getTime.countTime(lstartTime);
            tv_now_time.setText(nowTime);
            tv_duration.setText(duration);
//            bar += 1;
            /**得到一个消息对象
             * 该消息对象由UpdateProgressBar的obtainMessage提供
             * */
//            Bundle bundle = new Bundle();
//            bundle.putString("nowtime",nowTime);  //往Bundle中存放数据
//            bundle.putString("duration",duration);
            Message msg = handler.obtainMessage();
//            msg.setData(bundle);
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

//    public String[] initAssets(){
//        try{
//            InputStream inputStream = getAssets().open("ClockInfo.txt");
//            String str = getString(inputStream);
//            String[] arr = str.split("\n");
//            return arr;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private String getString(InputStream inputStream) {
//        InputStreamReader inputStreamReader=null;
//        try {
//            inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
//        }catch (UnsupportedEncodingException el){
//            el.printStackTrace();
//        }
//        BufferedReader reader = new BufferedReader(inputStreamReader);
//        StringBuffer sb = new StringBuffer("");
//        String line;
//        try{
//            while ((line = reader.readLine()) != null) {
//                //添加到字符缓冲流中
//                sb.append(line);
//                //一条一行
//                sb.append("\n");
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////      返回所有信息
//        return sb.toString();
//
//    }

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
