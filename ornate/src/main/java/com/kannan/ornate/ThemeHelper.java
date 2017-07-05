package com.kannan.ornate;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by kannan on 4/7/17.
 */

public class ThemeHelper {

    private Theme mTheme;

    private MenuOrientation mOrientation;

    public ThemeHelper(Theme theme, MenuOrientation orientation) {
        mTheme = theme;
        mOrientation = orientation;
    }

    public void applyForTextView(TextView textView) {
        textView.setTextAppearance(c, mTheme.getTextAppearanceStyle()); // needs context

    }

    public void applyForImageView(ImageView imageView) {
        imageView.setScaleType(mTheme.getImageScaleType());
    }

    public void applyForMenuItem(LinearLayout wrapper) {
        int w, h;
        if (mOrientation == MenuOrientation.HORIZONTAL) {
                w = ViewGroup.LayoutParams.WRAP_CONTENT;
                h = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            w = ViewGroup.LayoutParams.MATCH_PARENT;
            h = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        wrapper.getLayoutParams().height = h;
        wrapper.getLayoutParams().width = w;

        wrapper.setBackgroundColor(mTheme.getMenuItemBgColor());
        Theme.Padding padding = mTheme.getMenuItemPadding();
        wrapper.setPadding(
                padding.left,
                padding.top,
                padding.right,
                padding.bottom
        );


//        check for spacer element first;
        if (wrapper.getTag() == MenuItemType.ITEM_ICON) {
            applyForImageView((ImageView) wrapper.getChildAt(0));
        } else if (wrapper.getTag() == MenuItemType.ITEM_TEXT) {
            applyForTextView((TextView) wrapper.getChildAt(0));
        } else {
            View child1 = wrapper.getChildAt(0);
            View child2 = wrapper.getChildAt(1);
            if (wrapper.getTag() == MenuItemType.ITEM_ICON_TEXT) {
                applyForImageView((ImageView) child1);
                applyForTextView((TextView) child2);
            } else {
                applyForTextView((TextView) child1);
                applyForImageView((ImageView) child2);
            }

            // increase padding of first element in menuitem to simulate spacing
            // between elements
            int paddingToIncrease = mTheme.getSpacingBetweenElements();
            int newPaddingRight = 0;
            int newPaddingBottom = 0;

            // check for type of menusystem

            child1.setPadding(
                    child1.getPaddingLeft(),
                    child1.getPaddingTop(),
                    child1.getPaddingRight() + mTheme.getSpacingBetweenElements(),
                    child1.getPaddingBottom()
            );
        }

    }
}
