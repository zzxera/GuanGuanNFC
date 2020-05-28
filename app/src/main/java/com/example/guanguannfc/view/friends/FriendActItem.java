package com.example.guanguannfc.view.friends;

public class FriendActItem {

    private String friendName,actType,date,startTime,endTime,len,shareContent,level,shareDate;
    private int imageId;
    public FriendActItem(String[] infor){
        this.friendName=infor[0];
        this.level=infor[1];
        this.shareContent=infor[2];
        this.actType=infor[3];
        this.date=infor[4];
        this.startTime=infor[5];
        this.endTime=infor[6];
        this.len=infor[7];
        this.shareDate=infor[8];
        this.imageId=Integer.parseInt(infor[9]);

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
    public String getShareContent(){
        return shareContent;
    }
    public String getLevel(){
        return level;
    }
    public String getShareDate(){
        return shareDate;
    }
}
