package com.example.guanguannfc.view.data;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.guanguannfc.controller.timeManagement.GetTime;

public class ClockService extends Service {
    
    public static Long lStartTime;
    public static String startTime;
    public static GetTime getTime;

    public ClockService(){
    }

    @Override
    public void onCreate() {
        lStartTime = getTime.getStartTime();
        startTime=getTime.getNowTime();
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
