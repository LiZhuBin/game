package com.example.administrator.happygame.util;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

import com.aopa.greendao.DaoMaster;
import com.aopa.greendao.DaoSession;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.mvp.api.ApiServiceManager;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.mob.MobSDK;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.HashMap;
import java.util.List;

import cat.ereza.customactivityoncrash.config.CaocConfig;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.administrator.happygame.util.GlobalData.initForumData;
import static com.example.administrator.happygame.util.GlobalData.initNewsData;
import static com.example.administrator.happygame.util.GlobalData.initUserData;
import static com.example.administrator.happygame.util.GlobalData.mActivityDao;
import static com.example.administrator.happygame.util.GlobalData.mForumDao;
import static com.example.administrator.happygame.util.GlobalData.mNewsDao;
import static com.example.administrator.happygame.util.GlobalData.mUserDao;


/**
 * Created by Administrator on 2017/8/13 0013.
 */

public class MyApplication extends Application {
    public static MyApplication instances;
    private static Context context;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static User me;
    public static Object userId;
    public static MyApplication getInstances() {
        return instances;
    }
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    initEMClient();
                    break;
                default:
                    break;
            }
        }
    };
    public static Context getContext() {
        return context;
    }

    public static void mobAccredit(Platform platform) {

//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        platform.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                arg0.getDb().exportData();
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
//authorize与showUser单独调用一个即可
        platform.authorize();//单独授权,OnComplete返回的hashmap是空的
        platform.showUser(null);//授权并获取用户信息
//移除授权
//weibo.removeAccount(true);
    }

    @Override
    public void onCreate() {


        context = getApplicationContext();
        instances = this;
        setDatabase();
        MobSDK.init(this, "21819c0c884c1 ", "0acef4983fd0549ea3a27b3ea5d0f8a3");
        initData();
        refWatcher = LeakCanary.install(this);

    }
private void initEMClient(){
    EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
    options.setAcceptInvitationAlways(false);
//初始化
    EMClient.getInstance().init(context, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
    EMClient.getInstance().setDebugMode(true);

    userId = SPUtil.get(MyApplication.getContext(), "UserId", 1);

    me=mUserDao.load((String)userId.toString());
    EMClient.getInstance().chatManager().loadAllConversations();
    EMClient.getInstance().groupManager().loadAllGroups();
    EMClient.getInstance().login(me.getId(),me.getPassword(),new EMCallBack() {//回调
        @Override
        public void onSuccess() {
            EMClient.getInstance().groupManager().loadAllGroups();
            EMClient.getInstance().chatManager().loadAllConversations();
            LogUtil.d("main", "登录聊天服务器成功！");
        }

        @Override
        public void onProgress(int progress, String status) {

        }

        @Override
        public void onError(int code, String message) {
            LogUtil.d("main", "登录聊天服务器失败！");
        }
    });
    EMClient.getInstance().chatManager().addMessageListener(msgListener);


}
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
private void initData(){
    CaocConfig.Builder.create()
            .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
            .enabled(false) //default: true
            .showErrorDetails(false) //default: true
            .showRestartButton(false) //default: true
            .trackActivities(true) //default: false
            .minTimeBetweenCrashesMs(2000) //default: 3000
            .apply();
    if (SPUtil.get(context, "UserId", 1) == null) {
        SPUtil.put(context, "UserId", 1);
    }
    if (mUserDao.count() == 0) {
        LogUtil.e("fgggggggggggg");
        initUserData();
    }
    if (mForumDao.count() == 0) {
        initForumData();
    }
    if (mNewsDao.count() == 0) {
        initNewsData();
    }
    if (mActivityDao.count() == 0) {
        ApiServiceManager.getActivityData("1")            //获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行

                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Consumer<List<Activity>>() {
                    @Override
                    public void accept(List<Activity> activityList) throws Exception {
                        for (final Activity activity : activityList) {
                            mActivityDao.insertOrReplace(activity);
                        }
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }

                });

    }
}
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            LogUtil.d(messages.get(0).getMsgId());
            //收到消息
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
        }


        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
        }
    };
    public static RefWatcher getRefWatcher(Context context) {
       MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;


    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}