package com.example.administrator.myapplication.base;

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
import android.view.WindowManager;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.child_fragment.AddChatFragment;
import com.example.administrator.myapplication.child_fragment.AddListFragment;
import com.example.administrator.myapplication.child_fragment.ForumCommentFragment;
import com.example.administrator.myapplication.child_fragment.ForumListFragment;
import com.example.administrator.myapplication.child_fragment.NewListFragment;
import com.example.administrator.myapplication.my_ui.MyViewPager;
import com.example.administrator.myapplication.util.ViewFindUtils;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.swipbackhelper.SwipeListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class BaseActivity extends AppCompatActivity {

     ArrayList<Fragment> mFragments = new ArrayList<>();
    View mDecorView;
    SegmentTabLayout mTabLayout_3;
    String[] mTitles_3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SwipeBackHelper.getCurrentPage(this)//get current instance
                .addListener(new SwipeListener() {

                    @Override
                    public void onScroll(float percent, int px) {

                    }

                    @Override
                    public void onEdgeTouch() {
                    }

                    @Override
                    public void onScrollToClose() {
                        finish();
                    }
                });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu2,menu);

        return super.onCreateOptionsMenu(menu);
    }
    public void initViewPager(String[] mTitles_3,String which) {
        this.mTitles_3=mTitles_3;
        for (String title : mTitles_3) {
            if(which.equals("add")) {
                mFragments.add(AddListFragment.getInstance("Switch ViewPager " + title));
                mFragments.add(AddChatFragment.getInstance("Switch ViewPager " + title));
            }else  if(which.equals("forum")){
                mFragments.add(ForumListFragment.getInstance("Switch ViewPager " + title));
                mFragments.add(ForumCommentFragment.getInstance("Switch ViewPager " + title));
            }else if(which.equals("new")){
                mFragments.add(NewListFragment.getInstance("Switch ViewPager " + title));
                mFragments.add(ForumCommentFragment.getInstance("Switch ViewPager " + title));
            }
        }


        mDecorView = getWindow().getDecorView();

        mTabLayout_3 = ViewFindUtils.find(mDecorView, R.id.tl_3);


        final MyViewPager vp_3 = ViewFindUtils.find(mDecorView, R.id.vp_2);
        vp_3.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        mTabLayout_3.setTabData(mTitles_3);
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
        vp_3.setCurrentItem(0);

        mTabLayout_3.showDot(1);

        //设置未读消息红点
        mTabLayout_3.showDot(2);
        MsgView rtv_3_2 = mTabLayout_3.getMsgView(2);
        if (rtv_3_2 != null) {
            rtv_3_2.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
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
