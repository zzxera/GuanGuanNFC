package com.example.guanguannfc.view.friends;

import android.widget.Button;

public class FriendRequestItem {

    private String name,remark;
    private int imageId;

    public FriendRequestItem(String[] arry){
        this.name = arry[0];
        this.remark = arry[1];
    }

    public String getName(){
        return name;
    }

    public String getRemark(){
        return remark;
    }

}
