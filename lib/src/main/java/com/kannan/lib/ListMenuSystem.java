package com.kannan.lib;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kannan on 28/6/17.
 */

public class ListMenuSystem extends AbstractMenuSystem {

    ListMenuSystem(Context context, RelativeLayout rootContainer, List<MenuItem> menuItems) {
        super(context, rootContainer, menuItems);
    }


    @Override
    protected void createMenuViews() {
        for (MenuItem menuItem : mMenuItems) {
            if (menuItem instanceof TextMenuItem) {
                mMenuContainer.addView(
                        ViewSpawner.spawnTextView(mContext, (TextMenuItem) menuItem)
                );
            } else if (menuItem instanceof ImageMenuItem) {
                mMenuContainer.addView(
                        ViewSpawner.spawnImageView(mContext, (ImageMenuItem) menuItem)
                );
            } else if (menuItem instanceof ImageTextMenuItem) {
                mMenuContainer.addView(
                        ViewSpawner.spawnImageTextView(mContext, (ImageTextMenuItem) menuItem)
                );
            }

            //change
            mMenuContainer.addView(
                    ViewSpawner.spawnSpaceView(mContext, -1, 30)
            );
        }
    }

    @Override
    protected void initialiseViewProperties() {
        for (int i = 0; i < mMenuContainer.getChildCount(); i += 1) {
            mMenuContainer.getChildAt(i).setAlpha(0.0f);
        }
    }

    @Override
    protected void buildAnimatorSet() {
        buildOpenAnimatorSet();
    }

    private void buildOpenAnimatorSet() {
        List<Animator> animations = new ArrayList<>();

        int d = 100, td = 0;
        for (int i = 0; i < mMenuContainer.getChildCount(); i += 1) {
            if (mMenuContainer.getChildAt(i) instanceof ViewGroup) {

                Animator a = AnimatorSpawner.forAlpha(
                        mMenuContainer.getChildAt(i), 0, 1
                ).setDuration(100);
                a.setStartDelay(td + i * d);
                animations.add(a);
                td += d;
            } else if (mMenuContainer.getChildAt(i) instanceof TextView) {

            } else {

            }
        }
        mCloseAnimatorSet.playTogether(animations);
        mCloseAnimatorSet.setDuration(250);
        mCloseAnimatorSet.setStartDelay(500);

    }
}
