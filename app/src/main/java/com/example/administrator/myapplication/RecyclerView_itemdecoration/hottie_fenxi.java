package com.example.administrator.myapplication.RecyclerView_itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.myapplication.R;


/**
 * Created by lenovo on 2017/9/12.
 */

public class hottie_fenxi extends RecyclerView.ItemDecoration {

    private int fenxi;

    public hottie_fenxi(Context context) {
        super();
        fenxi = context.getResources().getDimensionPixelSize(R.dimen.Fenxi);
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

        int ChildCount = parent.getChildCount() ;
        int pos = parent.getChildAdapterPosition(view);
        if (pos != ChildCount+1)
        {
            outRect.right = fenxi ;
       }else
       {
           return;
       }


    }
}
