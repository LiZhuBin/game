package com.example.administrator.myapplication.main_fragment;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ViewPagerAdapter;
import com.example.administrator.myapplication.base.BasePagerFragment;
import com.example.administrator.myapplication.child_fragment.OneChildFragment1;
import com.example.administrator.myapplication.child_fragment.OneChildFragment2;


public class MainFragment2 extends BasePagerFragment {
    @Override
    protected void addPageToAdapter(ViewPagerAdapter adapter) {
        String[] title = getResources().getStringArray(R.array.one_title);
        adapter.addFragment(new OneChildFragment1(), title[0]);
        adapter.addFragment(new OneChildFragment2(), title[1]);
    }
}
