/*
 * *
 *  * AnimationUtil.java
 *  * as part of mkcommons-android
 *  *
 *  * Created by michaelkuck, last updated on 7/25/14 3:41 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.android.mkcommons;

import android.animation.Animator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;

/**
 * Created by michaelkuck on 7/25/14.
 */
public class AnimationUtil {

    public static ViewPropertyAnimator translateXAnimator(final View view, final int newX, final int duration,
                                                          final Runnable completionBlock)
    {
        return translateXAnimator(view, newX, duration, view.getVisibility(), completionBlock);
    }

    public static ViewPropertyAnimator translateXAnimator(final View view, final float newX, final int duration,
                                                          final int visibilityOnCompletion,
                                                          final Runnable completionBlock)
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
        return animator;
    }

    public static void setCompletionBlock(final ViewPropertyAnimator animator, final Runnable completionBlock) {
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

    public static void setCompletionBlock(final Animation animation, final Runnable completionBlock) {
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(final Animation animation) {

            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                if (completionBlock != null) {
                    completionBlock.run();
                }
            }

            @Override
            public void onAnimationRepeat(final Animation animation) {

            }
        });
    }

    public static ViewPropertyAnimator translateYAnimator(final View view, final int newY, final int duration,
                                                          final Runnable completionBlock)
    {
        return translateYAnimator(view, newY, duration, view.getVisibility(), completionBlock);
    }

    public static ViewPropertyAnimator translateYAnimator(final View view, final float newY, final int duration,
                                                          final int visibilityOnCompletion,
                                                          final Runnable completionBlock)
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
        return animator;
    }

}
