package com.kannan.lib;

/**
 * Created by kannan on 1/7/17.
 */

public enum MenuItemStructure {

    TEXT(0) {
        @Override
        String getTag() {
            return "text";
        }
    },

    ICON(1) {
        @Override
        String getTag() {
            return "icon";
        }
    },

    ICON_TEXT(2) {
        @Override
        String getTag() {
            return "icon_text";
        }
    },

    ICON_TEXT_DESCRIPTION(3) {
        @Override
        String getTag() {
            return "icon_text_description";
        }
    };

    int mId;

    MenuItemStructure(int id) {
        mId = id;
    }

    abstract String getTag();
}
