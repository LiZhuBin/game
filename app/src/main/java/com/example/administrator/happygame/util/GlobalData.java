package com.example.administrator.happygame.util;


import com.example.administrator.happygame.main_fragment.UserFragment;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class GlobalData {

    public static String httpAddressUser = "http://39.108.97.239:88/game/user/";
    public static String httpAddressActivity = "http://39.108.97.239:88/game/activity/";
    public static String httpAddressForum = "http://39.108.97.239:88/game/forum/";
    public static String httpAddressNew = "http://39.108.97.239:88/game/new/";
    public static String httpAddressAdvertise = "http://39.108.97.239:88/game/advertise/";
    public static String httpAddressPicture = "http://39.108.97.239:88/game/picture/";
    public static OkHttpClient client = new OkHttpClient();



    public static int isFriend(String friendId) {
        for (String string : StringUtil.httpArray(UserFragment.me.getFriends())) {
            if (string.equals(friendId)) {
                return 1;
            }

        }
        if (friendId.equals(UserFragment.me.getId())) {
            return 0;
        }
        return -1;
    }

    public static boolean hasAdd(String activityId) {
        for (String string : StringUtil.httpArray(UserFragment.me.getDoingActivities())) {
            if (string.equals(activityId)) {
                return true;
            }
        }
        return false;
    }
public static String getStringSix(String six){
    if(six.equals("0")){
        return "女";
    }
    return "男";
}
}
