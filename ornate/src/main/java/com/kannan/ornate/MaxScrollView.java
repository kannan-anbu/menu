package com.kannan.ornate;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by kannan on 6/7/17.
 */

public class MaxScrollView extends ScrollView {

    private int mMaxWidth;

    private int mMaxHeight;

    public MaxScrollView(Context context) {
        super(context);
    }

    public MaxScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, MeasureSpec.AT_MOST);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setmMaxWidth(int maxWidth) {
        mMaxWidth = maxWidth;
        requestLayout();
    }

    public void setmaxHeight(int maxHeight) {
        mMaxHeight = maxHeight;
        requestLayout();
    }
}
