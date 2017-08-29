package com.example.administrator.myapplication.thing_class;

/**
 * Created by Administrator on 2017/8/18 0018.
 */

public class Fruit {
    String name;
   int  imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
