package com.example.administrator.happygame.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.MyFragmentAdapter;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.childfragment.CollectActivityFragment;
import com.example.administrator.happygame.childfragment.CollectForumFragment;
import com.example.administrator.happygame.childfragment.CollectNewsFragment;
import com.example.administrator.happygame.childfragment.CollectUserFragment;
import com.example.administrator.happygame.my_ui.MyViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {


    @Bind(R.id.search_tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.vp_2)
    MyViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    private String[] sTitle = new String[]{"用户", "新闻", "约战请求", "帖子"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
toolbar.setTitle("查找结果");
setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }


    private void initView() {


        //  mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[3]));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(CollectUserFragment.getInstance());
        fragments.add(CollectNewsFragment.getInstance());
        fragments.add(CollectForumFragment.getInstance());
        fragments.add(CollectActivityFragment.getInstance());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments, Arrays.asList(sTitle));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
