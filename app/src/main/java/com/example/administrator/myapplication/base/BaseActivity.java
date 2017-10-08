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
import com.example.administrator.myapplication.ZoomOutPageTransformer;
import com.example.administrator.myapplication.child_fragment.AddChatFragment;
import com.example.administrator.myapplication.child_fragment.AddListFragment;
import com.example.administrator.myapplication.child_fragment.ForumCommentFragment;
import com.example.administrator.myapplication.child_fragment.ForumListFragment;
import com.example.administrator.myapplication.child_fragment.MessageChatFragment;
import com.example.administrator.myapplication.child_fragment.MessageSystemFragment;
import com.example.administrator.myapplication.child_fragment.NewListFragment;
import com.example.administrator.myapplication.child_fragment.PersonAddFragment;
import com.example.administrator.myapplication.child_fragment.PersonForumFragment;
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
    public void initViewPager(String[] mTitles_3,String which,int pager) {
        this.mTitles_3=mTitles_3;

            if(which.equals("add")) {
                mFragments.add(AddListFragment.getInstance());
                mFragments.add(AddChatFragment.getInstance());
            }else  if(which.equals("forum")){
                mFragments.add(ForumListFragment.getInstance());
                mFragments.add(ForumCommentFragment.getInstance());
            }else if(which.equals("new")){
                mFragments.add(NewListFragment.getInstance());
                mFragments.add(ForumCommentFragment.getInstance());
            }else if(which.equals("person")){
                mFragments.add(PersonAddFragment.getInstance());
                mFragments.add(PersonForumFragment.getInstance());
            }else if(which.equals("message")){
                mFragments.add(MessageChatFragment.getInstance());
                mFragments.add(MessageSystemFragment.getInstance());
            }



        mDecorView = getWindow().getDecorView();

        mTabLayout_3 = ViewFindUtils.find(mDecorView, R.id.tl_3);


        final MyViewPager vp_3 = ViewFindUtils.find(mDecorView, R.id.vp_2);
        vp_3.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        vp_3.setOffscreenPageLimit(2);
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
