package com.example.administrator.myapplication.util;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/10 0010.
 */

public class UiUtil {
    private static TextView headTextView(){
        AbsListView.LayoutParams lp=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,120);
        TextView textView=new TextView(ApplicationUtil.getContext());
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        textView.setPadding(60,0,0,0);
        textView.setTextSize(40);
        return textView;
    }
    private static  TextView childTextView(){
        AbsListView.LayoutParams lp=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,120);
        TextView textView=new TextView(ApplicationUtil.getContext());
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
        textView.setPadding(40,0,0,0);
        textView.setTextSize(20);
        return textView;
    }

}
