package com.example.administrator.happygame.util;

import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.been.Advertise;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.been.News;
import com.example.administrator.happygame.been.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/13 0013.
 */

public class HttpUtil {
    private static final String TAG = "HttpUtil";
    public static User user = null;
    public static Forum forum = null;
    private static String string = null;
    private static Advertise advertise = null;

    public static void sendOkHttpResquest(String address, String id, okhttp3.Callback callback) {
        RequestBody body = new FormBody.Builder()
                .add("id", id)//添加键值对
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttpResquest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();

        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttpResquest(String address, RequestBody body, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);
    }

    public static List<User> getListUser(Response response) {
        List<User> appList;
        appList = null;
        try {
            String responseData = response.body().string();
            Gson gson = new Gson();
            appList = gson.fromJson(responseData, new TypeToken<List<User>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return appList;
    }

    public static List<News> getListNews(Response response) {
        List<News> appList;
        appList = null;
        try {
            String responseData = response.body().string();
            Gson gson = new Gson();
            appList = gson.fromJson(responseData, new TypeToken<List<News>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return appList;
    }

    public static List<Activity> getListActivity(Response response) {
        List<Activity> appList;
        appList = null;
        try {
            String responseData = response.body().string();
            Gson gson = new Gson();
            appList = gson.fromJson(responseData, new TypeToken<List<Activity>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return appList;
    }

    public static List<Forum> getListForum(Response response) {
        List<Forum> appList;
        appList = null;
        try {
            String responseData = response.body().string();
            Gson gson = new Gson();
            appList = gson.fromJson(responseData, new TypeToken<List<Forum>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return appList;
    }

    public static List<Advertise> getListAdvertise(Response response) {
        List<Advertise> appList;
        appList = null;
        try {
            String responseData = response.body().string();
            Gson gson = new Gson();
            appList = gson.fromJson(responseData, new TypeToken<List<Advertise>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return appList;
    }

    public static User getSingleUser(Response response) {
        return HttpUtil.getListUser(response).get(0);
    }

    public static Activity getSingleActivity(Response response) {
        return HttpUtil.getListActivity(response).get(0);
    }

    public static Forum getSingleForum(Response response) {
        return HttpUtil.getListForum(response).get(0);
    }

    public static Advertise getSingleAdvertise(Response response) {
        return HttpUtil.getListAdvertise(response).get(0);
    }


}
