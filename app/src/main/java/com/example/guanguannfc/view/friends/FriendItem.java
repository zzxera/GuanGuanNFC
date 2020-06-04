package com.example.guanguannfc.view.friends;

public class FriendItem {
    private String friendName,friendLevel;
    private int imageId;

    public FriendItem(String[] friendInfo,int imgid){
        this.friendName=friendInfo[0];
        this.friendLevel = friendInfo[1]+"级";
        this.imageId=imgid;
    }

    public String getFriendName(){
        return friendName;
    }

    public int getImageId(){
        return imageId;
    }

    public String getFriendLevel(){
        return friendLevel;
    }

}
