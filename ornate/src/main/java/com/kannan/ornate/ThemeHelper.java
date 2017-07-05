package com.kannan.ornate;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
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

    public void applyForSpacer(Space space) {
        ViewGroup wrapper = (ViewGroup) space.getParent();
        if (wrapper.getTag() == MenuItemType.ICON_AFTER_TEXT
                || wrapper.getTag() == MenuItemType.ICON_BEFORE_TEXT) {
            space.getLayoutParams().width = mTheme.getSpacingBetweenElements();
            space.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else if (wrapper.getTag() == MenuItemType.ICON_ABOVE_TEXT
                || wrapper.getTag() == MenuItemType.ICON_BELOW_TEXT) {
            space.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            space.getLayoutParams().height = mTheme.getSpacingBetweenElements();
        }
        // check for text only / icon only ?
    }

    public void applyForDivider(Space divider) {
        if (divider.getTag() == MenuElementType.ELEMENT_DEVIDER) {
            if (mOrientation == MenuOrientation.VERTICAL) {
                divider.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                divider.getLayoutParams().width = mTheme.getDividerThickness();
            } else {
                divider.getLayoutParams().height = mTheme.getDividerThickness();
                divider.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            }
            divider.setBackgroundColor(mTheme.getDividerColor());
        }
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
        if (wrapper.getTag() == MenuItemType.ICON_ONLY) {
            applyForImageView((ImageView) wrapper.getChildAt(0));
        } else if (wrapper.getTag() == MenuItemType.TEXT_ONLY) {
            applyForTextView((TextView) wrapper.getChildAt(0));
        } else {
            View icon;
            View text;
            View space;
            if (wrapper.getTag() == MenuItemType.ICON_BEFORE_TEXT) {
                icon = wrapper.getChildAt(0);
                space = wrapper.getChildAt(1);
                text = wrapper.getChildAt(2);
            } else {
                text = wrapper.getChildAt(0);
                space = wrapper.getChildAt(1);
                icon = wrapper.getChildAt(2);
            }

            applyForTextView((TextView) text);
            applyForImageView((ImageView) icon);
            applyForSpacer((Space) space);
//
//            // increase padding of first element in menuitem to simulate spacing
//            // between elements
//            int paddingToIncrease = mTheme.getSpacingBetweenElements();
//            int newPaddingRight = 0;
//            int newPaddingBottom = 0;
//
//            // check for type of menusystem
//
//            child1.setPadding(
//                    child1.getPaddingLeft(),
//                    child1.getPaddingTop(),
//                    child1.getPaddingRight() + mTheme.getSpacingBetweenElements(),
//                    child1.getPaddingBottom()
//            );
        }

    }
}
