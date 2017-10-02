package com.example.administrator.myapplication.thing_class;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class lookphotoitemdecorate extends RecyclerView.ItemDecoration {

    private  int ChlidCount;
    private  int fenxi_bian;
    private  int fenxi_other;

    public lookphotoitemdecorate(Context context) {
        super();
        fenxi_bian = 10;
        fenxi_other= 10;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (ChlidCount==0){ parent.getChildCount(); }

        if (parent.getChildAdapterPosition(view)%2==0)
        {
            outRect.right = fenxi_bian ;
            outRect.top = fenxi_bian ;
        }else{
            outRect.top=fenxi_bian;
        }


    }
}