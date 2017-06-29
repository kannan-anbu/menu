package com.kannan.lib;

import android.media.Image;

/**
 * Created by kannan on 29/6/17.
 */

public class ImageTextMenuItem extends MenuItem {

    private TextMenuItem mTextMenuItem;
    private ImageMenuItem mImageMenuItem;

    public ImageTextMenuItem(ImageMenuItem imageMenuItem, TextMenuItem textMenuItem) {
        mTextMenuItem = textMenuItem;
        mImageMenuItem = imageMenuItem;
    }

    public TextMenuItem getTextMenuItem() {
        return mTextMenuItem;
    }

    public ImageMenuItem getImageMenuItem() {
        return mImageMenuItem;
    }
}
