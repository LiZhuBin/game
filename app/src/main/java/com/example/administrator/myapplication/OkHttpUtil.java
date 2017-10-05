package com.example.administrator.myapplication;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/10/2 0002.
 */

public class OkHttpUtil
{
    private static OkHttpClient singleton;
    //非常有必要，要不此类还是可以被new，但是无法避免反射，好恶心
    private OkHttpUtil(){

    }
    public static OkHttpClient getInstance() {
        if (singleton == null)
        {
            synchronized (OkHttpUtil.class)
            {
                if (singleton == null)
                {
                    singleton = new OkHttpClient();
                }
            }
        }
        return singleton;
    }
}