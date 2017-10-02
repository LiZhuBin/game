package com.example.administrator.myapplication.util;


import com.example.administrator.myapplication.main_fragment.UserFragment;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class GlobalData {

    public static String httpAddressUser="http://39.108.97.239:88/game/user/";
    public static String httpAddressActivity="http://39.108.97.239:88/game/activity/";
    public static String httpAddressForum="http://39.108.97.239:88/game/forum/";
    public static String httpAddressNew="http://39.108.97.239:88/game/new/";
    public static String httpAddressBusine="http://39.108.97.239:88/game/busine/";
    public static String httpAddressPicture="http://39.108.97.239:88/game/picture/";
public static int isFriend(String friendId){
   for(String string:StringUtil.httpArray(UserFragment.me.getFriends())) {
       if(string.equals(friendId)){
           return 1;
       }

   }
   if(friendId.equals(UserFragment.me.getId())){
       return 0;
   }
    return -1;
}

}
