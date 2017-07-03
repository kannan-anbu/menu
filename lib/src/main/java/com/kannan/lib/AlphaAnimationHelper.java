package com.kannan.lib;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by kannan on 1/7/17.
 */

public class AlphaAnimationHelper implements AnimationHelper {

    MenuAnimationDirection animDirection;
    float translation = 100;
    float fromTranslationX = 0;
    float toTranslationX = 0;
    float fromTranslationY = 0;
    float toTranslationY = 0;
    public AlphaAnimationHelper(MenuAnimationDirection direction) {
        animDirection = direction;
        switch (animDirection) {
            case LEFT_TO_RIGHT:
                fromTranslationX = -translation;
                toTranslationX = 0;
                break;
            case RIGHT_TO_LEFT:
                fromTranslationX = translation;
                toTranslationX = 0;
                break;
            case TOP_TO_BOTTOM:
                fromTranslationY = -translation;
                toTranslationY = 0;
                break;
            case BOTTOM_TO_TOP:
                fromTranslationY = translation;
                toTranslationY = 0;
        }
    }

    public void initViewProperties(View view) {
        if (view instanceof ViewGroup) {
            initViewGroup((ViewGroup) view);
        } else {
            view.setAlpha(0.0f);
        }
    }

    private void initViewGroup(ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i += 1) {
            View child = vg.getChildAt(i);
            if (child instanceof ViewGroup) {
                initViewGroup((ViewGroup) child);
            } else {
                child.setAlpha(0.0f);
            }
        }
    }

    public List<Animator> getShowAnimation(View view,int dur,int del) {
        ViewGroup container = ((ViewGroup) view);
        String tag = (String) container.getTag();
        List<Animator> animList = new ArrayList<>();
        Animator containerAnimator1 = AnimatorSpawner.forTranslationX(container, fromTranslationX, toTranslationX);
        containerAnimator1.setDuration(dur);
        containerAnimator1.setStartDelay(del);
        Animator containerAnimator2 = AnimatorSpawner.forTranslationY(container, fromTranslationY, toTranslationY);
        containerAnimator2.setDuration(dur);
        containerAnimator2.setStartDelay(del);
        Animator containerAnimator3 = AnimatorSpawner.forScaleX(container, 0.5f, 1);
        containerAnimator3.setDuration(dur);
        containerAnimator3.setStartDelay(del);
        Animator containerAnimator4 = AnimatorSpawner.forScaleY(container, 0.5f, 1);
        containerAnimator4.setDuration(dur);
        containerAnimator4.setStartDelay(del);
        animList.add(containerAnimator1);
        animList.add(containerAnimator2);
//        animList.add(containerAnimator3);
        animList.add(containerAnimator4);
        if (tag.equals(MenuItemStructure.TEXT.getTag())
                || tag.equals(MenuItemStructure.ICON.getTag())) {
//            Log.i("app", "text / icon");
            View textView = container.getChildAt(0);
            Animator textAnimator = AnimatorSpawner.forAlpha(textView, 0.0f, 1.0f);
            textAnimator.setDuration(dur);
            textAnimator.setStartDelay(del);
            animList.add(textAnimator);
        } else if (tag.equals(MenuItemStructure.ICON_TEXT.getTag())) {
//            Log.i("app", "icon text");
            View imgView = container.getChildAt(0);
            Animator imgAnimator = AnimatorSpawner.forAlpha(imgView, 0.0f, 1.0f);
            imgAnimator.setDuration(dur);
            imgAnimator.setStartDelay(del);
            View textView = container.getChildAt(1);
//            Animator textAnimator = AnimatorSpawner.forRotationX(imgView, 0, 90);
            Animator textAnimator = AnimatorSpawner.forAlpha(textView, 0.0f, 1.0f);
            textAnimator.setDuration( (dur));
//            textAnimator.setDuration((int) (dur * 0.5f));
            textAnimator.setStartDelay( (del));
//            textAnimator.setStartDelay((int) (del + dur * 0.5f));
            animList.add(imgAnimator);
            animList.add(textAnimator);
        } else {
            Log.i("app", "not reached");
        }
//        AnimatorSet set =  new AnimatorSet();
//        set.playSequentially(animList);
        for (Animator atr : animList) {
            atr.setInterpolator(new DecelerateInterpolator());
        }
        return animList;
    }

    public AnimatorSet getHideAnimation(View view,int dur,int del) {
        Animator atr = AnimatorSpawner.forAlpha(view, 1.0f, 0.0f);
        atr.setDuration(dur);
        atr.setStartDelay( del);
        AnimatorSet set =  new AnimatorSet();
        set.playTogether(atr);
        return set;
    }
}
