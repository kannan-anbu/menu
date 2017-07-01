package com.kannan.lib;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kannan on 28/6/17.
 */

public class MenuDialogFragmentOptions implements Serializable {

    private List<MenuItem> mMenuItems;

    private MenuGravity mMenuGravity;
    private float mDividerSpacingDP;


    public MenuDialogFragmentOptions() {
        mMenuItems = null;
        mDividerSpacingDP = 0;
        mMenuGravity = MenuGravity.CENTER;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        mMenuItems = menuItems;
    }

    public void setDividerSpacingDP(float spacing) {
        mDividerSpacingDP = spacing;
    }

    public void setMenuGravity(MenuGravity gravity) {
        mMenuGravity = gravity;
    }




    public List<MenuItem> getMenuItems() {
        return mMenuItems;
    }

    public float getDividerSpacingDP() {
        return mDividerSpacingDP;
    }

    public MenuGravity getMenuGravity() {
        return mMenuGravity;
    }
}
