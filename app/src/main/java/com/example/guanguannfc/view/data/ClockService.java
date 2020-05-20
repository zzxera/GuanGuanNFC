package com.example.guanguannfc.view.data;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.guanguannfc.controller.timeManagement.GetTime;

public class ClockService extends Service {
    
    private Long lStartTime;
    private String startTime,nowtime;
    private GetTime getTime;

    private LocalBroadcastManager lbm;
    private ClockService.TimerThread timerThread;
    boolean counting;


    class TimerThread extends Thread{
        @Override
        public void run() {
            while (true){
                try{
                    if (counting){
                        Thread.sleep(1000);
                        nowtime=getTime.getNowTime();
                        Intent data = new Intent();
                        data.setAction("clock");
                        data.putExtra("lstarttime",lStartTime);
                        data.putExtra("starttime",startTime);
                        data.putExtra("nowtime",nowtime);
                        lbm.sendBroadcast(data);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public class MyBinder extends Binder{
        boolean getTimerStatue(){
            return counting;
        }

        public void starTimer(){
            if (!counting){
                counting = true;
                lStartTime = getTime.getStartTime();
                startTime = getTime.getNowTime();
                nowtime = getTime.getNowTime();
            }
        }

       public void stopTimer(){
            counting = false;
        }

    }

    public ClockService(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ClockService.MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timerThread = new ClockService.TimerThread();
        lStartTime = getTime.getStartTime();
        startTime=getTime.getNowTime();
        nowtime = getTime.getNowTime();
        counting=false;
        timerThread.start();
        lbm = LocalBroadcastManager.getInstance(this);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }




}
