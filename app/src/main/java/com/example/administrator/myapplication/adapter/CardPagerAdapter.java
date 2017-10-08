package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.my_ui.BezierViewPager;
import com.example.administrator.myapplication.thing_class.Advertisement;
import com.example.administrator.myapplication.thing_class.Until;

import java.util.ArrayList;
import java.util.List;
public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<Advertisement> mData;
    private Context mContext;
    private int MaxElevationFactor = 9;
    private int CurItem ;
    private BezierViewPager FatherViewPager;

    @Override
    public int getMaxElevationFactor() {
        return MaxElevationFactor;
    }

    @Override
    public void setMaxElevationFactor(int MaxElevationFactor) {
        this.MaxElevationFactor = MaxElevationFactor;
    }


    public CardPagerAdapter(Context context) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        this.mContext = context;
        //这里可以初始化
        //这里要放启动的类名

    }

    public CardPagerAdapter(Context context,BezierViewPager fatherViewPager)
    {
        this(context);
        this.FatherViewPager = fatherViewPager ;
    }

    public CardPagerAdapter(Context context,List datalist)
    {
        this(context);


        if (datalist!=null)
        {
            addImgUrlList(datalist);
        }

    }
    public CardPagerAdapter(Context context,List datalist,BezierViewPager viewPager)
    {
        this(context,datalist);
        this.FatherViewPager = viewPager ;
    }


    public void addImgUrlList(List<Advertisement> imgUrlList) {
        //增加image的地址！！通过 Glide来分析在这些地址
        if (imgUrlList!=null) {
            mData = imgUrlList ;
        }
        handle_DataList(); //在这里去处理好数据，让数据变得正常！！！
        //然后再增加视图！！
        for (int i = 0; i < mData.size() ; i++) {
            mViews.add(null);
        }
    }


    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public int getCurItem() {
        return CurItem;
    }

    public void setCurItem(int curItem) {
        CurItem = curItem;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.adapter, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardItemClickListener != null) {
                    cardItemClickListener.onClick(position);
                }
            }
        });
        container.addView(view);
        Advertisement curpositionAds = mData.get(position);
        //curpositionAds.setPosition(position);//这里设置position
        bind(Until.Parse_Advertisement(curpositionAds),view,position); //这里把图片导入//通过方法去解析一个广告类！！！
        CardView cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(MaxElevationFactor);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }


    public void setFatherViewPager(BezierViewPager fatherViewPager) {
        FatherViewPager = fatherViewPager;
    }

    private void bind(Object imgUrl, View view, final int setPosition) {
        ImageView iv = (ImageView) view.findViewById(R.id.item_iv);
        //这里是加载图片的方法
        //在这里对Advertisement对象解析
        Glide.with(mContext).load(imgUrl).into(iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FatherViewPager != null) { //这样是开始了点击旁边item来跳转
                    setClickGoOtherItem(setPosition);
                } else if (FatherViewPager == null) { //不启动点击跳转
                    //这里是放置点击页面跳转情况，可以对advertisement进行解析后 开启活动！！
                    parseAdvertisement(mData.get(setPosition));
                }
            }});
        //加载到某个框架内
    }


    private void parseAdvertisement(Advertisement advertisement) {

        Intent intent = new Intent();
        //
    }

    private void setClickGoOtherItem(int setPosition) {
        if (setPosition!=CurItem&&FatherViewPager!=null) //要在FatherViewPager有的情况下
        {
            FatherViewPager.setCurrentItem(setPosition);
        }else { Toast.makeText(mContext,"This"+setPosition,Toast.LENGTH_SHORT).show(); }
        setCurItem(setPosition);
    }

    private OnCardItemClickListener cardItemClickListener;

    public interface OnCardItemClickListener {
        void onClick(int position);
    }

    public void setOnCardItemClickListener(OnCardItemClickListener cardItemClickListener) {
        this.cardItemClickListener = cardItemClickListener;
    }

    public List<Advertisement> getmData() {
        return mData;
    }

    private void handle_DataList() {

        Object thelast_One = mData.get(mData.size()-1);//倒数第一个
        Object thelast_Two = mData.get(mData.size()-2);
        Object the_One     = mData.get(0);
        Object the_Second  = mData.get(1);
        //这是为了制造良好的视觉效果
        //在倒数第1个视图是为了保持一致性，到倒数第二个时候就应该跳转！！回从前面数的第一个视图
        List Adapter_Viewpager_List = mData ;
        Adapter_Viewpager_List.add(0,thelast_Two);
        Adapter_Viewpager_List.add(1,thelast_One);
        Adapter_Viewpager_List.add(the_One);
        Adapter_Viewpager_List.add(the_Second);
        //item为7 没问题
    }



}
