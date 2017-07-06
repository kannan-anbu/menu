package com.kannan.ornate.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by kannan on 6/7/17.
 */

public class MaxVerticalScrollView extends ScrollView {

    public static final int NO_LIMIT = -1;

    private int mMaxWidth = NO_LIMIT;

    private int mMaxHeight = NO_LIMIT;

    public MaxVerticalScrollView(Context context) {
        super(context);
    }

    public MaxVerticalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxVerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (mMaxWidth > -1 && mMaxHeight > -1) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST);
        }

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
