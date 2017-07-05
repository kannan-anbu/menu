package com.kannan.ornate;

import android.content.Context;

import java.util.List;

/**
 * Created by kannan on 3/7/17.
 */

public class MenuBuilder {

    public List<MenuItem> mi = null;

    public MenuGravity gravity = MenuGravity.CENTER;

    public MenuOrientation orientation = MenuOrientation.VERTICAL;

    public Theme theme = null;

    public AbstractMenuSystem menuSystem;

    public Context ctx;


    public MenuBuilder(Context c) {
        ctx = c;
    }


    public MenuDialogFragment build() {
        MenuDialogFragment frag = new MenuDialogFragment();
        menuSystem.setMenuItems(mi);
        menuSystem.setMenuGravity(gravity);
        menuSystem.setMenuOrientation(orientation);
        frag.setMenuSystem(menuSystem);

        return frag;

    }
}
