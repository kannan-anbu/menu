package com.kannan.lib;

import android.view.View;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kannan on 1/7/17.
 */

public class AlphaAnimationHelper implements AnimationHelper {

    public AlphaAnimationHelper() { }

    public void initViewProperties(View view) {
                view.setAlpha(0.0f);

    }

    public AnimatorSet getShowAnimation(View view,int dur,int del) {
                Animator atr = AnimatorSpawner.forAlpha(view, 0.0f, 1.0f);
                atr.setDuration(dur);
                atr.setStartDelay( del);
        AnimatorSet set =  new AnimatorSet();
        set.playTogether(atr);
        return set;
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
