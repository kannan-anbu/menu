package com.kannan.ornate.menu;

import com.kannan.ornate.MenuPosition;
import com.kannan.ornate.MenuItem;
import com.kannan.ornate.MenuOrientation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kannan on 28/6/17.
 */

public class MenuDialogFragmentOptions implements Serializable {

    private List<MenuItem> mMenuItems;

    private MenuPosition mMenuPosition;
//    private MenuAnimationDirection mMenuAnimationDirection;
    private MenuOrientation mMenuOrientation;
    private float mDividerSpacingDP;


    public MenuDialogFragmentOptions() {
        mMenuItems = null;
        mDividerSpacingDP = 0;
        mMenuPosition = MenuPosition.CENTER;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        mMenuItems = menuItems;
    }

    public void setDividerSpacingDP(float spacing) {
        mDividerSpacingDP = spacing;
    }

    public void setMenuGravity(MenuPosition gravity) {
        mMenuPosition = gravity;
    }

//    public void setMenuAnimationDirection(MenuAnimationDirection direction) {
//        mMenuAnimationDirection = direction;
//    }

    public void setMenuOrientation(MenuOrientation orientation) {
        mMenuOrientation = orientation;
    }



    public List<MenuItem> getMenuItems() {
        return mMenuItems;
    }

    public float getDividerSpacingDP() {
        return mDividerSpacingDP;
    }

    public MenuPosition getMenuGravity() {
        return mMenuPosition;
    }

//    public MenuAnimationDirection getMenuAnimationDirection() {
//        return mMenuAnimationDirection;
//    }

    public MenuOrientation getMenuOrientation() {
        return mMenuOrientation;
    }
}
