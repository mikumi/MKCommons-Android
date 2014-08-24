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

package com.michael_kuck.android.mkcommons;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by michaelkuck on 7/30/14.
 */
// TODO: find a better name
public class Android {

    private static Application application = null;

    public static void init(final Application application) {
        Android.application = application;
    }

    public static Application getApplication() {
        if (application == null) {
            throw new IllegalStateException(
                    "MKCommons must be initialized immediately after creating the application (best in constructor).");
        }
        return application;
    }

    public static String getPackageName() {
        final String packageName = getApplication().getPackageName();
        return packageName;
    }

    /**
     * @return
     */
    public static PackageInfo getPackageInfo() throws PackageManager.NameNotFoundException {
        final Context applicationContext = getApplication();
        final String packageName = getPackageName();
        final PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(packageName, 0);
        return packageInfo;
    }

    /**
     * Retrieve the version code from the package
     *
     * @return An int containing the version code
     */
    public static int getVersionCode() throws PackageManager.NameNotFoundException {
        final PackageInfo packageInfo = getPackageInfo();
        return packageInfo.versionCode;
    }

    /**
     * Retrieve the version code from the package
     *
     * @return An int containing the version code
     */
    public static String getVersionString() throws PackageManager.NameNotFoundException {
        final PackageInfo packageInfo = getPackageInfo();
        return packageInfo.versionName;
    }
}
