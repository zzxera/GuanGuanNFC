package com.example.guanguannfc.view.friends;

public class FriendActItem {

    private String friendName,actType,date,startTime,endTime,len,name;
    private int imageId;
    public FriendActItem(String[] infor){
        this.friendName=infor[0];
        this.imageId=Integer.parseInt(infor[1]);
        this.actType=infor[2];
        this.date=infor[3];
        this.startTime=infor[4];
        this.endTime=infor[5];
        this.len=infor[6];
        this.name=infor[7];


    }
    public String getFriendName(){
        return friendName;
    }

    public int getImageId(){
        return imageId;
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
