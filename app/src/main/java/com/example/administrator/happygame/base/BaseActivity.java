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
import android.view.WindowManager;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.behavior.ZoomOutPageTransformer;
import com.example.administrator.happygame.child_fragment.AddChatFragment;
import com.example.administrator.happygame.child_fragment.AddContentFragment;
import com.example.administrator.happygame.child_fragment.ForumCommentFragment;
import com.example.administrator.happygame.child_fragment.ForumContentFragment;
import com.example.administrator.happygame.child_fragment.NewContentFragment;
import com.example.administrator.happygame.child_fragment.PersonAddFragment;
import com.example.administrator.happygame.child_fragment.PersonForumFragment;
import com.example.administrator.happygame.my_ui.MyViewPager;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;
import com.jude.swipbackhelper.SwipeBackHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class BaseActivity extends AppCompatActivity {

    ArrayList<Fragment> mFragments = new ArrayList<>();
    View mDecorView;
    SegmentTabLayout mTabLayout_3;
    String[] mTitles_3;

    @Subscribe
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SwipeBackHelper.onCreate(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SwipeBackHelper.getCurrentPage(this).setSwipeEdgePercent(0.2f);//get current instance
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
            mFragments.add(ForumCommentFragment.getInstance());
        } else if ("new".equals(which)) {
            mFragments.add(NewContentFragment.getInstance());
            mFragments.add(ForumCommentFragment.getInstance());
        } else if ("person".equals(which)) {
            mFragments.add(PersonAddFragment.getInstance());
            mFragments.add(PersonForumFragment.getInstance());
        }


        mDecorView = getWindow().getDecorView();

        mTabLayout_3 = (SegmentTabLayout) mDecorView.findViewById(R.id.tl_3);


        final MyViewPager vp_3 = (MyViewPager) mDecorView.findViewById(R.id.vp_2);
        vp_3.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        vp_3.setOffscreenPageLimit(1);
        vp_3.setPageTransformer(true, new ZoomOutPageTransformer());
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
        vp_3.setCurrentItem(pager);


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
