/*
 * *
 *  * AnimationHelper.java
 *  * as part of whereisthat-android
 *  *
 *  * Created by michaelkuck, last updated on 7/25/14 3:41 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.mkcommons.android;

import android.animation.Animator;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * Created by michaelkuck on 7/25/14.
 */
public class AnimationHelper {

    /**
     * @param view
     * @param newX
     * @param duration
     * @param completionBlock
     */
    public static void translateX(final View view, final int newX, final int duration, final Runnable completionBlock) {
        translateX(view, newX, duration, view.getVisibility(), completionBlock);
    }

    /**
     * @param view
     * @param newX
     * @param duration
     * @param visibilityOnCompletion
     * @param completionBlock
     */
    public static void translateX(final View view, final float newX, final int duration,
                                  final int visibilityOnCompletion, final Runnable completionBlock)
    {
        Runnable onAnimationEnd = new Runnable() {
            @Override
            public void run() {
                view.setVisibility(visibilityOnCompletion);
                if (completionBlock != null) {
                    completionBlock.run();
                }
            }
        };
        ViewPropertyAnimator animator = view.animate().translationX(newX).setDuration(duration);
        setCompletionBlock(animator, onAnimationEnd);
        animator.start();
    }

    /**
     * @param view
     * @param newY
     * @param duration
     * @param completionBlock
     */
    public static void translateY(final View view, final int newY, final int duration, final Runnable completionBlock) {
        translateY(view, newY, duration, view.getVisibility(), completionBlock);
    }

    /**
     * @param view
     * @param newY
     * @param duration
     * @param visibilityOnCompletion
     * @param completionBlock
     */
    public static void translateY(final View view, final float newY, final int duration,
                                  final int visibilityOnCompletion, final Runnable completionBlock)
    {
        Runnable onAnimationEnd = new Runnable() {
            @Override
            public void run() {
                view.setVisibility(visibilityOnCompletion);
                if (completionBlock != null) {
                    completionBlock.run();
                }
            }
        };
        ViewPropertyAnimator animator = view.animate().translationY(newY).setDuration(duration);
        setCompletionBlock(animator, onAnimationEnd);
        animator.start();
    }

    /**
     * @param animator
     * @param completionBlock
     */
    private static void setCompletionBlock(final ViewPropertyAnimator animator, final Runnable completionBlock) {
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(final Animator animation) {

            }

            @Override
            public void onAnimationEnd(final Animator animation) {
                if (completionBlock != null) {
                    completionBlock.run();
                }
            }

            @Override
            public void onAnimationCancel(final Animator animation) {

            }

            @Override
            public void onAnimationRepeat(final Animator animation) {

            }
        });
    }

}
