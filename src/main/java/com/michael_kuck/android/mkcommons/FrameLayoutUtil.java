/*
 * *
 *  * FrameLayoutUtil.java
 *  * as part of mkcommons-android
 *  *
 *  * Created by michaelkuck, last updated on 7/24/14 7:36 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.android.mkcommons;

import android.graphics.Point;
import android.widget.FrameLayout;

/**
 * Created by michaelkuck on 7/24/14.
 */
public class FrameLayoutUtil {

    /**
     * @param frameLayout
     * @param origin
     */
    static public void setOrigin(final FrameLayout frameLayout, final Point origin) {
        setOrigin(frameLayout, origin.x, origin.y);
    }

    /**
     * @param frameLayout
     * @param left
     * @param top
     */
    static public void setOrigin(final FrameLayout frameLayout, final int left, final int top) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) frameLayout.getLayoutParams();
        layoutParams.leftMargin = left;
        layoutParams.topMargin = top;
        frameLayout.setLayoutParams(layoutParams);
    }

    /**
     * @param frameLayout
     * @param size
     */
    static public void setSize(final FrameLayout frameLayout, final Point size) {
        setSize(frameLayout, size.x, size.y);
    }

    /**
     * @param frameLayout
     * @param width
     * @param height
     */
    static public void setSize(final FrameLayout frameLayout, final int width, final int height) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) frameLayout.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        frameLayout.setLayoutParams(layoutParams);
    }

    /**
     * @param frameLayout
     * @param origin
     * @param size
     */
    static public void setFrame(final FrameLayout frameLayout, final Point origin, final Point size) {
        setFrame(frameLayout, origin.x, origin.y, size.x, size.y);
    }

    /**
     * @param frameLayout
     * @param left
     * @param top
     * @param width
     * @param height
     */
    static public void setFrame(final FrameLayout frameLayout, final int left, final int top, final int width,
                                final int height)
    {
        setOrigin(frameLayout, left, top);
        setSize(frameLayout, width, height);
    }

}
