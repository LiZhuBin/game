package com.example.administrator.myapplication.my_ui;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.adapter.CardAdapter;
import com.example.administrator.myapplication.adapter.CardPagerAdapter;
import com.example.administrator.myapplication.thing_class.Until;

import java.util.ArrayList;
import java.util.List;




public class BezierViewPager extends ViewPager {

    private boolean touchable = true;
    private ShadowTransformer cardShadowTransformer;
    private Context MyContext ;
    private List DataList ;
    private boolean isAutoPlay = true;//默认自动化 , 这个可以控制他是否自动移动
    private long   AutoTime = 1500 ;
    private CardPagerAdapter cardPagerAdapter ;

    public void setAutoTime(long autoTime) {
        AutoTime = autoTime;
    }

    public void setAutoPlay(boolean autoPlay) {
        isAutoPlay = autoPlay;
    }
    public boolean isAutoPlay() {
        return isAutoPlay;
    }

    public List getDataList() {
        return DataList;
    }
    public BezierViewPager(Context context) {
        super(context);
        MyContext = context ;
        startAuto();

    }

    public void startAuto(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isAutoPlay) //只有在自动播放被允许的时候才自动播放
                {
                    setCurrentItem(getCurrentItem() + 1);
                }
                new Handler().postDelayed(this, AutoTime);//注意了这个自动播放不能放在 判断条件里面了，要不然只有在能自动播放的时候去执行下一个延时任务，会导致在变得不自动
                                                            //播放的时候，没有执行延时任务
                }
        },AutoTime);
    }

    public BezierViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        MyContext = context ;
        this.setCurrentItem(2);//这里设定初始的页数 从第三页开始
        startAuto();
    }

    public void setTouchable(boolean isCanScroll) {
        this.touchable = isCanScroll;
    }



    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (touchable) {
            return super.onTouchEvent(arg0);
        } else {
            return false;
        }
    }

    @Override
    public boolean post(Runnable action) {
        initPhotoSize();
        return super.post(action);
    }




    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (touchable) {
            return super.onInterceptTouchEvent(arg0);
        } else {
            return false;
        }

    }

    public void showTransformer(float zoomIn) {
        if (CardAdapter.class.isInstance(getAdapter())) {
            if (cardShadowTransformer == null) {
                cardShadowTransformer = new ShadowTransformer();
                cardShadowTransformer.attachViewPager(this, (CardAdapter) getAdapter());
            }
            cardShadowTransformer.setZoomIn(zoomIn);
        }
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        cardPagerAdapter = (CardPagerAdapter)this.getAdapter();

        if (cardPagerAdapter!=null){
            DataList = cardPagerAdapter.getmData();

        }
        else{
            DataList= new ArrayList();

    }}


    public void initPhotoSize()
    {

        int mWidth = ((MainActivity)MyContext).getWindowManager().getDefaultDisplay().getWidth();
        float heightRatio = 0.565f;
        int maxFactor = mWidth / 15 ;
        cardPagerAdapter.setMaxElevationFactor(maxFactor);
        int mWidthPading = mWidth / 10;
        //因为我们adapter里的cardView CornerRadius已经写死为10dp，所以0.3*CornerRadius=3
        //设置Elevation之后，控件宽度要减去 (maxFactor + dp2px(3)) * heightRatio
        //heightMore 设置Elevation之后，控件高度 比  控件宽度* heightRatio  多出的部分
        float heightMore = (1.5f * maxFactor + Until.dp2px((MainActivity)MyContext,3)) - (maxFactor + Until.dp2px((MainActivity)MyContext,3)) * heightRatio;
        int mHeightPading = (int) (mWidthPading * heightRatio - heightMore);
        this.setLayoutParams(new RelativeLayout.LayoutParams(mWidth, (int) (mWidth * heightRatio)));
    }

}
