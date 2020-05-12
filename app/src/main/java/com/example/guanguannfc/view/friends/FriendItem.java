package com.example.guanguannfc.view.friends;

public class FriendItem {
    private String friendName;
    private int imageId;

    public FriendItem(String friendname,int imgid){
        this.friendName=friendname;
        this.imageId=imgid;
    }

    public String getFriendName(){
        return friendName;
    }

    public int getImageId(){
        return imageId;
    }

}
