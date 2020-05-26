package com.example.guanguannfc.view.pushs;

public class PushItem {

    private String title,author,content;
    private int imageId;

    public PushItem(String[] array,int id){
        this.title=array[1];
        this.author=array[0];
        this.content=array[2];
        this.imageId=id;

    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getContent(){
        return content;
    }
    public int getImageId(){
        return imageId;
    }
}
