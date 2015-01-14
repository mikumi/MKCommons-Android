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

package com.michael_kuck.android.mkcommons;

import android.app.Activity;
import android.view.ViewGroup;

// TODO: this approach is shit and this class should be removed
@Deprecated
public abstract class ViewElement {

    private final Activity  parentActivity;
    private final ViewGroup parentLayout; // TODO: should it be View or ViewGroup?

    public ViewElement(final Activity parentActivity, final ViewGroup parentLayout) {
        this.parentActivity = parentActivity;
        this.parentLayout = parentLayout;
    }

    public Activity getParentActivity() {
        return parentActivity;
    }

    public ViewGroup getParentLayout() {
        return parentLayout;
    }

    public abstract ViewGroup getContentLayout();  // TODO: should it be View or ViewGroup?

}
