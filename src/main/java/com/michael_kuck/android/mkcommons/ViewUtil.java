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

import android.graphics.Point;
import android.view.View;

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

}
