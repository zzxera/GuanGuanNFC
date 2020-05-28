package com.example.guanguannfc.view.mainInterface;

public class PushItem {

    private String title,author,content;
    private int imageId;

    public PushItem(String[] array){
        this.title=array[0];
        this.author=array[1];
        this.content=array[2];
        this.imageId=Integer.parseInt(array[3]);
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
