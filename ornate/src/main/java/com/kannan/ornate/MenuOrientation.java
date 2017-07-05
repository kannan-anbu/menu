package com.kannan.ornate;

import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by kannan on 1/7/17.
 */

public enum MenuOrientation {

    VERTICAL(0) {
        @Override
        LinearLayout.LayoutParams getLinearLayoutParams() {
            return new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }
    },

    HORIZONTAL(1) {
        @Override
        LinearLayout.LayoutParams getLinearLayoutParams() {
            return new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
        }
    };

    int mId;

    MenuOrientation(int id) {
        mId = id;
    }

    abstract LinearLayout.LayoutParams getLinearLayoutParams();
}
