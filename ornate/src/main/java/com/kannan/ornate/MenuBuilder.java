package com.kannan.ornate;

import android.content.Context;

import com.kannan.ornate.menu.AbstractMenuSystem;
import com.kannan.ornate.menu.ListMenuSystem;
import com.kannan.ornate.menu.MenuDialogFragment;
import com.kannan.ornate.theme.Theme;

import java.util.List;

/**
 * Created by kannan on 3/7/17.
 */

public class MenuBuilder {

    public List<MenuItem> mi = null;

    public MenuPosition gravity = MenuPosition.CENTER;

    public MenuOrientation orientation = MenuOrientation.VERTICAL;

    public Theme theme = null;

    public AbstractMenuSystem menuSystem;

    private int[] ScreenMargin = new int[4];

    private int[] padding = new int[4];

    public Context ctx;


    public MenuBuilder(Context c) {
        ctx = c;
    }


    public MenuDialogFragment build() {
        MenuDialogFragment frag = new MenuDialogFragment();
        menuSystem = new ListMenuSystem(ctx, mi, gravity, orientation);
//        menuSystem.setMenuItems(mi);
//        menuSystem.setMenuGravity(gravity);
//        menuSystem.setMenuOrientation(orientation);
        menuSystem.setTheme(theme);
        menuSystem.setMenuMargin(0, 100, 0, 0);
        menuSystem.setMenuPadding(10, 10, 10, 10);
        frag.setMenuSystem(menuSystem);

        return frag;

    }
}
