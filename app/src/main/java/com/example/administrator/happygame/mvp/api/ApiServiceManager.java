package com.example.administrator.happygame.mvp.api;

import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.been.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.administrator.happygame.util.GlobalData.client;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class ApiServiceManager {

    private  static ApiServer api=createRetrofit().create(ApiServer.class);

    public static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://39.108.97.239:88/game/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public static Observable<List<User>> getUserData(String id) {
        ApiServer apiService = createRetrofit().create(ApiServer.class);
        Observable<List<User>> movieBeanObservable = apiService.getUserData(id);
        return movieBeanObservable;
    }
    public static Observable<List<Activity>> getActivityData(String id) {
        ApiServer apiService = createRetrofit().create(ApiServer.class);
        Observable<List<Activity>> movieBeanObservable = apiService.getActivityData(id);
        return movieBeanObservable;
    }
    public static Observable<List<Forum>> getForumData(String id) {
        ApiServer apiService = createRetrofit().create(ApiServer.class);
        Observable<List<Forum>> movieBeanObservable = apiService.getForumData(id);
        return movieBeanObservable;
    }













}
