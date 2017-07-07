package com.kannan.ornate.animation;

import android.view.View;

import com.nineoldandroids.animation.Animator;

import java.util.List;

/**
 * Created by kannan on 6/7/17.
 */

public interface AnimationModel {

    void initMenuItem(View view);

    void initManuContainer(View view);

    void initBgOverlay(View view);

    List<Animator> getMenuItemOpenAnimations(View view, int duration, int delay);

    List<Animator> getMenuItemCloseAnimations(View view, int duration, int delay);

    List<Animator> getMenuContainerOpenAnimations(View view, int duration, int delay);

    List<Animator> getMenuContainerCloseAnimations(View view, int duration, int delay);

    List<Animator> getBgOverlayOpenAnimations(View view, int duration, int delay);

    List<Animator> getBgOverlayCloseAnimations(View view, int duration, int delay);

}
