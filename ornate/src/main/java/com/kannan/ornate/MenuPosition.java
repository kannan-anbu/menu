package com.kannan.ornate;

/**
 * Created by kannan on 29/6/17.
 */

public enum MenuPosition {

    CENTER(0), // full screen ?

    TOP_LEFT(1),

    TOP_CENTER(2),

    TOP_RIGHT(3),

    RIGHT_CENTER(4),

    BOTTOM_RIGHT(5),

    BOTTOM_CENTER(6),

    BOTTOM_LEFT(7),

    LEFT_CENTER(8),

    LEFT_STRTCHED(9),

    TOP_STRETCHED(10),

    RIGHT_STRETCHED(11),

    BOTTOM_STRETCHED(12);

    int mId;

    MenuPosition(int id) {
        mId = id;
    }

}
