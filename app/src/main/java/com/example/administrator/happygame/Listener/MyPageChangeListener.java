package com.example.administrator.happygame.Listener;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.administrator.happygame.adapter.CardPagerAdapter;
import com.example.administrator.happygame.my_ui.BezierViewPager;


/**
 * Created by lenovo on 2017/10/4.
 */

public class MyPageChangeListener implements ViewPager.OnPageChangeListener {

    private Context MyContext;
    private BezierViewPager Myviewpager;
    private int ItemCount;
    private int CurItem;


    public MyPageChangeListener(Context context, BezierViewPager viewPager) {
        MyContext = context;
        Myviewpager = viewPager;
        ItemCount = ((CardPagerAdapter) viewPager.getAdapter()).getmData().size();
        Log.d("Are", "Item" + ItemCount);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("onPager", "onPageScrolled");


    }

    @Override
    public void onPageSelected(int position) {
        Log.d("onPager", "onPageSelect");

        CurItem = position;

        Log.d("Are", "PlayAgain" + Myviewpager.isAutoPlay());
        Myviewpager.setAutoPlay(true);

    }

    @Override
    public void onPageScrollStateChanged(int state) {


        if (state == ViewPager.SCROLL_STATE_IDLE) {
            if (ItemCount > 1) //页面在大于两个时候才实现无限循环
            {
                if (CurItem == (ItemCount - 2)) {
                    Myviewpager.setCurrentItem(2, false);
                } else if (CurItem == 1) {
                    Myviewpager.setCurrentItem(ItemCount - 3, false);
                } else if (CurItem == 0) {
                    Myviewpager.setCurrentItem(ItemCount - 4, false);
                } else if (CurItem == ItemCount - 1) {
                    Myviewpager.setCurrentItem(3, false);
                }
                /*   预防意外，在去到最边边的一页强制返回，页数 */
                Myviewpager.setAutoPlay(true);//在空闲时候的时候自动播放
            }
        } else if (state == ViewPager.SCROLL_STATE_DRAGGING || state == ViewPager.SCROLL_STATE_SETTLING) {
            Myviewpager.setAutoPlay(false); //在被用户操作的时候，不应该自动播放
        }


        Log.d("onPager", "onPageScrollStateChanged");


    }
}
