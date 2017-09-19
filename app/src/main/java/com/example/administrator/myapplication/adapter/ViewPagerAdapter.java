package com.example.administrator.myapplication.adapter;

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
    private ArrayList<String> titles=new ArrayList<String>();
    public void addFragment(Fragment  fragment){
        mFragment.add(fragment);
    }
    public void addFragment(Fragment fragment,String str){

        mFragment.add(fragment);
        titles.add(str);

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

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}