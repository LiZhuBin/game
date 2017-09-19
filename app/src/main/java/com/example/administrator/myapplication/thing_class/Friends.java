package com.example.administrator.myapplication.thing_class;

/**
 * Created by 10619 on 2017/9/11.
 */

public class Friends {

    private String name;
    private int imageId;

    public Friends(String name, int imageId){
        this.name=name;
        this.imageId=imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}
