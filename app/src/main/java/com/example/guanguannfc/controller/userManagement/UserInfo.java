package com.example.guanguannfc.controller.userManagement;

import android.content.Context;

import com.example.guanguannfc.model.Dao.DaoActSta;
import com.example.guanguannfc.model.Dao.DaoFriend;
import com.example.guanguannfc.model.Dao.DaoMoment;
import com.example.guanguannfc.model.Dao.DaoPush;
import com.example.guanguannfc.model.Dao.DaoUserInfo;
import com.example.guanguannfc.model.Helper.HelperPush;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    DaoActSta DA;
    DaoUserInfo DU;
    DaoPush DP;
    public UserInfo(Context context){
        this.DU = new DaoUserInfo(context);
        this.DA = new DaoActSta(context);
        this.DP = new DaoPush(context);
    }
    public boolean updateActDay(String username,String last_actday){
        boolean a = DU.queryLastActDate(username, last_actday);
        if (a){
            return false;
        }
        else {
            boolean b = DU.updateLastAct(username,last_actday);
            if (b){
                boolean c = DU.updateActiveDay(username);
                return c;
            }
            else {
                return false;
            }
        }
    }
    public boolean leadupdate(String username,int is_studied){
        boolean a= DU.updateStudy(username,is_studied);
        if(a){
            return true;
        }
        else {
            return false;
        }
    }

/*public String [][] Userinfor(String username){

}*/

public String[][] Push(){
    List<HelperPush> list = new ArrayList<>();
    list = DP.query();
    if (list != null){
        int n = list.size();
        String[][] arr1 = new String[n][4];
        for (int i=0; i<n; i++){
            arr1[i][0] = list.get(i).getAutthor_name();
            arr1[i][1] = list.get(i).getTitle();
            arr1[i][2] = list.get(i).getContents();
            arr1[i][3] = list.get(i).getSummary();
        }
        return arr1;
    }
    else {
        return null;
    }
}

public boolean updateact(String username,long starttime, String context){
    return DA.update(username,starttime,context);
}

public boolean updateact(String username, String text){
    return DA.update(username,text);
}

public String[] getUserInfo(String username){
    return DU.personMessage(username);
}

}
