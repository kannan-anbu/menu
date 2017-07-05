package com.kannan.ornate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.Animator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kannan on 28/6/17.
 */

public class ListMenuSystem extends AbstractMenuSystem {

//    AnimationHelper helper;
    int animCount = 0;

    LinearLayout mMenuContainer;

    ListMenuSystem(Context context) {
        super(context);
    }


    @Override
    protected void createMenuViews() {
        for (MenuItem menuItem : mMenuItems) {
            switch (menuItem.getType()) {
                case TEXT_ONLY:
                    View wrappedText = ViewSpawner.spawnWrappedTextView(mContext, menuItem);
                    wrappedText.setTag(menuItem.getType().getTag());
                    mMenuContainer.addView(wrappedText);
                    break;
                case ICON_ONLY:
                    View wrappedIcon = ViewSpawner.spawnWrappedImageView(mContext, menuItem);
                    wrappedIcon.setTag(menuItem.getType().getTag());
                    mMenuContainer.addView(wrappedIcon);
                    break;
                case ICON_BEFORE_TEXT: {
                    LinearLayout wrapper = ViewSpawner.spawnLinearLayout(mContext);
                    wrapper.setOrientation(LinearLayout.HORIZONTAL);
                    View text = ViewSpawner.spawnTextView(mContext, menuItem);
                    View icon = ViewSpawner.spawnImageView(mContext, menuItem);
                    wrapper.addView(icon);
                    wrapper.addView(text);
                    mMenuContainer.addView(wrapper);
                    break;
                }
                case ICON_AFTER_TEXT: {
                    LinearLayout wrapper = ViewSpawner.spawnLinearLayout(mContext);
                    wrapper.setOrientation(LinearLayout.HORIZONTAL);
                    View text = ViewSpawner.spawnTextView(mContext, menuItem);
                    View icon = ViewSpawner.spawnImageView(mContext, menuItem);
                    wrapper.addView(text);
                    wrapper.addView(icon);
                    mMenuContainer.addView(wrapper);
                    break;
                }
            }

            //change
//            mMenuContainer.addView(
//                    ViewSpawner.spawnSpaceView(mContext, -1, 30)
//            );
        }
//        helper = new AlphaAnimationHelper(mMenuAnimationDirection);
    }

    @Override
    void attachMenuContainer(FrameLayout parent, MenuOrientation orientation, int width, int height) {
        mMenuContainer = new LinearLayout(mContext);
        mMenuContainer.setOrientation(
                orientation == MenuOrientation.HORIZONTAL
                        ? LinearLayout.HORIZONTAL
                        : LinearLayout.VERTICAL
        );
        ViewGroup.LayoutParams menuContainerLP = new ViewGroup.LayoutParams(
                width, height
        );
        mMenuContainer.setLayoutParams(menuContainerLP);
        parent.addView(mMenuContainer);
    }

    @Override
    protected void initialiseViewProperties() {
        for (int i = 0; i < mMenuContainer.getChildCount(); i += 1) {
            View view = mMenuContainer.getChildAt(i);
            if (isAnimatable(view)) {
//                helper.initViewProperties(view);
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
