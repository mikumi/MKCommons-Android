/*
 * *
 *  * MKCommons.java
 *  * as part of mkcommons-android
 *  *
 *  * Created by michaelkuck, last updated on 7/30/14 2:31 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.mkcommons.android;

import android.app.Application;

/**
 * Created by michaelkuck on 7/30/14.
 */
// TODO: find a better name
public class MKCommons {

    private static Application application = null;

    public static void init(final Application application) {
        MKCommons.application = application;
    }

    public static Application getApplication() {
        if (application == null) {
            throw new IllegalStateException(
                    "MKCommons must be initialized immediately after creating the application (best in constructor).");
        }
        return application;
    }

}
