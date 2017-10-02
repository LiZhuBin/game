package com.example.administrator.myapplication.thing_class;

import java.io.Serializable;

/**
 * Created by 10619 on 2017/9/11.
 */

public class Friends implements Serializable{
private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private int imageId;
    private String imageUrl;

    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Friends(String id,String name, int imageId){
        this.id=id;
        this.name=name;
        this.imageId=imageId;
    }

    public Friends(String id,String name, String imageUrl) {
        this.id=id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}
