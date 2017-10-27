package com.example.administrator.happygame.activity.fifth;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.MyPagerAdapter;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.child_fragment.AddListFragment;
import com.example.administrator.happygame.child_fragment.ForumListFragment;
import com.example.administrator.happygame.child_fragment.NewsListFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;

import static com.example.administrator.happygame.util.GlobalData.mUserDao;

public class CollectActivity extends BaseActivity {

    private static User user;
    String userId;
    @Bind(R.id.vp)
    ViewPager mViewPager;
    @Bind(R.id.coordinatortablayout)
    CoordinatorTabLayout coordinatortablayout;
    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"Android", "iOS", "Web"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        initFragments();
        initViewPager();
        mImageArray = new int[]{
                R.drawable.image_news1,
                R.drawable.image_news3,
                R.drawable.image_news4,
        };
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light};

        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        mCoordinatorTabLayout.setTranslucentStatusBar(this)
                .setTitle("Demo")
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mViewPager);

    }

    private void initFragments() {
        userId = getIntent().getExtras().getString("id");
        user =mUserDao.load(userId);
        EventBus.getDefault().post(user);
        mFragments = new ArrayList<>();
      mFragments.add(NewsListFragment.getInstance());
      mFragments.add(AddListFragment.getInstance());
      mFragments.add(ForumListFragment.getInstance());


    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
