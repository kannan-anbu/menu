package com.kannan.lib;

/**
 * Created by kannan on 1/7/17.
 */

public enum MenuAnimationDirection {

    LEFT_TO_RIGHT(0),

    RIGHT_TO_LEFT(1),

    TOP_TO_BOTTOM(2),

    BOTTOM_TO_TOP(3);

    int mId;

    MenuAnimationDirection(int id) {
        mId = id;
    }
}
