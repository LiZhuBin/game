package com.example.administrator.myapplication.zairuclas;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/9/14.
 */

public class Headview implements Serializable {

    private String Imageview_url;
    private String Imageview_name;
    private int Imageview_id;

    public int getImageview_id() {
        return Imageview_id;
    }

    public void setImageview_id(int imageview_id) {
        Imageview_id = imageview_id;
    }

    public String getImageview_name() {
        return Imageview_name;
    }

    public void setImageview_name(String imageview_name) {
        Imageview_name = imageview_name;
    }

    public Headview(String imageview_url, String imageview_name) {
        Imageview_url = imageview_url;
        Imageview_name = imageview_name;
    }

    public String getImageview_url() {

        return Imageview_url;
    }

    public void setImageview_url(String imageview_url) {
        Imageview_url = imageview_url;
    }

    public Headview( int imageview_id,String imageview_name) {
        Imageview_name = imageview_name;
        Imageview_id = imageview_id;
    }
}
