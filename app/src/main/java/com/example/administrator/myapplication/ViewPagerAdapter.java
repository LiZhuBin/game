package com.example.administrator.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/27 0027.
 */
public   class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragment=new ArrayList<Fragment>();

    public void addFragment(Fragment  fragment){
        mFragment.add(fragment);
    }
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();

    }

}