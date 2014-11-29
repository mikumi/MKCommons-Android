/*
 * *
 *  * LayoutUtil.java
 *  * as part of mkcommons-android
 *  *
 *  * Created by michaelkuck, last updated on 7/25/14 1:00 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.android.mkcommons;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.TypedValue;

/**
 * Created by michaelkuck on 7/25/14.
 */
public class LayoutUtil {

    public static float pixelFromDp(final float dpPixel) {
        final Resources resources = Android.getApplication().getResources();
        final float pixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpPixel, resources.getDisplayMetrics());
        return pixel;
    }

    public static int pixelFromDp(final int dpPixel) {
        return (int) pixelFromDp((float)dpPixel);
    }

    public static PointF pixelsFromDpPoint(final PointF dpPixels) {
        final PointF pixels = new PointF(pixelFromDp(dpPixels.x), pixelFromDp(dpPixels.y));
        return pixels;
    }

    public static Point pixelsFromDpPoint(final Point dpPixels) {
        final Point pixels = new Point(pixelFromDp(dpPixels.x), pixelFromDp(dpPixels.y));
        return pixels;
    }

}
