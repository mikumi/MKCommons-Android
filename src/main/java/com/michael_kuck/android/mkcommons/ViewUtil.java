/*
 * *
 *  * ViewUtil.java
 *  * as part of mkcommons-android
 *  *
 *  * Created by michaelkuck, last updated on 7/24/14 9:02 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.android.mkcommons;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by michaelkuck on 7/24/14.
 */
public class ViewUtil {

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * Generate a value suitable for use in setId(int).
     * This value will not collide with ID values generated at build time by aapt for R.id.
     * <p/>
     * Note: This method is copied over from later API 17 View.java
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    /**
     * Get the absolute screen position of the top left corner of the view in x,y coordinates.
     *
     * @param view
     * @return
     */
    public static Point getAbsoluteOrigin(final View view) {
        final int origin[] = new int[2];
        view.getLocationOnScreen(origin);
        final Point originAsPoint = new Point(origin[0], origin[1]);
        return originAsPoint;
    }

    /**
     * View parent holder for inflation, required for rendering some layouts (e.g. RelativeLayouts) before rendering
     * with @see renderToTDrawable
     * @return
     */
    public static LinearLayout parentHolderForRendering() {

        final LinearLayout viewHolder = new LinearLayout(Android.getApplication().getApplicationContext());
        viewHolder.setOrientation(LinearLayout.VERTICAL);
        final LinearLayout.LayoutParams holderLayoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                              ViewGroup.LayoutParams.MATCH_PARENT);
        viewHolder.setLayoutParams(holderLayoutParams);
        return viewHolder;
    }

    public static Drawable renderToDrawable(final View view) {
        // Measure view size
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        // Render
        final Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                                                  Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        final Drawable renderedDrawable = new BitmapDrawable(Android.getApplication().getResources(), bitmap);
        return renderedDrawable;
    }

}
