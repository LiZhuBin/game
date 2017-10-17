package com.example.administrator.happygame.RecyclerView_itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.happygame.R;

/**
 * Created by lenovo on 2017/9/13.
 */

public class hotpoint_item extends RecyclerView.ItemDecoration {

    private int fenxi;

    public hotpoint_item(Context context) {
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
        int pos = parent.getChildAdapterPosition(view);
        //现在的view
        if (pos == 0) {
            outRect.right = fenxi;
            outRect.bottom = fenxi;
        }
        if (pos == 1) {
            outRect.bottom = fenxi;
        }
        if (pos == 2) {
            outRect.right = fenxi;
        }
    }
}
