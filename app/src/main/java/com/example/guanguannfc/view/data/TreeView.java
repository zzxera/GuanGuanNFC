package com.example.guanguannfc.view.data;

public class TreeView {
    private String actType,startTime,endTime,len;

    public TreeView(String actType,String startTime,String endTime,String len){
        this.endTime=endTime;
        this.startTime=startTime;
        this.actType=actType;
        this.len=len;
    }

    public String getActType() {
        return actType;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getLen() {
        return len;
    }
}
