package com.example.administrator.happygame.util;

import android.graphics.Bitmap;

import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.been.Advertise;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.been.News;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.thing_class.Images;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.administrator.happygame.util.GlobalData.client;

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
    public static News getSingleNews(Response response) {
        return HttpUtil.getListNews(response).get(0);
    }

    public static Forum getSingleForum(Response response) {
        return HttpUtil.getListForum(response).get(0);
    }

    public static Advertise getSingleAdvertise(Response response) {
        return HttpUtil.getListAdvertise(response).get(0);
    }

    public static String getJson(Object object) {

        Gson gson2 = new GsonBuilder().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        String obj2 = gson2.toJson(object);
        return obj2;
    }

    public static void postImage(Images images) {
        //这里方便演示。读取drawable里的图片。

        Bitmap bitmap = BitmapUtil.getUsableImage(MyApplication.getContext(), images.getPath());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("img_1", images.getName(), RequestBody.create(MediaType.parse("image/jpeg"), byteArrayOutputStream.toByteArray()));
        //有多个图片就用for循环添加即可

        MultipartBody build = builder.build();

        okhttp3.Request bi = new okhttp3.Request.Builder()
                .url(GlobalData.HTTP_ADDRESS_PICTURE + "addImage.php")
                .post(build)
                .build();

        client.newCall(bi).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

            }
        });

    }
    public static  void addDoingActivity(String actiivityID,String userId){
        RequestBody body = new FormBody.Builder()
                .add("actiivityID", actiivityID)
                .add("userid", userId)
                .build();
        HttpUtil.sendOkHttpResquest(GlobalData.HTTP_ADDRESS_USER + "php/addDoingActivity.php", body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

}
