package com.example.administrator.myapplication.thing_class;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lenovo on 2017/10/5.
 */

public class Advertisement {

    private URL image_url;
    private int image_ResourceId ;
    private boolean isUse ;
    private int Position ;

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }
    // private 一个新闻类 实现跳转！！活动内容类！！

    private void ChangeType(Object address)
    {
        if (address instanceof String)
        {
            String ads = (String)address;
            URL url =null;
            try {
                 url = new URL(ads);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            setImage_url(url);
        }
        if (address instanceof Integer)
        {
            setImage_ResourceId((int)address);
        }

    }

    public Advertisement(Object address){
        ChangeType(address);
    }
    public Advertisement(Object address, boolean isUse)
    {
        this(address);
        this.isUse = isUse ;
    }


    public URL getImage_url() {
        return image_url;
    }

    public void setImage_url(URL image_url) {
        this.image_url = image_url;
    }

    public int getImage_ResourceId() {
        return image_ResourceId;
    }

    public void setImage_ResourceId(int image_ResourceId) {
        this.image_ResourceId = image_ResourceId;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }
}
