package com.kannan.ornate;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by kannan on 3/7/17.
 */

public class MenuItem {

    private MenuItemType mMenuItemType;

    private String mText;

    private int mImageRes;
    private Drawable mImageDrawable;
    private Bitmap mImageBitmap;

    public MenuItem(MenuItemType type) {
        mMenuItemType = type;
        mText = "";
        mImageRes = -1;
        mImageDrawable = null;
        mImageBitmap = null;
    }

    public MenuItem withText(String text) {
        mText = text;
        return this;
    }

    public MenuItem withImageRes(int imageResId) {
        mImageRes = imageResId;
        mImageDrawable = null;
        mImageBitmap = null;
        return this;
    }

    public MenuItem withImageDrawable(Drawable drawable) {
        mImageDrawable = drawable;
        mImageRes = -1;
        mImageBitmap = null;
        return this;
    }

    public MenuItem withImageBitmap(Bitmap bitmap) {
        mImageBitmap = bitmap;
        mImageRes = -1;
        mImageDrawable = null;
        return this;
    }

    public MenuItemType getType() {
        return mMenuItemType;
    }

    public String getText() {
        return mText;
    }

    public int getImageRes() {
        return mImageRes;
    }

    public Drawable getImageDrawable() {
        return mImageDrawable;
    }

    public Bitmap getImageBitmap() {
        return mImageBitmap;
    }

}
