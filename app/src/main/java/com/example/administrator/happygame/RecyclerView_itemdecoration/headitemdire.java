package com.example.administrator.happygame.RecyclerView_itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.happygame.R;

/**
 * Created by lenovo on 2017/9/15.
 */

public class headitemdire extends RecyclerView.ItemDecoration {

    private int ChlidCount;
    private int fenxi_bian;
    private int fenxi_other;

    public headitemdire(Context context) {
        super();
        fenxi_bian = context.getResources().getDimensionPixelSize(R.dimen.HeadVIEW_bian);
        fenxi_other = context.getResources().getDimensionPixelSize(R.dimen.HeadView_others);
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

        if (ChlidCount == 0) {
            parent.getChildCount();
        }

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = fenxi_bian;
            outRect.right = fenxi_other;
        } else if (parent.getChildAdapterPosition(view) != ChlidCount) {
            outRect.right = fenxi_other;
        } else {
            outRect.right = fenxi_bian;
        }
        outRect.top = fenxi_bian;
        outRect.bottom = fenxi_bian;

    }
}
