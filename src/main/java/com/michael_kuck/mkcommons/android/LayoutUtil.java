/*
 * *
 *  * LayoutHelper.java
 *  * as part of whereisthat-android
 *  *
 *  * Created by michaelkuck, last updated on 7/25/14 1:00 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.mkcommons.android;

import android.content.Context;
import android.graphics.Point;
import android.util.TypedValue;

/**
 * Created by michaelkuck on 7/25/14.
 */
public class LayoutUtil {

    public static Point dpToP(final Context context, final Point point) {
        int x = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, point.x, context.getResources().getDisplayMetrics());
        int y = (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, point.y, context.getResources().getDisplayMetrics());
        return new Point(x, y);
    }

    public static int dpToP(final Context context, final int pixel) {
        return (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixel, context.getResources().getDisplayMetrics());
    }

}
