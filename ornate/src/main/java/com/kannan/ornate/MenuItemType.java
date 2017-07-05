package com.kannan.ornate;

/**
 * Created by kannan on 4/7/17.
 */

public enum MenuItemType {

    ITEM_ICON(0) {
        @Override
        String getTag() {
            return "item_icon";
        }
    },

    ITEM_TEXT(1) {
        @Override
        String getTag() {
            return "item_text";
        }
    },

    ITEM_ICON_TEXT(2) {
        @Override
        String getTag() {
            return "item_icon_text";
        }
    },

    ITEM_TEXT_ICON(3) {
        @Override
        String getTag() {
            return "item_text_icon";
        }
    };

    int mId;

    MenuItemType(int id) {
        mId = id;
    }

    abstract String getTag();
}
