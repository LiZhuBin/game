package com.example.administrator.happygame.thing_class;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/10/1 0001.
 */
public class folderitemdecoration extends RecyclerView.ItemDecoration {

    private Context MyContext;

    public folderitemdecoration(Context context) {
        super();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#E8E8E8"));

        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 1; i < childCount; i++) {
            View view = parent.getChildAt(i);
            float bottom = view.getTop();
            float top = view.getTop() - 5;

            c.drawRect(left, top, right, bottom, paint);

        }


    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int ChildCount = parent.getChildCount();

        int pos = parent.getChildAdapterPosition(view);
        if (pos != 0) {
            outRect.top = 5;
        }

    }
}
