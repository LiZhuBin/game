package com.example.administrator.happygame.util;


import com.aopa.greendao.ActivityDao;
import com.aopa.greendao.ChatDao;
import com.aopa.greendao.ForumDao;
import com.aopa.greendao.NewsDao;
import com.aopa.greendao.UserDao;
import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.been.News;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.example.administrator.happygame.mvp.api.ApiServiceManager;

import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class GlobalData {
    public static  Platform qq = ShareSDK.getPlatform(QQ.NAME);
    public static final String HTTP_ADDRESS_USER = "http://39.108.97.239:88/game/user/";
    public static final String HTTP_ADDRESS_ACTIVITY = "http://39.108.97.239:88/game/activity/";
    public static final String HTTP_ADDRESS_FORUM = "http://39.108.97.239:88/game/forum/";
    public static final String HTTP_ADDRESS_PICTURE = "http://39.108.97.239:88/game/picture/";
    public static final String HTTP_ADDRESS_News = "http://39.108.97.239:88/game/new/";
    public static UserDao mUserDao = MyApplication.getInstances().getDaoSession().getUserDao();
    public static ForumDao mForumDao = MyApplication.getInstances().getDaoSession().getForumDao();
    public static ActivityDao mActivityDao = MyApplication.getInstances().getDaoSession().getActivityDao();
    public static NewsDao mNewsDao = MyApplication.getInstances().getDaoSession().getNewsDao();
    public static ChatDao mChatDao = MyApplication.getInstances().getDaoSession().getChatDao();

    public static OkHttpClient client = new OkHttpClient();
public static synchronized void initUserData(){
    mUserDao.deleteAll();
    ApiServiceManager.getUserData("1")            //获取Observable对象
            .subscribeOn(Schedulers.newThread())
            //请求在新的线程中执行
            .observeOn(AndroidSchedulers.mainThread())
            //最后在主线程中执行
            .subscribe(new Consumer<List<User>>() {
                @Override
                public void accept(List<User> userList) throws Exception {
                    for (final User user : userList) {
                        // GlobalData.HTTP_ADDRESS_ACTIVITY+activity.getImage(),
                        mUserDao.insertOrReplace(user);

                    }
                }
            });
}
    public static void initActivityData(){
        mActivityDao.deleteAll();
        ApiServiceManager.getActivityData("1")            //获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行

                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Consumer<List<Activity>>() {
                    @Override
                    public void accept(List<Activity> activityList) throws Exception {
                        for (final Activity activity : activityList) {
                            mActivityDao.insertOrReplace(activity);
                        }

                    }
                });
    }
    public static void initForumData(){
        mForumDao.deleteAll();
        ApiServiceManager.getForumData("1")            //获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Consumer<List<Forum>>() {
                    @Override
                    public void accept(List<Forum> forumList) throws Exception {
                        for (final Forum forum : forumList) {
                            // GlobalData.HTTP_ADDRESS_ACTIVITY+activity.getImage(),
                            mForumDao.insertOrReplace(forum);

                        }
                    }
                });
    }
    public static void initNewsData(){
        mNewsDao.deleteAll();
        ApiServiceManager.getNewsData("1")            //获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Consumer<List<News>>() {
                    @Override
                    public void accept(List<News> newsList) throws Exception {
                        for (final News news : newsList) {
                            mNewsDao.insertOrReplace(news);

                        }

                    }
                });
    }
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

    public static boolean hasAdd(String activityId,String where) {
        for (String string : StringUtil.httpArray(where)) {
            if (string.equals(activityId)) {
                return true;
            }
        }
        return false;
    }

    public static String getStringSix(String six) {
        if ("0".equals(six)) {
            return "女";
        }
        return "男";
    }
}
