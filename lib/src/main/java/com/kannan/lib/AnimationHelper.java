package com.kannan.lib;

import android.view.View;

import com.nineoldandroids.animation.AnimatorSet;

/**
 * Created by kannan on 1/7/17.
 */

public interface AnimationHelper {

    void initViewProperties(View view);

    AnimatorSet getShowAnimation(View view, int duration, int delay);

    AnimatorSet getHideAnimation(View view, int duration, int delay);
}
