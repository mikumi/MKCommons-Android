/*
 * *
 *  * AnimationHelper.java
 *  * as part of whereisthat-android
 *  *
 *  * Created by michaelkuck, last updated on 7/23/14 2:27 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.mkcommons.android.support;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by michaelkuck on 7/23/14.
 */
public class LegacyAnimator {

    /**
     * @param view
     * @param fromXDelta
     * @param toXDelta
     * @param duration
     * @param completionBlock
     */
    public static void translateX(final View view, final int fromXDelta, final int toXDelta, final int duration,
                                  final Runnable completionBlock)
    {
        translateX(view, fromXDelta, toXDelta, duration, view.getVisibility(), completionBlock);

    }

    /**
     * @param view
     * @param fromXDelta
     * @param toXDelta
     * @param duration
     * @param completionBlock
     */
    public static void translateX(final View view, final int fromXDelta, final int toXDelta, final int duration,
                                  final int visibilityOnCompletion, final Runnable completionBlock)
    {
        Runnable onAnimationCompletion = new Runnable() {
            @Override
            public void run() {
                view.setVisibility(visibilityOnCompletion);
                if (completionBlock != null) {
                    completionBlock.run();
                }
            }
        };
        // TODO: stay at current translationX
        TranslateAnimation animation = new TranslateAnimation(fromXDelta, toXDelta, 0, 0);
        custom(view, animation, duration, onAnimationCompletion);

    }

    /**
     * @param view
     * @param fromYDelta
     * @param toYDelta
     * @param duration
     * @param completionBlock
     */
    public static void translateY(final View view, final int fromYDelta, final int toYDelta, final int duration,
                                  final Runnable completionBlock)
    {
        translateY(view, fromYDelta, toYDelta, duration, view.getVisibility(), completionBlock);

    }

    /**
     * @param view
     * @param fromYDelta
     * @param toYDelta
     * @param duration
     * @param completionBlock
     */
    public static void translateY(final View view, final int fromYDelta, final int toYDelta, final int duration,
                                  final int visibilityOnCompletion, final Runnable completionBlock)
    {
        Runnable onAnimationCompletion = new Runnable() {
            @Override
            public void run() {
                view.setVisibility(visibilityOnCompletion);
                if (completionBlock != null) {
                    completionBlock.run();
                }
            }
        };
        // TODO: stay at current translationX
        TranslateAnimation animation = new TranslateAnimation(0, 0, fromYDelta, toYDelta);
        custom(view, animation, duration, onAnimationCompletion);

    }

    /**
     * @param view
     * @param animation
     * @param duration
     * @param completionBlock
     */
    public static void custom(final View view, Animation animation, final int duration,
                              final Runnable completionBlock)
    {
        animation.setDuration(duration);
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
        view.startAnimation(animation);
    }

}
