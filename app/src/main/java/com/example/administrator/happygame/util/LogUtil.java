package com.example.administrator.happygame.util;

import android.util.Log;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class LogUtil {
    private static final String TAG = "way";
    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private LogUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, string(msg));
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, string(msg));
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, string(msg));
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, string(msg));
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, string(msg));
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.i(tag, string(msg));
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.i(tag, string(msg));
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.i(tag, string(msg));
    }

    public static String string(String msg) {
        return "<<<<<<<<<<<<<<<<" + msg + ">>>>>>>>>>>>>>>>";
    }
}
