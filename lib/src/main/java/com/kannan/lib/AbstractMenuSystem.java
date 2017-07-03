package com.kannan.lib;

import android.content.Context;
import android.graphics.Color;
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

    protected MenuGravity mMenuGravity = MenuGravity.CENTER;
    protected MenuOrientation mMenuOrientation = MenuOrientation.VERTICAL;
    protected MenuAnimationDirection mMenuAnimationDirection = MenuAnimationDirection.LEFT_TO_RIGHT;
    protected MenuStretchMode mMenuStretchMode = MenuStretchMode.WRAP_CONTENT;

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
        mMenuContainer.setBackgroundColor(Color.GRAY);
        createMenuViews();
        initialiseViewProperties();
        buildAnimatorSet();
    }


    protected void setupLayouts() {

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

//        if (mMenuStretchMode == MenuStretchMode.MATCH_PARENT) {
//            if (mMenuGravity == MenuGravity.TOP_CENTER
//                    || mMenuGravity == MenuGravity.BOTTOM_CENTER
//                    || mMenuGravity == MenuGravity.CENTER) {
//                width = LinearLayout.LayoutParams.MATCH_PARENT;
//            }
//        } else {
//            if (mMenuGravity == MenuGravity.RIGHT_CENTER
//                    || mMenuGravity == MenuGravity.LEFT_CENTER
//                    || mMenuGravity == MenuGravity.CENTER) {
//                height = LinearLayout.LayoutParams.MATCH_PARENT;
//            }
//        }

        mMenuContainer = createMenuContainer(mMenuOrientation);
        ViewGroup.LayoutParams menuContainerLP = new ViewGroup.LayoutParams(
                width, height
        );

        mScrollView = createScrollView(mMenuOrientation);
        RelativeLayout.LayoutParams scrollViewLP = new RelativeLayout.LayoutParams(
                width, height
        );

        switch (mMenuGravity) {
            case CENTER:
                scrollViewLP.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                break;

            case TOP_LEFT:
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                break;

            case TOP_CENTER:
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                break;

            case TOP_RIGHT:
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                break;

            case RIGHT_CENTER:
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                break;

            case BOTTOM_RIGHT:
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                break;

            case BOTTOM_CENTER:
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                break;

            case BOTTOM_LEFT:
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                break;

            case LEFT_CENTER:
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                break;
        }

        mScrollView.setLayoutParams(scrollViewLP);
        mMenuContainer.setLayoutParams(menuContainerLP);

        mScrollView.addView(mMenuContainer);
        mRootContainer.addView(mScrollView);
    }

    private LinearLayout createMenuContainer(MenuOrientation orientation) {
        LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(
                orientation == MenuOrientation.HORIZONTAL
                        ? LinearLayout.HORIZONTAL
                        : LinearLayout.VERTICAL
        );
        return layout;
    }

    private FrameLayout createScrollView(MenuOrientation orientation) {
        return orientation == MenuOrientation.HORIZONTAL
                ? new HorizontalScrollView(mContext)
                : new ScrollView(mContext);
    }

    abstract void createMenuViews();

    abstract void initialiseViewProperties();

    abstract void buildAnimatorSet();

    public void toggleMenu() {
//        if (!mIsAnimating) {
//            change mIsAnimating
//            if (mIsMenuOpen) {
        initialiseViewProperties();
                mOpenAnimatorSet.start();
//            } else {
//                mCloseAnimatorSet.start();
//            }
//            mIsMenuOpen = !mIsMenuOpen;
//        }
    }

    public void setMenuGravity(MenuGravity gravity) {
        mMenuGravity = gravity;
    }

    public void setMenuAnimationDirection(MenuAnimationDirection direction) {
        mMenuAnimationDirection = direction;
    }

    public void setMenuOrientation(MenuOrientation orientation) {
        mMenuOrientation = orientation;
    }

    public void setDividerSpacing(int spacing) {
        mDividerSpacing = spacing;
    }

    public int getMenuItemsCount() {
        return mMenuItems.size();
    }

}
