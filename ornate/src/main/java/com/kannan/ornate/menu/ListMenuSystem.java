package com.kannan.ornate.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.kannan.ornate.MenuElementType;
import com.kannan.ornate.MenuItemType;
import com.kannan.ornate.MenuPosition;
import com.kannan.ornate.MenuItem;
import com.kannan.ornate.MenuOrientation;
import com.kannan.ornate.animation.AnimationHelper;
import com.kannan.ornate.animation.AnimationModel;
import com.kannan.ornate.theme.ThemeHelper;
import com.kannan.ornate.utils.ViewSpawner;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kannan on 28/6/17.
 */

public class ListMenuSystem extends AbstractMenuSystem {

    private Context mContext;

    private LinearLayout mMenuContainer;

    public ListMenuSystem(Context context, List<MenuItem> menuItems,
                          MenuPosition gravity, MenuOrientation orientation) {
        super(context, menuItems, gravity, orientation);
        mContext = context;
    }


    @Override
    protected void createMenuViews() {
        for (int i = 0; i < getMenuItemsCount(); i += 1) {
            MenuItem menuItem = getMenuItem(i);
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
                case ICON_AFTER_TEXT: {
                    LinearLayout wrapper = ViewSpawner.spawnLinearLayout(mContext);
                    wrapper.setOrientation(LinearLayout.HORIZONTAL);
                    View text = ViewSpawner.spawnTextView(mContext, menuItem);
                    View icon = ViewSpawner.spawnImageView(mContext, menuItem);
                    View space = ViewSpawner.spawnSpaceView(mContext, 1, 1);
                    wrapper.addView(text);
                    wrapper.addView(space);
                    wrapper.addView(icon);
                    wrapper.setTag(menuItem.getType().getTag());
                    mMenuContainer.addView(wrapper);
                    break;
                }
                case ICON_BEFORE_TEXT:
                default: {
                    LinearLayout wrapper = ViewSpawner.spawnLinearLayout(mContext);
                    wrapper.setOrientation(LinearLayout.HORIZONTAL);
                    View text = ViewSpawner.spawnTextView(mContext, menuItem);
                    View icon = ViewSpawner.spawnImageView(mContext, menuItem);
                    View space = ViewSpawner.spawnSpaceView(mContext, 1, 1);
                    wrapper.addView(icon);
                    wrapper.addView(space);
                    wrapper.addView(text);
                    wrapper.setTag(menuItem.getType().getTag());
                    mMenuContainer.addView(wrapper);
                    break;
                }
            }

            if (i < getMenuItemsCount() - 1) {
                View divider = ViewSpawner.spawnDividerView(mContext, 1, 1);
                divider.setTag(MenuElementType.ELEMENT_DEVIDER.getTag());
                mMenuContainer.addView(divider);
            }
        }
//        helper = new AlphaAnimationModel(mMenuAnimationDirection);

    }

    public void applyTheme(ThemeHelper themeHelper) {
        themeHelper.applyForMenuContainer(mMenuContainer);
        for (int i = 0; i < mMenuContainer.getChildCount(); i += 1) {
            View child = mMenuContainer.getChildAt(i);
            if (child.getTag() == MenuElementType.ELEMENT_DEVIDER.getTag()) {
                themeHelper.applyForDivider(child);
            } else {
                themeHelper.applyForMenuItem((LinearLayout) child);
            }
        }
    }

    @Override
    void attachMenuContainer(FrameLayout parent) {
        mMenuContainer = new LinearLayout(mContext);
        mMenuContainer.setOrientation(
                getMenuOrientation() == MenuOrientation.HORIZONTAL
                        ? LinearLayout.HORIZONTAL
                        : LinearLayout.VERTICAL
        );
        ViewGroup.LayoutParams menuContainerLP = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        mMenuContainer.setLayoutParams(menuContainerLP);
        parent.addView(mMenuContainer);
    }

    @Override
    protected void initialiseViewProperties(AnimationModel animationHelper) {
        animationHelper.initManuContainer(mMenuContainer);
        for (int i = 0; i < mMenuContainer.getChildCount(); i += 1) {
            View view = mMenuContainer.getChildAt(i);
            if (isAnimatable(view)) {
                animationHelper.initMenuItem(view);
            }
        }
    }

    @Override
    protected void buildAnimatorSet(AnimationModel animationModel) {
        AnimatorSet openAnimSet = buildAnimatorSet(animationModel, true);
        openAnimSet.setStartDelay(500);
        AnimatorSet closeAnimSet = buildAnimatorSet(animationModel, false);
        super.setOpenAnimatorSet(openAnimSet);
        super.setCloseAnimatorSet(closeAnimSet);
    }

    private AnimatorSet buildAnimatorSet(AnimationModel animationModel, boolean isOpenAnimation) {
        int duration = 300; //super.getAnimationDuration();
//        int animCount = countAnimatables();
        float animationOverlapFactor = 0.8f;
        int delay = (int) ((1f - animationOverlapFactor) * duration);
//        int x = 0;

        List<Animator> al = new ArrayList<>();

        for (int i = 0; i < mMenuContainer.getChildCount(); i += 1) {
            View item = mMenuContainer.getChildAt(i);
            if (isAnimatable(item)) {
                al.addAll(
                        isOpenAnimation
                                ? animationModel.getMenuItemOpenAnimations(item, duration, delay * (i+1))
                                : animationModel.getMenuItemCloseAnimations(item, duration, delay * (i+1))
                );
            }
        }

        al.addAll(
                isOpenAnimation
                        ? animationModel.getBgOverlayOpenAnimations(super.getRootContainer(), duration, 0)
                        : animationModel.getBgOverlayCloseAnimations(super.getRootContainer(), duration, delay * (mMenuContainer.getChildCount()))
        );

        al.addAll(
                isOpenAnimation
                        ? animationModel.getMenuContainerOpenAnimations(mMenuContainer, duration, 0)
                        : animationModel.getMenuContainerCloseAnimations(mMenuContainer, duration, delay * (mMenuContainer.getChildCount()))
        );

        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(al);
//        animSet.setDuration(10000);

        return animSet;
    }

    private boolean isAnimatable(View view) {
        String tag = (String) view.getTag();
        return tag != null && (tag == MenuItemType.TEXT_ONLY.getTag()
                || tag == MenuItemType.ICON_ONLY.getTag()
                || tag == MenuItemType.ICON_BEFORE_TEXT.getTag()
                || tag == MenuItemType.ICON_AFTER_TEXT.getTag());
    }

    private int countAnimatables() {
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
