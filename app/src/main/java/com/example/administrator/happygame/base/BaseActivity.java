package com.example.administrator.happygame.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.behavior.ZoomOutPageTransformer;
import com.example.administrator.happygame.childfragment.AddChatFragment;
import com.example.administrator.happygame.childfragment.AddContentFragment;
import com.example.administrator.happygame.childfragment.ForumChatFragment;
import com.example.administrator.happygame.childfragment.ForumContentFragment;
import com.example.administrator.happygame.childfragment.NewChatFragment;
import com.example.administrator.happygame.childfragment.NewContentFragment;
import com.example.administrator.happygame.childfragment.PersonAddFragment;
import com.example.administrator.happygame.childfragment.PersonForumFragment;
import com.example.administrator.happygame.my_ui.MyViewPager;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;
import com.jude.swipbackhelper.SwipeBackHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class BaseActivity extends AppCompatActivity implements BGASwipeBackHelper.Delegate{
    private BGASwipeBackHelper mSwipeBackHelper;
    ArrayList<Fragment> mFragments = new ArrayList<>();
    View mDecorView;
    SegmentTabLayout mTabLayout_3;
    String[] mTitles_3;
   private MyPagerAdapter myPagerAdapter;
    @Subscribe
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       SwipeBackHelper.onCreate(this);
myPagerAdapter=new MyPagerAdapter(getSupportFragmentManager());
        initSwipeBackFinish();


        SwipeBackHelper.getCurrentPage(this).setSwipeEdgePercent(0.3f);//get current instance
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
      //  SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu2, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void initViewPager(String[] mTitles_3, String which, int pager) {
        this.mTitles_3 = mTitles_3;

        if ("add".equals(which)) {
            mFragments.add(AddContentFragment.getInstance());
            mFragments.add(AddChatFragment.getInstance());
        } else if ("forum".equals(which)) {
            mFragments.add(ForumContentFragment.getInstance());
            mFragments.add(ForumChatFragment.getInstance());
        } else if ("new".equals(which)) {
            mFragments.add(NewContentFragment.getInstance());
            mFragments.add(NewChatFragment.getInstance());
        } else if ("person".equals(which)) {
            mFragments.add(PersonAddFragment.getInstance());
            mFragments.add(PersonForumFragment.getInstance());
        }


        mDecorView = getWindow().getDecorView();

        mTabLayout_3 = (SegmentTabLayout) mDecorView.findViewById(R.id.tl_3);


        final MyViewPager vp_3 = (MyViewPager) mDecorView.findViewById(R.id.vp_2);
        vp_3.setAdapter(myPagerAdapter);
        vp_3.setOffscreenPageLimit(1);
        vp_3.setPageTransformer(true, new ZoomOutPageTransformer());
        mTabLayout_3.setTabData(mTitles_3);
        vp_3.setOverScrollMode( View.OVER_SCROLL_NEVER );
        mTabLayout_3.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp_3.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        vp_3.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout_3.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_3.setCurrentItem(pager);


        MsgView rtv_3_2 = mTabLayout_3.getMsgView(2);
        if (rtv_3_2 != null) {
            rtv_3_2.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
    }
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles_3[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
