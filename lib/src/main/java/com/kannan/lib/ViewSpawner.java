package com.kannan.lib;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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

    public static TextView spawnTextView(Context context, TextMenuItem textMenuItem) {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        lp.gravity = Gravity.CENTER;
        textView.setLayoutParams(lp);
//        textView.setPadding(padding, padding, padding, padding);
        textView.setText(textMenuItem.getText());
        textView.setTextColor(textMenuItem.getTextColor());

        return textView;
    }

    public static View spawnWrappedTextView(Context context, TextMenuItem textMenuItem, MenuOrientation orientation) {
        LinearLayout wrapper = new LinearLayout(context);
        wrapper.setLayoutParams(orientation.getLinearLayoutParams());
        wrapper.addView(spawnTextView(context, textMenuItem));

        return wrapper;
    }

    public static View spawnSpaceView(Context context, int width, int height) {
        Space space = new Space(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                width > -1 ? width : ViewGroup.LayoutParams.MATCH_PARENT,
                height > -1 ? height : ViewGroup.LayoutParams.MATCH_PARENT
        );
        space.setLayoutParams(lp);

        return space;
    }

    public static ImageView spawnImageView(Context context, ImageMenuItem imageMenuItem) {
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        lp.gravity = Gravity.CENTER;
        imageView.setLayoutParams(lp);
//        imageView.setPadding(padding, padding, padding,padding);
        if (imageMenuItem.getImgBitmap() != null) {
            imageView.setImageBitmap(imageMenuItem.getImgBitmap());
        } else if (imageMenuItem.getImgDrawable() != null) {
            imageView.setImageDrawable(imageMenuItem.getImgDrawable());
        } else if (imageMenuItem.getImgResource() != -1) {
            imageView.setImageResource(imageMenuItem.getImgResource());
        } else {
            imageView.setBackgroundColor(imageMenuItem.getImgColor());
        }
        imageView.setScaleType(imageMenuItem.getImgScaletype());

        return imageView;
    }

    public static View spawnWrappedImageView(Context context, ImageMenuItem imageMenuItem, MenuOrientation orientation) {
        LinearLayout wrapper = new LinearLayout(context);
        wrapper.setLayoutParams(orientation.getLinearLayoutParams());
        wrapper.addView(spawnImageView(context, imageMenuItem));

        return wrapper;
    }

    public static View spawnImageTextView(Context context, ImageTextMenuItem imageTextMenuItem) {
        LinearLayout container = new LinearLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        container.setLayoutParams(lp);
        container.setOrientation(LinearLayout.HORIZONTAL);
        ImageView imageView = spawnImageView(context, imageTextMenuItem.getImageMenuItem());
        TextView textView = spawnTextView(context, imageTextMenuItem.getTextMenuItem());
        textView.setPadding(
                20 + textView.getPaddingLeft(),
                textView.getPaddingTop(),
                textView.getPaddingRight(),
                textView.getPaddingBottom()
        );
        ((LinearLayout.LayoutParams) textView.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        ((LinearLayout.LayoutParams) imageView.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;
        container.addView(imageView);
        container.addView(textView);

        return container;

    }
}
