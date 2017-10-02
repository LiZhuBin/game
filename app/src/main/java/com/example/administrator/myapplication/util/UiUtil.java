package com.example.administrator.myapplication.util;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.sackcentury.shinebuttonlib.ShineButton;
import com.wx.goodview.GoodView;

/**
 * Created by Administrator on 2017/9/10 0010.
 */

public class UiUtil {
   public static GoodView mGoodView= new GoodView(ApplicationUtil.getContext());
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
    public static void good(View view, final ShineButton shineButton, final String str){
        shineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shineButton.isChecked()) {
                    mGoodView.setTextInfo(str, Color.RED,20);
                    mGoodView.show(view);
                }
            }
        });
    }

}
