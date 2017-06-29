package com.kannan.lib;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by kannan on 29/6/17.
 */

public class AnimatorSpawner {

    public static ObjectAnimator forAlpha(View view, float fromAlpha, float toAlpha) {
        return ObjectAnimator.ofFloat(view, "alpha", fromAlpha, toAlpha);
    }

    public static ObjectAnimator forRotationX(View view, int fromAngle, int toAngle) {
        return ObjectAnimator.ofFloat(view, "rotationX", fromAngle, toAngle);
    }

    public static ObjectAnimator forRotationY(View view, int fromAngle, int toAngle) {
        return ObjectAnimator.ofFloat(view, "rotationY", fromAngle, toAngle);
    }

    public static ObjectAnimator fronTranslationX(View view, float fromTrans, float toTrans) {
        return ObjectAnimator.ofFloat(view, "translationX", fromTrans, toTrans);
    }

    public static ObjectAnimator fronTranslationY(View view, float fromTrans, float toTrans) {
        return ObjectAnimator.ofFloat(view, "translationY", fromTrans, toTrans);
    }

}
