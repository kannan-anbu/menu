package com.kannan.lib;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kannan on 28/6/17.
 */

public class ListMenuSystem extends AbstractMenuSystem {

    AnimationHelper helper;
    int animCount = 0;

    ListMenuSystem(Context context, RelativeLayout rootContainer, List<MenuItem> menuItems) {
        super(context, rootContainer, menuItems);
    }


    @Override
    protected void createMenuViews() {
        for (MenuItem menuItem : mMenuItems) {
            if (menuItem instanceof TextMenuItem) {
                View view = ViewSpawner.spawnWrappedTextView(mContext, (TextMenuItem) menuItem, mMenuOrientation);
                view.setTag(MenuItemStructure.TEXT.getTag());
                mMenuContainer.addView(view);
            } else if (menuItem instanceof ImageMenuItem) {
                View view = ViewSpawner.spawnWrappedImageView(mContext, (ImageMenuItem) menuItem, mMenuOrientation);
                view.setTag(MenuItemStructure.ICON.getTag());
                mMenuContainer.addView(view);
            } else if (menuItem instanceof ImageTextMenuItem) {
                View view = ViewSpawner.spawnImageTextView(mContext, (ImageTextMenuItem) menuItem);
                view.setTag(MenuItemStructure.ICON_TEXT.getTag());
                mMenuContainer.addView(view);
            }

            //change
            mMenuContainer.addView(
                    ViewSpawner.spawnSpaceView(mContext, -1, 30)
            );
        }
        helper = new AlphaAnimationHelper(mMenuAnimationDirection);
    }

    @Override
    protected void initialiseViewProperties() {
        for (int i = 0; i < mMenuContainer.getChildCount(); i += 1) {
            View view = mMenuContainer.getChildAt(i);
            if (isAnimatable(view)) {
                helper.initViewProperties(view);
            }
        }
    }

    @Override
    protected void buildAnimatorSet() {
        buildOpenAnimatorSet();
    }

    private void buildOpenAnimatorSet() {
        int dt = 5000;
        animCount = countAnimatables();
        int d = dt / animCount;
        int del = d/2;
        int x = 0;

        List<Animator> al = new ArrayList<>();

        for (int i = 0; i < mMenuContainer.getChildCount(); i += 1) {
            View item = mMenuContainer.getChildAt(i);
            if (isAnimatable(item)) {
                al.addAll(helper.getShowAnimation(item, d, del*x));
                x += 1;

            }
        }
        mOpenAnimatorSet.playTogether(al);
        mOpenAnimatorSet.setStartDelay(1000);
    }

    boolean isAnimatable(View view) {
        String tag = (String) view.getTag();
        if (tag != null && (tag == MenuItemStructure.TEXT.getTag()
                || tag == MenuItemStructure.ICON.getTag()
                || tag == MenuItemStructure.ICON_TEXT.getTag())) {
            return true;
        }
        return false;
    }

    int countAnimatables() {
        int count = 0;
        for (int i = 0; i < mMenuContainer.getChildCount(); i += 1) {
            View menuItem = mMenuContainer.getChildAt(i);
            if (isAnimatable(menuItem)) {
                count++;
            }
        }
        return count;
    }

}
