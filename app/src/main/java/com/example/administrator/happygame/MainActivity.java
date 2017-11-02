package com.example.administrator.happygame;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.administrator.happygame.activity.MessageActivity;
import com.example.administrator.happygame.activity.SearchActivity;
import com.example.administrator.happygame.adapter.ViewPagerAdapter;
import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.behavior.ZoomOutPageTransformer;
import com.example.administrator.happygame.main_fragment.AddFragment;
import com.example.administrator.happygame.main_fragment.ForumFragment;
import com.example.administrator.happygame.main_fragment.RecommentFragment;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.example.administrator.happygame.mvp.api.ApiServiceManager;
import com.example.administrator.happygame.thing_class.Msg;
import com.example.administrator.happygame.util.BitmapUtil;
import com.example.administrator.happygame.util.LogUtil;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.NetworkUtil;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.util.NetUtils;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.ldoublem.loadingviewlib.view.LVGhost;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.administrator.happygame.util.GlobalData.initActivityData;
import static com.example.administrator.happygame.util.GlobalData.initForumData;
import static com.example.administrator.happygame.util.GlobalData.initNewsData;
import static com.example.administrator.happygame.util.GlobalData.initUserData;
import static com.example.administrator.happygame.util.GlobalData.mActivityDao;
import static com.example.administrator.happygame.util.GlobalData.mUserDao;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, EMConnectionListener,EMMessageListener {

    private static final String TAG = "MainActivity";
    SearchView searchView;
    @Bind(R.id.lv_ghost)
    LVGhost lvGhost;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    lvGhost.stopAnim();
                    lvGhost.setVisibility(View.GONE);
                    setupViewPager(viewPager);

                    break;
                default:
                    break;
            }
        }
    };
    @Bind(R.id.layout_net_error)
    LinearLayout layoutNetError;
    private boolean mIsExit;
    private BottomNavigationViewEx navigation;
    private ViewPager viewPager;
    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);

                    break;
                case R.id.navigation_dashboard:

                    viewPager.setCurrentItem(1);

                    break;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.navigation_email2:
                    viewPager.setCurrentItem(3);
                    break;
                default:
                    return false;
            }
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//全屏没有状态栏。

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initBottomNavigationViewEx();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        lvGhost.setVisibility(View.VISIBLE);
        lvGhost.startAnim(500);
        if (NetworkUtil.isNetworkAvailable()) {
                initUserData();
                initForumData();
                initNewsData();
                initActivityData();
                ApiServiceManager.getActivityData("1")            //获取Observable对象
                        .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                        .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                        .subscribe(new Consumer<List<Activity>>() {
                            @Override
                            public void accept(List<Activity> activityList) throws Exception {
                                for (final Activity activity : activityList) {
                                    mActivityDao.insertOrReplace(activity);
                                }
                                Message message = handler.obtainMessage();
                                message.what = 1;
                                handler.sendMessage(message);
                            }
                        });

            }
        else {
            TastyToast.makeText(getApplicationContext(), "当前无网络", TastyToast.INFO, TastyToast.ERROR);
        }
        viewPager.addOnPageChangeListener(this);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());


        final FloatingSearchView mSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

            }
        });

        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.message:

                        startActivity(new Intent(MainActivity.this, MessageActivity.class));
                        break;
                    default:
                        break;
                }
            }


        });

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));

                mSearchView.clearFocus();
            }

            @Override
            public void onFocusCleared() {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        //找到searchView
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecommentFragment());
        adapter.addFragment(new AddFragment());
        adapter.addFragment(new ForumFragment());
        adapter.addFragment(new UserFragment());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }


    public void initBottomNavigationViewEx() {
        navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                navigation.setSelectedItemId(R.id.navigation_home);
                break;
            case 1:
                navigation.setSelectedItemId(R.id.navigation_dashboard);
                break;
            case 2:
                navigation.setSelectedItemId(R.id.navigation_notifications);
                break;
            case 3:
                navigation.setSelectedItemId(R.id.navigation_email2);

                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    /** * 双击返回键退出 */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                TastyToast.makeText(getApplicationContext(), "再按一次退出", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onConnected() {
        layoutNetError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDisconnected(final int error) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (error == EMError.USER_REMOVED) {
                    // 显示帐号已经被移除
                } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    // 显示帐号在其他设备登录
                } else {
                    if (NetUtils.hasNetwork(MainActivity.this)) {

                    } else {
          layoutNetError.setVisibility(View.VISIBLE);
                    }
                    //连接不到聊天服务器

                    //当前网络不可用，请检查网络设置
                }
            }
        });
    }

    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);


        for (final EMMessage message:messages) {

            runOnUiThread(new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    String str = (((EMTextMessageBody) message.getBody()).getMessage());
                    LogUtil.e(str);
                    Msg msg = new Msg(str, Msg.TYPE_RECEIVED);

                    Notification notification=new NotificationCompat.Builder(MyApplication.getContext())
                            .setContentTitle(message.getUserName().equals("admin")? "系统消息":mUserDao.load(message.getUserName()).getName())
                            .setContentText(str)
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.ic_logo)
                            .setLargeIcon(message.getUserName().equals("admin")?BitmapFactory.decodeResource(getResources(),R.mipmap.ic_logo): BitmapUtil.getBitmap( mUserDao.load(message.getUserName()).getImage()))
                            .setPriority(NotificationCompat.PRIORITY_MAX)
                            .build();
                    manager.notify(1,notification);
                 //   setMsg(msg);

                }
            }));
        }
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {

    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EMClient.getInstance().chatManager().removeMessageListener(this);
    }
}