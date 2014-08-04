/*
 * *
 *  * ViewElement.java
 *  * as part of mkcommons-android
 *  *
 *  * Created by michaelkuck, last updated on 8/1/14 3:05 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.mkcommons.android;

import android.app.Activity;
import android.view.ViewGroup;

/**
 * Created by michaelkuck on 8/1/14.
 */
public abstract class ViewElement {

    private final Activity  parentActivity;
    private final ViewGroup parentLayout; // TODO: should it be View or ViewGroup?

    /**
     * @param parentActivity
     * @param parentLayout
     */
    public ViewElement(final Activity parentActivity, final ViewGroup parentLayout) {
        this.parentActivity = parentActivity;
        this.parentLayout = parentLayout;
    }

    /**
     *
     */
    public void attach() {
        parentLayout.addView(getContentLayout());
    }

    /**
     *
     */
    public void detach() {
        parentLayout.removeView(getContentLayout());
    }

    /**
     * @return
     */
    public Activity getParentActivity() {
        return parentActivity;
    }

    /**
     * @return
     */
    public ViewGroup getParentLayout() {
        return parentLayout;
    }

    public abstract ViewGroup getContentLayout();  // TODO: should it be View or ViewGroup?

}
