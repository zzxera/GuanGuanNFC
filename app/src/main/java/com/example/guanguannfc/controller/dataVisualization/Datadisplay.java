package com.example.guanguannfc.controller.dataVisualization;

import android.content.Context;
import android.renderscript.Sampler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.model.Helper.HelperActivity;
import com.example.guanguannfc.model.Helper.HelperActivityType;
import com.example.guanguannfc.model.Dao.DaoActSta;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.example.guanguannfc.controller.timeManagement.GetTime;

public class datadisplay {
    DaoActSta Dq;
    public datadisplay(Context context){
        this.Dq = new DaoActSta(context);
    }
    GetTime gt = new GetTime();
    public Object[] Datadplay(String username,String timestart,String timeend,String activityType,String showType){
        ArrayList<HelperActivityType> list = new ArrayList<>();
        long timeStart = gt.getStringToDate(timestart,"yyyy年MM月dd日 HH时mm分ss秒");
        long timeEnd = gt.getStringToDate(timeend,"yyyy年MM月dd日 HH时mm分ss秒");
        if (activityType.length() <= 0){
            list = Dq.queryActType(username,timeStart,timeEnd);
        }else {
            list = Dq.queryActType(username,timeStart,timeEnd,activityType);
        }
        int n = list.size();
        String[][] arr = new String[n][2];
        for (int i = 0;i<n;i++){
            arr [i][1]=list.get(i).getActivity_type();
            arr [i][2]=(gt.transString1(list.get(i).getLen_time()));
        }
        String dataAnalysis="test";
        String echarttype = showType;
        String url="" ;
        switch (echarttype){
         case "列表":
                url = "";
                break;
            case "条状图":
                url = "javascript:createChart('bar',[89,78,77]);";
                break;
            case "饼状图":
                url = "javascript:createChart('pie',[89,78,77]);";
                break;
            default:
                break;
        }
        Object[] objs = new Object[]{arr,url,dataAnalysis};
        return objs;
    }
    }
