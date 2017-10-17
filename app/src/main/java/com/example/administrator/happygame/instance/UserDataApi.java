package com.example.administrator.happygame.instance;

import com.example.administrator.happygame.been.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public interface UserDataApi {

    @GET("php/userData.php/{id}")
    Call<User> getAndroidInfo(@Path("id") String id);
}
