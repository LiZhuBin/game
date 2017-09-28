package com.example.administrator.myapplication.util;

import com.example.administrator.myapplication.been.Activity;
import com.example.administrator.myapplication.been.Forum;
import com.example.administrator.myapplication.been.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/13 0013.
 */

public class HttpUtil {
    private static final String TAG = "HttpUtil";
    private  static User me=null;
    public static void sendOkHttpResquest(String address,RequestBody body,okhttp3.Callback callback){

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(address)
                .post(body)
                .build()
                ;

        client.newCall(request).enqueue(callback);
    }
    public static void sendOkHttpResquest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(address)
                .build()
                ;

        client.newCall(request).enqueue(callback);
    }

    public static List<User> getListUser(Response response){
        List<User> appList;
        appList = null;
        try {
            String responseData=response.body().string();
            Gson gson=new Gson();
            appList=gson.fromJson(responseData,new TypeToken<List<User>>(){}.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return appList;
    }
    public static List<Activity> getListActivity(Response response){
        List<Activity> appList;
        appList = null;
        try {
            String responseData=response.body().string();
            Gson gson=new Gson();
            appList=gson.fromJson(responseData,new TypeToken<List<Activity>>(){}.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return appList;
    }
    public static User getSingleUser(Response response){
        return HttpUtil.getListUser(response).get(0);
    }
    public static List<Forum> parseForumJSONWithGSON(Response response){
        List<Forum> appList;
        appList = null;
        try {
            String responseData=response.body().string();
            Gson gson=new Gson();
            appList=gson.fromJson(responseData,new TypeToken<List<Forum>>(){}.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return appList;
    }
}
