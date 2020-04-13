package com.example.guanguannfc.controller.dataVisualization;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.guanguannfc.model.Dao.DaoActSta;
import com.example.guanguannfc.model.Helper.HelperActivity;

public class Allactivity extends AppCompatActivity {
    DaoActSta DS = new DaoActSta(this);
    public String[][] allacttype(String username ){
        String[][] arr = new String[1][5];
        arr[1][1]="学习";
        arr[1][2]="睡觉";
        arr[1][3]="工作";
        arr[1][4]="吃饭";
        arr[1][5]="其它";
        return arr;
    }
    public String[][] sortedactivity(String username,String activityType,String sortType ){
        //根据用户名和活动类型从数据库拿到一个集合，将需要的数据取出，将数据进行转换，根据排序方式调取数据库不同方法，返回一个集合，以数组的形式再返回给view//;根据活动类型返回数据的数目
        ArrayList<HelperActivity> list = new ArrayList<>();
        String sort=sortType;
        switch (sort){
            case "最新活动在前"://调用不同排序方法
                list = DS.queryByTimeDesc(username,activityType);
                break;
            case "最新活动在后":
                list = DS.queryByLengthAsc(username,activityType);
                break;
            case "时间由长到短":
                list = DS.queryByLengthDesc(username,activityType);
                break;
            case "时间由短到长":
                list = DS.queryByLengthAsc(username,activityType);
                break;
                default:
                    break;
        }
        int n = list.size();
        String[][] arr1 = new String[n][5];
        for (int i=0; i<n; i++){
            arr1[i][1]=list.get(i).getActivity_name();
            arr1[i][5]=String.valueOf(list.get(i).getLen_time());
        }
        return arr1;
    }



    }
