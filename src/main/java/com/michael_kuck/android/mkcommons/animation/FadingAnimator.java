/*
 * *
 *  * This file is part of mkcommons-android
 *  *
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.android.mkcommons.animation;

import android.animation.Animator;
import android.view.View;

public class FadingAnimator {

    public static void fade(final View view, final float endValue, final int durationInMillis,
                            final Runnable completion)
    {
        view.animate().alpha(endValue).setDuration(durationInMillis).setListener(new AnimatorCompletionListener() {
            @Override
            public void onAnimationEnd(final Animator animation) {
                if (completion != null) {
                    completion.run();
                }
            }
        }).start();
    }

    public static void fadeInFromInvisible(final View view, final int durationInMillis, final Runnable completion) {
        view.setAlpha(0.0f);
        view.setVisibility(View.VISIBLE);
        fade(view, 1.0f, durationInMillis, completion);
    }

    public static void fadeOutToInvisible(final View view, final int durationInMillis, final Runnable completion) {
        fade(view, 0.0f, durationInMillis, new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.INVISIBLE);
                if (completion != null) {
                    completion.run();
                }
            }
        });
    }

    public static void fadeInOut(final View view, final int animationDurationInMillis, final int showDurationInMillis,
                                 final Runnable completion)
    {
        fadeInFromInvisible(view, animationDurationInMillis, new Runnable() {
            @Override
            public void run() {
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fadeOutToInvisible(view, animationDurationInMillis, completion);
                    }
                }, showDurationInMillis);
            }
        });
    }

}
