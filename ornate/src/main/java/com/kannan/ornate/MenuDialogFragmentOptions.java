package com.kannan.ornate;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kannan on 28/6/17.
 */

public class MenuDialogFragmentOptions implements Serializable {

    private List<MenuItem> mMenuItems;

    private MenuGravity mMenuGravity;
    private MenuAnimationDirection mMenuAnimationDirection;
    private MenuOrientation mMenuOrientation;
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

    public void setMenuAnimationDirection(MenuAnimationDirection direction) {
        mMenuAnimationDirection = direction;
    }

    public void setMenuOrientation(MenuOrientation orientation) {
        mMenuOrientation = orientation;
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

    public MenuAnimationDirection getMenuAnimationDirection() {
        return mMenuAnimationDirection;
    }

    public MenuOrientation getMenuOrientation() {
        return mMenuOrientation;
    }
}
