package com.kannan.ornate;

/**
 * Created by kannan on 4/7/17.
 */

public enum MenuItemType {

    // CHANGE TAGS

    ICON_ONLY(0) {
        @Override
        String getTag() {
            return "item_icon";
        }
    },

    TEXT_ONLY(1) {
        @Override
        String getTag() {
            return "item_text";
        }
    },

    ICON_BEFORE_TEXT(2) {
        @Override
        String getTag() {
            return "item_icon_text";
        }
    },

    ICON_AFTER_TEXT(3) {
        @Override
        String getTag() {
            return "item_text_icon";
        }
    },

    ICON_ABOVE_TEXT(4) {
        @Override
        String getTag() {
            return "item_icon_above_text";
        }
    },

    ICON_BELOW_TEXT(5) {
        @Override
        String getTag() {
            return "item_icon_below_text";
        }
    };

    int mId;

    MenuItemType(int id) {
        mId = id;
    }

    abstract String getTag();
}
