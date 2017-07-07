package com.kannan.ornate.theme;

import android.graphics.Color;
import android.widget.ImageView;

import com.kannan.ornate.R;

/**
 * Created by kannan on 3/7/17.
 */

public class Theme {

    public int getMenuItemWidth() {
        return 300; // to DP
    }

    public int getMenuItemHeight() {
        return 100;  // to DP
    }

//    ------------------------

    public int getTextAppearanceStyle() {
        return R.style.TextAppearance_AppCompat_Light_Widget_PopupMenu_Large;
    }

    public ImageView.ScaleType getImageScaleType() {
        return ImageView.ScaleType.CENTER_CROP;
    }

    public Padding getMenuItemPadding() {
        return new Padding(15, 15, 15, 15);
        // to DP
    }

//    public int getMenuItemSpacing() {
//        return 20;  // to DP;
//    }

    public int getDividerThickness() {
        return 0;   // to DP;
    }

    public int getDividerColor() {
        return Color.TRANSPARENT;
    }

    public int getSpacingBetweenElements() {
        return 20; // to DP;
    }

    public int getMenuItemBgColor() {
        return Color.WHITE;
    }

    public int getMenuBgColor() {
        return Color.WHITE;
    }

    public int getMenuOverlayColor() {
        return Color.parseColor("#5f000000");
//        return Color.TRANSPARENT;
    }




//    manage gravity in ThemeHelper





    public static class Padding {

        public int left;
        public int top;
        public int right;
        public int bottom;

        public Padding(int left, int top, int right, int bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }
    }

    public static class Margin extends Padding {

        public Margin(int left, int top, int right, int bottom) {
            super(left, top, right, bottom);
        }
    }


}
