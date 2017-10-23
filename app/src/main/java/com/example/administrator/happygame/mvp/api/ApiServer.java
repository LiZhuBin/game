package com.example.administrator.happygame.mvp.api;

import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.been.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public interface ApiServer {

    @GET("user/php/userData.php")
   Observable<List<User>> getUserData(@Query("ip") String ip);
@GET("activity/php/userData.php")
Observable<List<Activity>> getActivityData(@Query("id") String id);
    @GET("forum/php/userData.php")
    Observable<List<Forum>> getForumData(@Query("id") String id);
    @FormUrlEncoded
    @POST("user/php/getById.php")
//    1.4.通过@Field来指定key，后面跟上value
    Observable<User> getSingleUser( @Field( "id") String id);
    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);
    //  上传一个文件/图片





}


