package com.example.administrator.happygame.adapter;

/**
 * 作者：Administrator on 2017/11/2 0002 18:40
 * 邮箱：xjs250@163.com
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by shaoshuai on 16/3/9.
 */
public  class MyFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> list;//ViewPager要填充的fragment列表
    List<String>title;//tab中的title文字列表
    //使用构造方法来将数据传进去
    public MyFragmentAdapter(FragmentManager fm, List<Fragment> list,List<String>title) {
        super(fm);
        this.list = list;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {//获得position中的fragment来填充
        return list.get(position);
    }

    @Override
    public int getCount() {//返回FragmentPager的个数
        return list.size();
    }

    //FragmentPager的标题,如果重写这个方法就显示不出tab的标题内容
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}