package com.kannan.ornate.menu;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.kannan.ornate.widget.MaxHorizontalScrollView;
import com.kannan.ornate.widget.MaxVerticalScrollView;
import com.kannan.ornate.MenuPosition;
import com.kannan.ornate.MenuItem;
import com.kannan.ornate.MenuOrientation;
import com.kannan.ornate.theme.Theme;
import com.kannan.ornate.theme.ThemeHelper;
import com.nineoldandroids.animation.AnimatorSet;

import java.util.List;

/**
 * Created by kannan on 28/6/17.
 */

public abstract class AbstractMenuSystem {

    private static final int SCROLL_CONTAINER_MAX_WIDTH = 600;      // to dp
    private static final int SCROLL_CONTAINER_MAX_HEIGHT = 700;      // to dp


    private Context mContext;

    private MenuPosition mMenuPosition;
    private MenuOrientation mMenuOrientation;
//    protected MenuAnimationDirection mMenuAnimationDirection = MenuAnimationDirection.LEFT_TO_RIGHT;
//    protected MenuStretchMode mMenuStretchMode = MenuStretchMode.WRAP_CONTENT;
    private ThemeHelper mThemeHelper;

    private List<MenuItem> mMenuItems;
    private RelativeLayout mRootContainer;
    private FrameLayout mScrollContainer;

    private Theme.Margin mMenuMargin;
    private Theme.Padding mMenuPadding;

    private AnimatorSet mOpenAnimatorSet;
    private AnimatorSet mCloseAnimatorSet;

    protected boolean mIsMenuOpen;
    protected boolean mIsAnimating;

    AbstractMenuSystem(Context context, List<MenuItem> menuItems,
                       MenuPosition gravity, MenuOrientation orientation) {
        mContext = context;
        mMenuItems = menuItems;
        mMenuPosition = gravity;
        mMenuOrientation = orientation;
        mOpenAnimatorSet = new AnimatorSet();
        mCloseAnimatorSet = new AnimatorSet();
    }

    public void buildUpon(RelativeLayout rootContainer) {
        mRootContainer = rootContainer;
        setupLayouts();
        createMenuViews();
        mThemeHelper.applyForRootContainer(mRootContainer);
        if (mThemeHelper != null) {
            applyTheme(mThemeHelper);
        }
        initialiseViewProperties();
        buildAnimatorSet();
    }


    protected void setupLayouts() {

        if (mMenuMargin != null && mMenuPadding != null) {
//            RelativeLayout.LayoutParams rootLP = (RelativeLayout.LayoutParams) mRootContainer.getLayoutParams();
            RelativeLayout.LayoutParams rootLP = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            rootLP.setMargins(mMenuMargin.left, mMenuMargin.top, mMenuMargin.right, mMenuMargin.bottom);
            mRootContainer.setLayoutParams(rootLP);
            mRootContainer.setPadding(mMenuPadding.left, mMenuPadding.top, mMenuPadding.right, mMenuPadding.bottom);
        }
//        if (mMenuStretchMode == MenuStretchMode.MATCH_PARENT) {
//            if (mMenuPosition == MenuPosition.TOP_CENTER
//                    || mMenuPosition == MenuPosition.BOTTOM_CENTER
//                    || mMenuPosition == MenuPosition.CENTER) {
//                width = LinearLayout.LayoutParams.MATCH_PARENT;
//            }
//        } else {
//            if (mMenuPosition == MenuPosition.RIGHT_CENTER
//                    || mMenuPosition == MenuPosition.LEFT_CENTER
//                    || mMenuPosition == MenuPosition.CENTER) {
//                height = LinearLayout.LayoutParams.MATCH_PARENT;
//            }
//        }

        mScrollContainer = createScrollView(mMenuOrientation);
        RelativeLayout.LayoutParams scrollViewLP = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        boolean stretchWidth = false, stretchHeight = false;
        switch (mMenuPosition) {
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

            case LEFT_STRTCHED:
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                stretchHeight = true;
//                change width, height ?
                break;

            case TOP_STRETCHED:
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                stretchWidth = true;
                break;

            case RIGHT_STRETCHED:
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                stretchHeight = true;
                break;

            case BOTTOM_STRETCHED:
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                scrollViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                stretchWidth = true;
                break;
        }

        if (stretchWidth) {
            if (mScrollContainer instanceof MaxHorizontalScrollView) {
                ((MaxHorizontalScrollView) mScrollContainer).setMaxWidth(MaxHorizontalScrollView.NO_LIMIT);
            } else {
                ((MaxVerticalScrollView) mScrollContainer).setMaxWidth(MaxVerticalScrollView.NO_LIMIT);
            }
        }

        if (stretchHeight) {
            if (mScrollContainer instanceof MaxHorizontalScrollView) {
                ((MaxHorizontalScrollView) mScrollContainer).setMaxHeight(MaxHorizontalScrollView.NO_LIMIT);
            } else {
                ((MaxVerticalScrollView) mScrollContainer).setMaxHeight(MaxVerticalScrollView.NO_LIMIT);
            }
        }

        mScrollContainer.setLayoutParams(scrollViewLP);
        attachMenuContainer(mScrollContainer);
        mRootContainer.addView(mScrollContainer);
    }

    private FrameLayout createScrollView(MenuOrientation orientation) {
        if (orientation == MenuOrientation.HORIZONTAL) {
            MaxHorizontalScrollView mhsv = new MaxHorizontalScrollView(mContext);
            mhsv.setMaxWidth(SCROLL_CONTAINER_MAX_WIDTH);
            mhsv.setMaxHeight(SCROLL_CONTAINER_MAX_HEIGHT);
            return mhsv;
        } else {
            MaxVerticalScrollView mvsv = new MaxVerticalScrollView(mContext);
            mvsv.setMaxWidth(SCROLL_CONTAINER_MAX_WIDTH);
            mvsv.setMaxHeight(SCROLL_CONTAINER_MAX_HEIGHT);
            return mvsv;
        }
    }

    abstract void attachMenuContainer(FrameLayout parent);

    abstract void createMenuViews();

    abstract void applyTheme(ThemeHelper mThemeHelper);

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

    public void setMenuItems(List<MenuItem> menuItems) {
        mMenuItems = menuItems;
    }

    public void setMenuGravity(MenuPosition gravity) {
        mMenuPosition = gravity;
    }

//    public void setMenuAnimationDirection(MenuAnimationDirection direction) {
//        mMenuAnimationDirection = direction;
//    }

    public void setMenuOrientation(MenuOrientation orientation) {
        mMenuOrientation = orientation;
    }

    public void setTheme(Theme theme) {
        mThemeHelper = new ThemeHelper(mContext, theme, mMenuOrientation);
    }

    public void setMenuMargin(int left, int top, int right, int bottom) {
        mMenuMargin = new Theme.Margin(left, top, right, bottom);
    }

    public void setMenuPadding(int left, int top, int right, int bottom) {
        mMenuPadding = new Theme.Padding(left, top, right, bottom);
    }

//    public void setDividerSpacing(int spacing) {
//        mDividerSpacing = spacing;
//    }

    public int getMenuItemsCount() {
        return mMenuItems.size();
    }

    public MenuItem getMenuItem(int i) {
        if (i >= 0 && i < getMenuItemsCount()) {
            return mMenuItems.get(i);
        }
        return null;
    }

    public MenuOrientation getMenuOrientation() {
        return mMenuOrientation;
    }

}
