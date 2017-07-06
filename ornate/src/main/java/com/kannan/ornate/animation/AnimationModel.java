package com.kannan.ornate.animation;

import android.view.View;

import com.nineoldandroids.animation.Animator;

import java.util.List;

/**
 * Created by kannan on 6/7/17.
 */

public interface AnimationModel {

    void initView(View view);

    List<Animator> getOpenAnimations(View view, int duration, int delay);

    List<Animator> getCloseAnimations(View view, int duration, int delay);

}
