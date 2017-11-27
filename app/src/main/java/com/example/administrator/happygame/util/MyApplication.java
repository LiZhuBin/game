package com.example.administrator.happygame.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

import com.aopa.greendao.DaoMaster;
import com.aopa.greendao.DaoSession;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.mvp.api.ApiServiceManager;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
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
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.io.File;
import java.util.List;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.administrator.happygame.util.GlobalData.initActivityData;
import static com.example.administrator.happygame.util.GlobalData.initForumData;
import static com.example.administrator.happygame.util.GlobalData.initNewsData;
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
    @SuppressLint("HandlerLeak")
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



    @Override
    public void onCreate() {
        context = getApplicationContext();
        instances = this;
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
//初始化
        EMClient.getInstance().init(context, options);
        EMClient.getInstance().setDebugMode(true);
        setDatabase();
        setDaoDatabase();
        MobSDK.init(this, "21819c0c884c1 ", "0acef4983fd0549ea3a27b3ea5d0f8a3");

        refWatcher = LeakCanary.install(this);
        ZXingLibrary.initDisplayOpinion(this);
        BGASwipeBackHelper.init(this, null);
        initData();
    }
    private void initEMClient(){

//在做打包混淆时，关闭debug模式，避免消耗不必要的资源


        userId = SPUtil.get(MyApplication.getContext(), "UserId", 1);

        me=mUserDao.load((String)userId.toString());

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
     //   EMClient.getInstance().chatManager().addMessageListener(msgListener);


    }
    private void setDatabase(){
        String dirPath="/data/data/"+this.getPackageName()+"/databases/";
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();}
        SQLiteDatabase database=SQLiteDatabase.openOrCreateDatabase(dirPath+"data.db",null);

    }
    private void setDaoDatabase() {
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

    private  void initData(){

        if (SPUtil.get(context, "UserId", 1) == null) {
            SPUtil.put(context, "UserId", 1);
        }

            LogUtil.e("fgggggggggggg");

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
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                });

            initForumData();

            initNewsData();
initActivityData();


    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

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