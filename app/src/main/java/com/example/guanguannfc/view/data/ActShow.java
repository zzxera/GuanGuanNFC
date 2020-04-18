package com.example.guanguannfc.view.data;

public class ActShow {
    private String actType,date,startTime,endTime,len,name;

    public ActShow(String[] infor ){

        this.actType=infor[0];
        this.date=infor[1];
        this.startTime=infor[2];
        this.endTime=infor[3];
        this.len=infor[4];
        this.name=infor[5];

    }

    public String getActType(){
        return actType;
    }

    public String getDate(){
        return date;
    }
    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){
        return endTime;
    }
    public String getLen(){
        return len;
    }
    public String getName(){
        return name;
    }
}
