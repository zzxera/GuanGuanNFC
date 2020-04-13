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

public class datadisplay extends AppCompatActivity {
    DaoActSta Dq =  new DaoActSta(this);
    public Object[] Datadisplay(String username,String timestart,String timeend,String activityType,String showType){
        ArrayList<HelperActivityType> list = new ArrayList<>();
        long timeStart = 1;
        long timeEnd = 2;
        list = Dq.queryActType(username,timeStart,timeEnd);
        int n = list.size();
        String[][] arr = new String[n][2];
        for (int i = 0;i<n;i++){
            arr [i][1]=list.get(i).getActivity_type();
            arr [i][2]=String.valueOf(list.get(i).getLen_time());
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
