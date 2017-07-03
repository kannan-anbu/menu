package com.kannan.lib;

/**
 * Created by kannan on 29/6/17.
 */

public enum MenuGravity {

    CENTER(0),


    TOP_LEFT(1),


    TOP_CENTER(2),


    TOP_RIGHT(3),


    RIGHT_CENTER(4),


    BOTTOM_RIGHT(5),


    BOTTOM_CENTER(6),


    BOTTOM_LEFT(7),

    LEFT_CENTER(8);

    int mId;

    MenuGravity(int id) {
        mId = id;
    }

}
