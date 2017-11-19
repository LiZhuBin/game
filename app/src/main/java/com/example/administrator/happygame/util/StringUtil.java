package com.example.administrator.happygame.util;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class StringUtil {
    private  String fixedStringLength;
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
    public  static String getAddOne(int oldNum){
        int newNum=oldNum+1;
        return newNum+"";
    }
public static String getFixedString(String str,int length){
    if(str == null ){
        return "";
    }
    if( length >=str.length()){//如果长度为null或者大于要截取的字符串的长度放回原来的字符串
        return str;
    }

        return str.substring(0,length-3)+"0";

}
}
