package com.kannan.ornate;

import android.content.Context;
import android.support.v4.widget.Space;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by kannan on 28/6/17.
 */

public class ViewSpawner {

//    public static TextView spawnTextView(Context context, MenuItem menuItem) {
//        TextView textView = new TextView(context);
////        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
////                ViewGroup.LayoutParams.WRAP_CONTENT,
////                ViewGroup.LayoutParams.WRAP_CONTENT
////        );
////        lp.gravity = Gravity.CENTER;
////        textView.setLayoutParams(lp);
////        textView.setPadding(padding, padding, padding, padding);
//        textView.setText(menuItem.getText());
////        textView.setTextColor(textMenuItem.getTextColor());
//
//        return textView;
//    }
//
//    public static View spawnWrappedTextView(Context context, MenuItem menuItem, ViewGroup.LayoutParams layoutParams, int orientation) {
//        LinearLayout wrapper = spawnLinearLayout(context, layoutParams, orientation);
//        wrapper.addView(spawnTextView(context, menuItem));
//
//        return wrapper;
//    }
//
//    public static View spawnSpaceView(Context context, int width, int height) {
//        Space space = new Space(context);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                width > -1 ? width : ViewGroup.LayoutParams.MATCH_PARENT,
//                height > -1 ? height : ViewGroup.LayoutParams.MATCH_PARENT
//        );
//        space.setLayoutParams(lp);
//
//        return space;
//    }
//
//    public static ImageView spawnImageView(Context context, MenuItem menuItem) {
//        ImageView imageView = new ImageView(context);
////        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
////                ViewGroup.LayoutParams.WRAP_CONTENT,
////                ViewGroup.LayoutParams.WRAP_CONTENT
////        );
////        lp.gravity = Gravity.CENTER;
////        imageView.setLayoutParams(lp);
////        imageView.setPadding(padding, padding, padding,padding);
////        if (menuItem.getImgBitmap() != null) {
////            imageView.setImageBitmap(imageMenuItem.getImgBitmap());
////        } else if (menuItem.getImgDrawable() != null) {
////            imageView.setImageDrawable(imageMenuItem.getImgDrawable());
//         if (menuItem.getImageRes() != -1) {
//            imageView.setImageResource(menuItem.getImageRes());
//        }
////        imageView.setScaleType(imageMenuItem.getImgScaletype());
//
//        return imageView;
//    }
//
//    public static View spawnWrappedImageView(Context context, MenuItem menuItem, ViewGroup.LayoutParams layoutParams, int orientation) {
//        LinearLayout wrapper = spawnLinearLayout(context, layoutParams, orientation);
//        wrapper.addView(spawnImageView(context, menuItem));
//
//        return wrapper;
//    }
//
//    public static LinearLayout spawnLinearLayout(Context context, ViewGroup.LayoutParams layoutParams, int orientation) {
//        LinearLayout layout = new LinearLayout(context);
//        layout.setLayoutParams(layoutParams);
//        layout.setOrientation(orientation);
//
//        return layout;
//    }
//
//    public static View spawnImageTextView(Context context, MenuItem menuItem, ViewGroup.LayoutParams layoutParams, int orientation) {
//        LinearLayout wrapper = spawnLinearLayout(context, layoutParams, orientation);
//
//        ImageView imageView = spawnImageView(context, menuItem);
//        TextView textView = spawnTextView(context, menuItem);
//
////        textView.setPadding(
////                20 + textView.getPaddingLeft(),
////                textView.getPaddingTop(),
////                textView.getPaddingRight(),
////                textView.getPaddingBottom()
////        );
////        ((LinearLayout.LayoutParams) textView.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
////        ((LinearLayout.LayoutParams) imageView.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
//        wrapper.addView(imageView);
//        wrapper.addView(textView);
//
//        return wrapper;
//
//    }






//    ================================================================================



    public static View spawnSpaceView(Context context, int width, int height) {
        Space space = new Space(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                width > -1 ? width : ViewGroup.LayoutParams.MATCH_PARENT,
                height > -1 ? height : ViewGroup.LayoutParams.MATCH_PARENT
        );
        space.setLayoutParams(lp);

        return space;
    }


    public static TextView spawnTextView(Context context, MenuItem menuItem) {
        TextView textView = new TextView(context);
        textView.setText(menuItem.getText());

        return textView;
    }

    public static ImageView spawnImageView(Context context, MenuItem menuItem) {
        ImageView imageView = new ImageView(context);

        if (menuItem.getImageRes() != -1) {
            imageView.setImageResource(menuItem.getImageRes());
        }

        return imageView;
    }

    public static View spawnWrappedTextView(Context context, MenuItem menuItem) {
        LinearLayout wrapper = spawnLinearLayout(context);
        wrapper.addView(spawnTextView(context, menuItem));

        return wrapper;
    }

    public static View spawnWrappedImageView(Context context, MenuItem menuItem) {
        LinearLayout wrapper = spawnLinearLayout(context);
        wrapper.addView(spawnImageView(context, menuItem));

        return wrapper;
    }

    public static LinearLayout spawnLinearLayout(Context context) {
        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layout.setLayoutParams(layoutParams);

        return layout;
    }

//    public static View spawnImageTextView(Context context, MenuItem menuItem, ViewGroup.LayoutParams layoutParams, int orientation) {
//        LinearLayout wrapper = spawnLinearLayout(context, layoutParams, orientation);
//
//        ImageView imageView = spawnImageView(context, menuItem);
//        TextView textView = spawnTextView(context, menuItem);
//
////        textView.setPadding(
////                20 + textView.getPaddingLeft(),
////                textView.getPaddingTop(),
////                textView.getPaddingRight(),
////                textView.getPaddingBottom()
////        );
////        ((LinearLayout.LayoutParams) textView.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
////        ((LinearLayout.LayoutParams) imageView.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
//        wrapper.addView(imageView);
//        wrapper.addView(textView);
//
//        return wrapper;
//
//    }
}
