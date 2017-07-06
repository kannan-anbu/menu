package com.kannan.ornate.theme;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.kannan.ornate.widget.MaxVerticalScrollView;
import com.kannan.ornate.MenuElementType;
import com.kannan.ornate.MenuItemType;
import com.kannan.ornate.MenuOrientation;

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
//        textView.setTextAppearance(c, mTheme.getTextAppearanceStyle()); // needs context

    }

    public void applyForImageView(ImageView imageView) {
        imageView.setScaleType(mTheme.getImageScaleType());
    }

    public void applyForSpacer(Space space) {
        ViewGroup wrapper = (ViewGroup) space.getParent();
        if (wrapper.getTag() == MenuItemType.ICON_AFTER_TEXT.getTag()
                || wrapper.getTag() == MenuItemType.ICON_BEFORE_TEXT.getTag()) {
            space.getLayoutParams().width = mTheme.getSpacingBetweenElements();
            space.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else if (wrapper.getTag() == MenuItemType.ICON_ABOVE_TEXT.getTag()
                || wrapper.getTag() == MenuItemType.ICON_BELOW_TEXT.getTag()) {
            space.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            space.getLayoutParams().height = mTheme.getSpacingBetweenElements();
        }
//        space.setBackgroundColor(mTheme.getDividerColor());
        // check for text only / icon only ?
    }

    public void applyForDivider(View divider) {
        if (divider.getTag() == MenuElementType.ELEMENT_DEVIDER.getTag()) {   // getTag() in enum
            if (mOrientation == MenuOrientation.HORIZONTAL) {
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
        if (wrapper.getTag() == MenuItemType.ICON_ONLY.getTag()) {
            applyForImageView((ImageView) wrapper.getChildAt(0));
        } else if (wrapper.getTag() == MenuItemType.TEXT_ONLY.getTag()) {
            applyForTextView((TextView) wrapper.getChildAt(0));
        } else {
            View icon;
            View text;
            View space;
            if (wrapper.getTag() == MenuItemType.ICON_BEFORE_TEXT.getTag()
                    || wrapper.getTag() == MenuItemType.ICON_ABOVE_TEXT.getTag()) {
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

    // should be called only after all children are set layoutparams
    // to hack max width / height
    public void applyForScrollContainer(FrameLayout scrollView) {
        if (scrollView instanceof MaxVerticalScrollView) {
            ((MaxVerticalScrollView) scrollView).setmMaxWidth(mTheme.getMaxWidth());
            ((MaxVerticalScrollView) scrollView).setmaxHeight(mTheme.getMaxHeight());
        }
    }
}
