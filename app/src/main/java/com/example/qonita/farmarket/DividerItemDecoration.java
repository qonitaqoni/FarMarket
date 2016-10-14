package com.example.qonita.farmarket;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Qonita on 10/14/2016.
 */

public class DividerItemDecoration  extends RecyclerView.ItemDecoration{
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable bDivider;

    private int bOrientation;

    public DividerItemDecoration(Context context, int orientation){
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        bDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    private void setOrientation(int orientation) {
        if(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
            throw new IllegalArgumentException("invalid orientation");

        }
        bOrientation = orientation;
    }

    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state){
        if(bOrientation == VERTICAL_LIST){
            drawVertical(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent){
        final int left = parent.getPaddingLeft();
        final  int right = parent.getWidth() - parent.getPaddingRight();

        final  int childCount = parent.getChildCount();
        for (int i= 0; i<childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final  int top = child.getBottom() + params.bottomMargin;
            final  int bottom = top + bDivider.getIntrinsicHeight();
            bDivider.setBounds(left, top, right, bottom);
            bDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent){
        final  int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final  int childCount = parent.getChildCount();

        for(int i = 0; i<childCount; i++){
            final View child = parent.getChildAt(i);
            final  RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + bDivider.getIntrinsicHeight();
            bDivider.setBounds(left, top, right, bottom    );
            bDivider.draw(c);
        }

    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        if(bOrientation == VERTICAL_LIST){
            outRect.set(0,0,0, bDivider.getIntrinsicHeight());
        }
        else {
            outRect.set(0,0, bDivider.getIntrinsicWidth(), 0);
        }
    }

}

