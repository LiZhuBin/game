package com.example.administrator.happygame.util;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class StringUtil {
    public static String[] httpArray(String httpData) {
        if (httpData == null) {
            return null;
        }
        String[] arrays = httpData.split("\\|");
        return arrays;
    }

    public static String httpArrayStringLength(String httpData) {
        return httpArray(httpData).length + "";
    }
}
