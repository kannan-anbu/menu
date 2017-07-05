package com.kannan.ornate;

/**
 * Created by kannan on 3/7/17.
 */

public class MenuItem {

    private MenuItemType mMenuItemType;

    private String mText;

    private int mImageRes;

    public MenuItem(MenuItemType type) {
        mMenuItemType = type;
    }

    public MenuItem setText(String text) {
        mText = text;
        return this;
    }

    public MenuItem setImageRes(int imageResId) {
        mImageRes = imageResId;
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


}
