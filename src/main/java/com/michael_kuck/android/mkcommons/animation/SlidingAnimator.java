/*
 * *
 *  * This file is part of whereisthat-android
 *  *
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.android.mkcommons.animation;

import android.animation.Animator;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.View;
import android.view.ViewGroup;
import com.michael_kuck.android.mkcommons.SystemUtil;
import com.michael_kuck.android.mkcommons.ViewUtil;

public class SlidingAnimator {

    public enum ViewLocation {
        TOP_LEFT, TOP, TOP_RIGHT, RIGHT, BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT, LEFT, ORIGIN
    }

    //======================================================================
    //= Public methods
    //======================================================================

    public static void slide(final View view, final ViewLocation destination, final ViewGroup parentView,
                             final int durationInMillis, final Runnable completion)
    {
        final PointF translation = getTranslationFor(view, destination, parentView);
        view.animate().translationX(translation.x).translationY(translation.y).setDuration(durationInMillis)
            .setListener(new AnimatorCompletionListener() {
                @Override
                public void onAnimationEnd(final Animator animation) {
                    if (completion != null) {
                        completion.run();
                    }
                }
            }).start();
    }

    public static void resetTo(final View view, final ViewLocation destination, final ViewGroup parentView) {
        final PointF translation = getTranslationFor(view, destination, parentView);
        view.setTranslationX(translation.x);
        view.setTranslationY(translation.y);
    }

    //======================================================================
    //= Private methods
    //======================================================================

    private static PointF getTranslationFor(final View view, final ViewLocation destination,
                                            final ViewGroup parentView)
    {
        final float translateX;
        final float translateY;
        switch (destination) {
            case ORIGIN:
                translateX = 0.0f;
                translateY = 0.0f;
                break;
            case TOP_LEFT:
                if (parentView != null) {
                    translateX = -view.getLeft() - view.getWidth();
                    translateY = -view.getTop() - view.getHeight();
                } else {
                    final Point origin = ViewUtil.getAbsoluteOrigin(view);
                    translateX = -origin.x - view.getWidth();
                    translateY = -origin.y - view.getHeight();
                }
                break;
            case TOP:
                if (parentView != null) {
                    translateX = view.getTranslationX();
                    translateY = -view.getTop() - view.getHeight();
                } else {
                    final Point origin = ViewUtil.getAbsoluteOrigin(view);
                    translateX = view.getTranslationX();
                    translateY = -origin.y - view.getHeight();
                }
                break;
            case TOP_RIGHT:
                if (parentView != null) {
                    translateX = parentView.getWidth() - view.getLeft();
                    translateY = -view.getTop() - view.getHeight();
                } else {
                    final Point screenSize = SystemUtil.getScreenSize();
                    final Point origin = ViewUtil.getAbsoluteOrigin(view);
                    translateX = screenSize.x - origin.x;
                    translateY = -origin.y - view.getHeight();
                }
                break;
            case RIGHT:
                if (parentView != null) {
                    translateX = parentView.getWidth() - view.getLeft();
                    translateY = view.getTranslationY();
                } else {
                    final Point screenSize = SystemUtil.getScreenSize();
                    final Point origin = ViewUtil.getAbsoluteOrigin(view);
                    translateX = screenSize.x - origin.x;
                    translateY = view.getTranslationY();
                }
                break;
            case BOTTOM_RIGHT:
                if (parentView != null) {
                    translateX = parentView.getWidth() - view.getLeft();
                    translateY = parentView.getHeight() - view.getTop();
                } else {
                    final Point screenSize = SystemUtil.getScreenSize();
                    final Point origin = ViewUtil.getAbsoluteOrigin(view);
                    translateX = screenSize.x - origin.x;
                    translateY = screenSize.y - origin.y;
                }
                break;
            case BOTTOM:
                if (parentView != null) {
                    translateX = view.getTranslationX();
                    translateY = parentView.getHeight() - view.getTop();
                } else {
                    final Point screenSize = SystemUtil.getScreenSize();
                    final Point origin = ViewUtil.getAbsoluteOrigin(view);
                    translateX = view.getTranslationX();
                    translateY = screenSize.y - origin.y;
                }
                break;
            case BOTTOM_LEFT:
                if (parentView != null) {
                    translateX = -view.getLeft() - view.getWidth();
                    translateY = parentView.getHeight() - view.getTop();
                } else {
                    final Point screenSize = SystemUtil.getScreenSize();
                    final Point origin = ViewUtil.getAbsoluteOrigin(view);
                    translateX = -origin.x - view.getWidth();
                    translateY = screenSize.y - origin.y;
                }
                break;
            case LEFT:
                if (parentView != null) {
                    translateX = -view.getLeft() - view.getWidth();
                    translateY = view.getTranslationY();
                } else {
                    final Point origin = ViewUtil.getAbsoluteOrigin(view);
                    translateX = -origin.x - view.getWidth();
                    translateY = view.getTranslationY();
                }
                break;
            default:
                translateX = view.getTranslationX();
                translateY = view.getTranslationY();
        }
        return new PointF(translateX, translateY);
    }

}
