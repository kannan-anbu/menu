package com.kannan.ornate;

/**
 * Created by kannan on 4/7/17.
 */

public enum MenuElementType {

    ELEMENT_TEXT(0),

    ELEMENT_ICON(1),

    ELEMENT_SPACER(2),

    ELEMENT_DEVIDER(3);

    int mId;

    MenuElementType(int id) {
        mId = id;
    }
}
