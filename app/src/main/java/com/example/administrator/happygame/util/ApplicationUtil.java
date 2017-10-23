package com.example.administrator.happygame.util;

import android.app.Application;
import android.content.Context;

import com.mob.MobSDK;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;


/**
 * Created by Administrator on 2017/8/13 0013.
 */

public class ApplicationUtil extends Application {
    private static Context context;


    public static Context getContext() {
        return context;
    }

    public static void mobAccredit(Platform platform) {

//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        platform.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                arg0.getDb().exportData();
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
//authorize与showUser单独调用一个即可
        platform.authorize();//单独授权,OnComplete返回的hashmap是空的
        platform.showUser(null);//授权并获取用户信息
//移除授权
//weibo.removeAccount(true);
    }

    @Override
    public void onCreate() {
        context = getApplicationContext();

        MobSDK.init(this, "21819c0c884c1 ", "0acef4983fd0549ea3a27b3ea5d0f8a3");

if(NetworkUtil.isNetworkAvailable()) {
    TastyToast.makeText(getApplicationContext(), "你好，悦游!", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
}

    }
}
