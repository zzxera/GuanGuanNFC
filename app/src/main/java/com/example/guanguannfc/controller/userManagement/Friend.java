package com.example.guanguannfc.controller.userManagement;

import android.content.Context;

import com.example.guanguannfc.controller.timeManagement.GetTime;
import com.example.guanguannfc.model.Dao.DaoFriend;
import com.example.guanguannfc.model.Dao.DaoMoment;
import com.example.guanguannfc.model.Helper.HelperApplication;
import com.example.guanguannfc.model.Helper.HelperFriend;
import com.example.guanguannfc.model.Helper.HelperFriendAct;

import java.util.ArrayList;
import java.util.List;

public class Friend {
    DaoFriend DF;
    DaoMoment DM;
    GetTime gt = new GetTime();
    public Friend(Context context){
        this.DF = new DaoFriend(context);
        this.DM = new DaoMoment(context);
    }
    public String[] friendlist(String username){
        List<HelperFriend> list = new ArrayList<>();
        list = DF.query(username);
        if (list!=null){
        int i = list.size();
        String []arr = new String[i];
        for (int a=0;a<i;a++){
            arr [a]= list.get(a).getUser_name();
        }
        return arr;
        }
        else {
            return null;
        }
    }
    public String[][] friendact(String username){
        List<HelperFriendAct> list =new ArrayList<>();
        list = DF.queryFriendAct(username);
        if (list!=null){
            int n=list.size();
            String arr1[][] = new String[n][7];
            for (int i=0;i<n;i++){
                arr1[i][0]=list.get(i).getFriend_name();
                arr1[i][1]=String.valueOf(list.get(i).getLevel());
                arr1[i][2]=gt.timeStampToDate(list.get(i).getBegin_time());
                arr1[i][3]=gt.timeStampToDate(list.get(i).getEnd_time());
                arr1[i][4]=gt.transString1(list.get(i).getLen_time());
                arr1[i][5]=list.get(i).getActivity_type();
                arr1[i][6]=list.get(i).getMoment_text();
            }
            return arr1;
        }
        else {
            return null;
        }
    }

    public boolean insert(String username ,String friendname){
        return DF.insert(username,friendname);
    }

    public boolean delete(String username ,String friendname){
        return DF.delete(username,friendname);
    }

    public boolean updateapply(String username ,String friendname){
        return DM.update(username,friendname);
    }
    public String[][] friendapply(String username){
        List<HelperApplication> list = new ArrayList<>();
        if (list!= null){
            int n = list.size();
            String[][] arr = new String[n][3];
            for (int i=0;i<n;i++){
                arr[i][0] = list.get(i).getUser_name();
                arr[i][1] = list.get(i).getContent();
                arr[i][2] = gt.timeStampToDate(list.get(i).getTime());
            }
            return arr;
        }
        else {
            return null;
        }
    }

    public boolean apply(String username, String friendname, String content){
        return DM.insert(username,friendname,content);
    }
}
