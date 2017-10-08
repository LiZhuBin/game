package com.example.administrator.myapplication.util;

import android.app.Application;
import android.content.Context;



/**
 * Created by Administrator on 2017/8/13 0013.
 */

public class ApplicationUtil extends Application{
    private static  Context context;

    @Override
    public void onCreate() {
        context=getApplicationContext();


    }
    public static Context getContext(){
        return context;
    }



}
