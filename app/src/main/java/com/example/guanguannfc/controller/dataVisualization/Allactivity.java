package com.example.guanguannfc.controller.dataVisualization;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.guanguannfc.model.Dao.DaoActSta;
import com.example.guanguannfc.model.Helper.HelperActivity;
import com.example.guanguannfc.controller.timeManagement.GetTime;

import static com.example.guanguannfc.view.homepage.HomePageActivity.actName;

public class Allactivity {
    DaoActSta DS;
    public Allactivity(Context context){
        this.DS = new DaoActSta(context);
    }
    GetTime gt = new GetTime();
    public String[] allacttype(String username ){
        String[] arr = new String[6];
        arr[0]="全部";
        arr[1]="学习";
        arr[2]="睡觉";
        arr[3]="工作";
        arr[4]="吃饭";
        arr[5]="其他";
        return arr;
    }
    public String[][] sortedactivity(String username,String activityType,String sortType ){
        //根据用户名和活动类型从数据库拿到一个集合，将需要的数据取出，将数据进行转换，根据排序方式调取数据库不同方法，返回一个集合，以数组的形式再返回给view//;根据活动类型返回数据的数目
        ArrayList<HelperActivity> list = new ArrayList<>();
        String sort=sortType;
        if (activityType.equals("全部")==false){
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
        }
        else {
            switch (sort){
                case "最新活动在前"://调用不同排序方法
                    list = DS.queryByTimeDesc(username);
                    break;
                case "最新活动在后":
                    list = DS.queryByTimeAsc(username);
                    break;
                case "时间由长到短":
                    list = DS.queryByLengthDesc(username);
                    break;
                case "时间由短到长":
                    list = DS.queryByLengthAsc(username);
                    break;
                default:
                    break;
            }
        }


        if(list!=null){
            int n = list.size();
            String[][] arr1 = new String[n][7];
            for (int i=0; i<n; i++){
                arr1[i][0]=list.get(i).getActivity_type();
                arr1[i][1]=gt.transString(list.get(i).getBegin_time())[0][0];
                arr1[i][2]=gt.transString(list.get(i).getBegin_time())[0][1];
                arr1[i][3]=gt.transString(list.get(i).getEnd_time())[0][1];
                arr1[i][4]=gt.transString1(list.get(i).getLen_time());
                arr1[i][5]=list.get(i).getActivity_name();
                arr1[i][6]=String.valueOf(list.get(i).getBegin_time());
            }
            return arr1;
        }

        else{
            return null;
        }

    }
    public String[][] sortedactivity1(String username,String activityType,String sortType ) {
        //根据用户名和活动类型从数据库拿到一个集合，将需要的数据取出，将数据进行转换，根据排序方式调取数据库不同方法，返回一个集合，以数组的形式再返回给view//;根据活动类型返回数据的数目
        ArrayList<HelperActivity> list = new ArrayList<>();
        String sort = sortType;
        if (activityType.equals("全部") == false) {
            switch (sort) {
                case "最新活动在前"://调用不同排序方法
                    list = DS.queryByTimeDesc(username, activityType);
                    break;
                case "最新活动在后":
                    list = DS.queryByLengthAsc(username, activityType);
                    break;
                case "时间由长到短":
                    list = DS.queryByLengthDesc(username, activityType);
                    break;
                case "时间由短到长":
                    list = DS.queryByLengthAsc(username, activityType);
                    break;
                default:
                    break;
            }
        } else {
            switch (sort) {
                case "最新活动在前"://调用不同排序方法
                    list = DS.queryByTimeDesc(username);
                    break;
                case "最新活动在后":
                    list = DS.queryByTimeAsc(username);
                    break;
                case "时间由长到短":
                    list = DS.queryByLengthDesc(username);
                    break;
                case "时间由短到长":
                    list = DS.queryByLengthAsc(username);
                    break;
                default:
                    break;
            }
        }


        if (list != null) {
            int n = list.size();
            String[][] arr1 = new String[n][7];
            for (int i = 0; i < n; i++) {
                arr1[i][0] = list.get(i).getActivity_type();
                arr1[i][1] = gt.transString(list.get(i).getBegin_time())[0][0];
                arr1[i][2] = gt.transString(list.get(i).getBegin_time())[0][1];
                arr1[i][3] = gt.transString(list.get(i).getEnd_time())[0][1];
                arr1[i][4] = String.valueOf(list.get(i).getLen_time());
                arr1[i][5] = list.get(i).getActivity_name();
                arr1[i][6] = String.valueOf(list.get(i).getBegin_time());
            }
            return arr1;
        } else {
            return null;
        }
    }

    public boolean insertdata(String username,String actName,Long startTime,Long endTime){
        return DS.insert(username,actName,startTime,endTime);
    }

//    public String[][] sortedactivity1(String username,String activityType,String sortType ){
//        //根据用户名和活动类型从数据库拿到一个集合，将需要的数据取出，将数据进行转换，根据排序方式调取数据库不同方法，返回一个集合，以数组的形式再返回给view//;根据活动类型返回数据的数目
//        ArrayList<HelperActivity> list = new ArrayList<>();
//        String sort=sortType;
//        if (activityType.equals("全部")==false){
//            switch (sort){
//                case "最新活动在前"://调用不同排序方法
//                    list = DS.queryByTimeDesc(username,activityType);
//                    break;
//                case "最新活动在后":
//                    list = DS.queryByLengthAsc(username,activityType);
//                    break;
//                case "时间由长到短":
//                    list = DS.queryByLengthDesc(username,activityType);
//                    break;
//                case "时间由短到长":
//                    list = DS.queryByLengthAsc(username,activityType);
//                    break;
//                default:
//                    break;
//            }
//        }
//        else {
//            switch (sort){
//                case "最新活动在前"://调用不同排序方法
//                    list = DS.queryByTimeDesc(username);
//                    break;
//                case "最新活动在后":
//                    list = DS.queryByTimeAsc(username);
//                    break;
//                case "时间由长到短":
//                    list = DS.queryByLengthDesc(username);
//                    break;
//                case "时间由短到长":
//                    list = DS.queryByLengthAsc(username);
//                    break;
//                default:
//                    break;
//            }
//        }
//
//
//        if(list!=null){
//            int n = list.size();
//            String[][] arr1 = new String[n][7];
//            for (int i=0; i<n; i++){
//                arr1[i][0]=list.get(i).getActivity_type();
//                arr1[i][1]=gt.transString(list.get(i).getBegin_time())[0][0];
//                arr1[i][2]=gt.transString(list.get(i).getBegin_time())[0][1];
//                arr1[i][3]=gt.transString(list.get(i).getEnd_time())[0][1];
//                arr1[i][4]=String.valueOf(list.get(i).getLen_time());
//                arr1[i][5]=list.get(i).getActivity_name();
//                arr1[i][6]=String.valueOf(list.get(i).getBegin_time());
//            }
//            return arr1;
//        }
//
//        else{
//            return null;
//        }
//    }

}