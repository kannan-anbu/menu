package com.kannan.ornate;

/**
 * Created by kannan on 4/7/17.
 */

public enum MenuElementType {

    ELEMENT_TEXT(0) {
        @Override
        public String getTag() {
            return "element_text";
        }
    },

    ELEMENT_ICON(1) {
        @Override
        public String getTag() {
            return "element_icon";
        }
    },

    ELEMENT_SPACE(2) {
        @Override
        public String getTag() {
            return "element_space";
        }
    },

    ELEMENT_DEVIDER(3) {
        @Override
        public String getTag() {
            return "element_devider";
        }
    };

    int mId;

    MenuElementType(int id) {
        mId = id;
    }

    abstract public String getTag();
}
