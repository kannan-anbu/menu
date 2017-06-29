package com.kannan.lib;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.nineoldandroids.animation.AnimatorSet;

import java.util.List;

/**
 * Created by kannan on 28/6/17.
 */

public abstract class AbstractMenuSystem {


    protected Context mContext;

    protected MenuGravity mMenuGravity = MenuGravity.CENTER_VERTICAL;

    protected List<MenuItem> mMenuItems;
    private RelativeLayout mRootContainer;
    private FrameLayout mScrollView;
    protected LinearLayout mMenuContainer;

    protected int mDividerSpacing;

    protected AnimatorSet mOpenAnimatorSet;
    protected AnimatorSet mCloseAnimatorSet;

    protected boolean mIsMenuOpen;
    protected boolean mIsAnimating;

    AbstractMenuSystem(Context context, RelativeLayout rootContainer, List<MenuItem> menuItems) {
        mContext = context;
        mRootContainer = rootContainer;
        mMenuItems = menuItems;
        mOpenAnimatorSet = new AnimatorSet();
        mCloseAnimatorSet = new AnimatorSet();
    }

    public void build() {
        setupLayouts();
        createMenuViews();
        initialiseViewProperties();
        buildAnimatorSet();
    }


    protected void setupLayouts() {

        mMenuContainer = new LinearLayout(mContext);
        ViewGroup.LayoutParams menuContainerLP = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        RelativeLayout.LayoutParams scrollViewLP = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        switch (mMenuGravity) {
            case CENTER_HORIZONTAL:
                mScrollView = new HorizontalScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.HORIZONTAL);
                break;

            case CENTER_VERTICAL:
                mScrollView = new ScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.VERTICAL);
                break;

            case TOP_LEFT_HORIZONTAL:
                mScrollView = new HorizontalScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.HORIZONTAL);
                break;

            case TOP_LEFT_VERTICAL:
                mScrollView = new ScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.VERTICAL);
                break;

            case TOP_CENTER_HORIZONTAL:
                mScrollView = new HorizontalScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.HORIZONTAL);
                break;

            case TOP_CENTER_VERTICAL:
                mScrollView = new ScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.VERTICAL);
                break;

            case TOP_RIGHT_HORIZONTAL:
                mScrollView = new HorizontalScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.HORIZONTAL);
                break;

            case TOP_RIGHT_VERTICAL:
                mScrollView = new ScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.VERTICAL);
                break;

            case RIGHT_CENTER_HORIZONTAL:
                mScrollView = new HorizontalScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.HORIZONTAL);
                break;

            case RIGHT_CENTER_VERTICAL:
                mScrollView = new ScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.VERTICAL);

                break;

            case BOTTOM_RIGHT_HORIZONTAL:
                mScrollView = new HorizontalScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.HORIZONTAL);
                break;

            case BOTTOM_RIGHT_VERTICAL:
                mScrollView = new ScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.HORIZONTAL);
                break;

            case BOTTOM_CENTER_HORIZONTAL:
                mScrollView = new HorizontalScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.HORIZONTAL);
                break;

            case BOTTOM_CENTER_VERTICAL:
                mScrollView = new ScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.VERTICAL);
                break;

            case BOTTOM_LEFT_HORIZONTAL:
                mScrollView = new HorizontalScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.HORIZONTAL);
                break;

            case BOTTOM_LEFT_VERTICAL:
                mScrollView = new ScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.VERTICAL);
                break;

            case LEFT_CENTER_HORIZONTAL:
                mScrollView = new HorizontalScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.HORIZONTAL);
                break;

            case LEFT_CENTER_VERTICAL:
                mScrollView = new ScrollView(mContext);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                mMenuContainer.setOrientation(LinearLayout.VERTICAL);
                break;
        }

        mScrollView.setLayoutParams(scrollViewLP);
        mMenuContainer.setLayoutParams(menuContainerLP);

        mScrollView.addView(mMenuContainer);
        mRootContainer.addView(mScrollView);
    }

    abstract void createMenuViews();

    abstract void initialiseViewProperties();

    abstract void buildAnimatorSet();

    public void toggleMenu() {
        if (!mIsAnimating) {
            //change mIsAnimating
            if (mIsMenuOpen) {
                mOpenAnimatorSet.start();
            } else {
                mCloseAnimatorSet.start();
            }
            mIsMenuOpen = !mIsMenuOpen;
        }
    }

    public void setMenuGravity(MenuGravity gravity) {
        mMenuGravity = gravity;
    }

    public void setDividerSpacing(int spacing) {
        mDividerSpacing = spacing;
    }

    public int getMenuItemsCount() {
        return mMenuItems.size();
    }

}
