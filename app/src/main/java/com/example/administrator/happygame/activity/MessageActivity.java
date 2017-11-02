package com.example.administrator.happygame.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.MyFragmentAdapter;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.child_fragment.MessageChatFragment;
import com.example.administrator.happygame.child_fragment.MessageSystemFragment;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.example.administrator.happygame.util.IntentHelp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageActivity extends BaseActivity {



    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    public static final String []sTitle = new String[]{"ITEM FIRST","ITEM SECOND"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(IntentHelp.toFriendsActivity(UserFragment.me.getFriends(), "我的好友"));
            }
        });
        initView();
    }
    private void initView() {

        tabLayout.addTab(tabLayout.newTab().setText(sTitle[0]).setIcon(R.drawable.icon_chat));
        tabLayout.addTab(tabLayout.newTab().setText(sTitle[1]).setIcon(R.drawable.icon_system));
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
        fragments.add(MessageChatFragment.getInstance());
        fragments.add(MessageSystemFragment.getInstance());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),fragments, Arrays.asList(sTitle));
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
