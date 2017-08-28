package com.example.administrator.myapplication.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/27 0027.
 */

public abstract class BasePagerFragment extends Fragment {
    @Bind(R.id.slidingTab)
    PagerSlidingTabStrip slidingTab;
    @Bind(R.id.pager)
    ViewPager viewPager;
    protected ViewPagerAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        ButterKnife.bind(this, view);
        adapter=new ViewPagerAdapter(getChildFragmentManager());
        addPageToAdapter(adapter);
        viewPager.setAdapter(adapter);
        slidingTab.setViewPager(viewPager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected abstract void addPageToAdapter(ViewPagerAdapter adapter);
}
