package com.example.administrator.myapplication.thing_class;

import java.util.List;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class Folder {

    private List<Images> ImageList ;
    private String name ;

    public Folder(String name,List<Images> images)
    {
        ImageList = images ;
        this.name = name;
    }

    public void AddImage_toimages(Images images){

        ImageList.add(images);

    }

    public List<Images> getImageList() {
        return ImageList;
    }

    public void setImageList(List<Images> imageList) {
        ImageList = imageList;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
