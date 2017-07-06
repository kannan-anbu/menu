package com.kannan.ornate.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by kannan on 6/7/17.
 */

public class MaxHorizontalScrollView extends HorizontalScrollView {

    public static final int NO_LIMIT = -1;

    private int mMaxWidth = NO_LIMIT;

    private int mMaxHeight = NO_LIMIT;

    public MaxHorizontalScrollView(Context context) {
        super(context);
    }

    public MaxHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (mMaxWidth > -1)
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, MeasureSpec.AT_MOST);
        if (mMaxHeight > -1)
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setMaxWidth(int maxWidth) {
        mMaxWidth = maxWidth;
        requestLayout();
    }

    public void setMaxHeight(int maxHeight) {
        mMaxHeight = maxHeight;
        requestLayout();
    }
}
