/*
 * *
 *  * This file is part of mkcommons-android
 *  *
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.mkcommons.android;

import android.graphics.Point;
import android.view.View;
import android.view.ViewPropertyAnimator;
import com.michael_kuck.mkcommons.android.support.ViewUtil;

/**
 * Created by michaelkuck on 8/5/14.
 */
public class SlideInOutAnimator {

    public static ViewPropertyAnimator slideOutToTopAnimator(final View view, final int duration,
                                                             final Runnable completionBlock)
    {
        final int hiddenY = getYForHiddenToTop(view);
        final ViewPropertyAnimator animator =
                AnimationUtil.translateYAnimator(view, hiddenY, duration, completionBlock);
        return animator;
    }

    private static int getYForHiddenToTop(final View view) {
        final Point origin = ViewUtil.getOrigin(view);
        final int height = view.getHeight();
        return -origin.y - height;
    }

    public static ViewPropertyAnimator slideOutToBottomAnimator(final View view, final int duration,
                                                                final Runnable completionBlock)
    {
        final int newY = getYForHiddenToBottom(view);
        final ViewPropertyAnimator animator = AnimationUtil.translateYAnimator(view, newY, duration, completionBlock);
        return animator;
    }

    private static int getYForHiddenToBottom(final View view) {
        final Point screenSize = SystemUtil.getScreenSize();
        final Point origin = ViewUtil.getOrigin(view);
        return screenSize.y - origin.y;
    }

    public static ViewPropertyAnimator slideOutToLeftAnimator(final View view, final int duration,
                                                              final Runnable completionBlock)
    {
        final int hiddenX = getXForHiddenToLeft(view);
        final ViewPropertyAnimator animator =
                AnimationUtil.translateXAnimator(view, hiddenX, duration, completionBlock);
        return animator;
    }

    private static int getXForHiddenToLeft(final View view) {
        final Point origin = ViewUtil.getOrigin(view);
        final int width = view.getWidth();
        return -origin.x - width;
    }

    public static ViewPropertyAnimator slideBackAnimator(final View view, final int duration,
                                                         final Runnable completionBlock)
    {
        final ViewPropertyAnimator animator = view.animate().translationX(0).translationY(0).setDuration(duration);
        AnimationUtil.setCompletionBlock(animator, completionBlock);
        return animator;
    }

    public static ViewPropertyAnimator slideOutToRightAnimator(final View view, final int duration,
                                                               final Runnable completionBlock)
    {
        final int hiddenX = getXForHiddenToRight(view);
        final ViewPropertyAnimator animator =
                AnimationUtil.translateXAnimator(view, hiddenX, duration, completionBlock);
        return animator;
    }

    private static int getXForHiddenToRight(final View view) {
        final Point screenSize = SystemUtil.getScreenSize();
        final Point origin = ViewUtil.getOrigin(view);
        return screenSize.x - origin.x;
    }

    public static void setStartingPositionTop(final View view) {
        view.setTranslationX(0.0f);
        final int hiddenY = getYForHiddenToTop(view);
        view.setTranslationY(hiddenY);
    }

    public static void setStartingPositionBottom(final View view) {
        view.setTranslationX(0.0f);
        final int hiddenY = getYForHiddenToBottom(view);
        view.setTranslationY(hiddenY);
    }

    public static void setStartingPositionLeft(final View view) {
        final int hiddenX = getXForHiddenToLeft(view);
        view.setTranslationX(hiddenX);
        view.setTranslationY(0.0f);
    }

    public static void setStartingPositionRight(final View view) {
        final int hiddenX = getXForHiddenToRight(view);
        view.setTranslationX(hiddenX);
        view.setTranslationY(0.0f);
    }

}
