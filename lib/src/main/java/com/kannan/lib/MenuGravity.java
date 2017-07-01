package com.kannan.lib;

/**
 * Created by kannan on 29/6/17.
 */

public enum MenuGravity {

    CENTER(0),


    TOP_LEFT(2),


    TOP_CENTER(4),


    TOP_RIGHT(6),


    RIGHT_CENTER(8),


    BOTTOM_RIGHT(10),


    BOTTOM_CENTER(12),


    BOTTOM_LEFT(14),

    LEFT_CENTER(16);

    int mId;

    MenuGravity(int id) {
        mId = id;
    }
}
