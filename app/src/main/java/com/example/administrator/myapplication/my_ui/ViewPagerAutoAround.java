package com.example.administrator.myapplication.my_ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAutoAround extends LinearLayout {
    private boolean  isAutoPlay;
    private List<String>    TitleList;
    private List<TextView>  NewsTitle;
    private TextView textView;
    private ViewPager viewPager;
    private LinearLayout linearLayout_point;
    private TypedArray a;
    private List<Integer> ImageReslist ;
    private List<ImageView> ImageViewList;
    private List<ImageView> PointViewlist;
    private long PauseTime;
    private Context mContext;
    ImageView Previous_point;
    private Handler mHander=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           if(isAutoPlay) {
               int NextCurrent = viewPager.getCurrentItem() + 1;
               if (NextCurrent == viewPager.getAdapter().getCount() - 1) {
                   viewPager.setCurrentItem(0, false);
                   NextCurrent = 1;
               }
               viewPager.setCurrentItem(NextCurrent);
           }
               sendEmptyMessageDelayed(0,PauseTime);
        }
    };






    public ViewPagerAutoAround(Context context) {
        super(context);
    }

    public ViewPagerAutoAround(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
       // a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        LayoutInflater.from(mContext).inflate(R.layout.viewaround_layout,this);
        linearLayout_point = (LinearLayout) findViewById(R.id.Layout_point);
        viewPager = (ViewPager) findViewById(R.id.Viewpager);
        initList();
        initPoint();
        initConget();

        viewPager.setCurrentItem(1);
        PauseTime = 3000;
       mHander.sendEmptyMessageDelayed(0,PauseTime);
      // a.recycle();
    }

    private void initNesTitle() {
        NewsTitle = new ArrayList<TextView>();
        String firsttitle = TitleList.get(0);
        String lasttitle  = TitleList.get(ImageReslist.size()-1);
        for (int i =0 ; i<viewPager.getAdapter().getCount();i++)
        {
            TextView text = new TextView(mContext);
            if (i==0){ text.setText(lasttitle); }
            else if (i==viewPager.getAdapter().getCount()-1){text.setText(firsttitle);}
            else {
                text.setText(TitleList.get(i-1));
            }
            NewsTitle.add(text);
        }

    }

    public ViewPagerAutoAround(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void initConget() {
      /*String title = a.getString(R.styleable.ViewPagerAutoAround_Titlewordk);
         int color = a.getColor(R.styleable.ViewPagerAutoAround_TitleColor, ContextCompat.getColor(getContext(),R.color.titleBackgroundColor));
        float Textsize = a.getFloat(R.styleable.ViewPagerAutoAround_TitleTextSize, 10);
        textView.setText(title);
        textView.setTextColor(color);
        textView.setTextSize(Textsize);*/
     viewPager.setAdapter(new MyAdapter(ImageViewList));
       viewPager.setOnPageChangeListener(new MyPagerListener());
    }

    private void initList() {
            if(ImageReslist==null) {
                ImageReslist = new ArrayList<Integer>();
                ImageReslist.add(R.drawable.image_scrolling_head);
                ImageReslist.add(R.drawable.image_whilte_me);
                ImageReslist.add(R.drawable.image_whilte_me);
                ImageReslist.add(R.drawable.image_whilte_me);
                ImageReslist.add(R.drawable.image_whilte_me);
            }
        ArrayList<ImageView> list = new ArrayList<>();
        for (int ID : ImageReslist) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(ID);
            list.add(imageView);
        }

        ImageView first = new ImageView(mContext);
        ImageView last = new ImageView(mContext);
        int firstid = ImageReslist.get(0);
        int lastid = ImageReslist.get(ImageReslist.size() - 1);
        first.setImageResource(lastid);
        last.setImageResource(firstid);
        list.add(0, first);
        list.add(list.size(), last);
        ImageViewList = list;
    }

    public void setImageReslist(List<Integer> list) {
        ImageReslist = list;
        initList();
        initPoint();
        initConget();
        viewPager.setCurrentItem(1);
        PauseTime = 3000;
        mHander.sendEmptyMessageDelayed(0,PauseTime);

    }

    class MyAdapter extends PagerAdapter {
        private List<ImageView> mylist;
        public MyAdapter(List<ImageView> mylist) {
            this.mylist = new ArrayList<ImageView>();
            for (ImageView one : mylist)
            {
                this.mylist.add(one);
            }

        }
        @Override
        public int getCount() {
            return mylist.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mylist.get(position);
            container.addView(view);
            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) mylist.get(position));
        }
    }

    class MyPagerListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            changePointColor(position);
        }

        private void changePointColor(int position) {

            if(position== viewPager.getAdapter().getCount()-1)
            {
                position = 1 ;
            }
            else if (position==0)
            {
                position = viewPager.getAdapter().getCount()-2;
            }

            int ChangePointNumber = position-1;
            if (Previous_point==null){ Previous_point = PointViewlist.get(0); Previous_point.setEnabled(false); }
            else { Previous_point.setEnabled(false);}
            ImageView point = PointViewlist.get(ChangePointNumber);
            point.setEnabled(true);
            Previous_point = point ;

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager.SCROLL_STATE_DRAGGING://这种拖动必须有手来完成
                    isAutoPlay = false;
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1) {
                        viewPager.setCurrentItem(1, false);
                    } else if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 2, false);
                    }
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    isAutoPlay = true;
                    break;
                case ViewPager.SCROLL_STATE_SETTLING:
                    isAutoPlay = true;
                    break;
            }
        }
    }

    ;

    private void initPoint() {
        linearLayout_point = (LinearLayout)findViewById(R.id.Layout_point);
        if (PointViewlist!=null) {
            for (ImageView one : PointViewlist) {
                linearLayout_point.removeView(one);
            }
        }
        PointViewlist = new ArrayList<ImageView>();
        for (int i = 0; i < ImageReslist.size(); i++) {
            ImageView point = new ImageView(mContext);
            LayoutParams lp = new LayoutParams(30, 30);
            lp.leftMargin = 30;
            lp.topMargin = 20;
            point.setLayoutParams(lp);
            point.setImageResource(R.drawable.point);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            final int finalI = i+1;
            point.setOnClickListener(new OnClickListener() {
                int Go_Item ;
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(finalI);
                }
            });
            linearLayout_point.addView(point);
            PointViewlist.add(point);
        }
    }

    public ViewPager getViewPager() {
        return viewPager;
    }



}
