package com.kannan.ornate;

import android.graphics.Color;
import android.widget.ImageView;

/**
 * Created by kannan on 3/7/17.
 */

public class Theme {

    public int getMenuItemWidth() {
        return 250; // to DP
    }

    public int getMenuItemHeight() {
        return 60;  // to DP
    }

//    ------------------------

    public int getTextAppearanceStyle() {
        return R.style.TextAppearance_AppCompat;
    }

    public ImageView.ScaleType getImageScaleType() {
        return ImageView.ScaleType.CENTER_CROP;
    }

    public Padding getMenuItemPadding() {
        return new Padding(10, 10, 10, 10);
        // to DP
    }

    public int getMenuItemSpacing() {
        return 20;  // to DP;
    }

    public int getDividerThickness() {
        return 1;   // to DP;
    }

    public int getDividerColor() {
        return Color.TRANSPARENT;
    }

    public int getSpacingBetweenElements() {
        return 20; // to DP;
    }

    public int getMenuItemBgColor() {
        return Color.TRANSPARENT;
    }




//    manage gravity in ThemeHelper





    class Padding {

        public int left;
        public int top;
        public int right;
        public int bottom;

        Padding(int left, int top, int right, int bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }
    }


}
