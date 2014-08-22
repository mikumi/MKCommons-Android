/*
 * *
 *  * SystemUtil.java
 *  * as part of mkcommons-android
 *  *
 *  * Created by michaelkuck, last updated on 7/30/14 11:22 AM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

/**
 *
 */
package com.michael_kuck.android.mkcommons;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.michael_kuck.commons.Log;

import java.lang.reflect.Field;

/**
 * @author michaelkuck
 */
public class SystemUtil {

    /**
     * @return
     */
    public static Point getScreenSize() {
        WindowManager windowManager =
                (WindowManager) MKCommons.getApplication().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        return displaySize;
    }

    /**
     * @return
     */
    public static boolean isTablet() {
        final Context applicationContext = MKCommons.getApplication();
        final Configuration conf = applicationContext.getResources().getConfiguration();
        int screenLayout = 1;
        try {
            final Field field = conf.getClass().getDeclaredField("screenLayout");
            screenLayout = field.getInt(conf);
        } catch (final Exception e) {
            Log.error("Error while checking if tablet: " + e.getLocalizedMessage());
        }

        final int screenType = screenLayout & 15;

        return (screenType >= 3);

    }

    /**
     * @return
     */
    public static String getPackageName() {
        final String packageName = MKCommons.getApplication().getPackageName();
        return packageName;
    }

    /**
     * @return
     */
    public static PackageInfo getPackageInfo() throws NameNotFoundException {
        final Context applicationContext = MKCommons.getApplication();
        final String packageName = getPackageName();
        final PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(packageName, 0);
        return packageInfo;
    }

    /**
     * Retrieve the version code from the package
     *
     * @return An int containing the version code
     */
    public static int getVersionCode() throws NameNotFoundException {
        final PackageInfo packageInfo = getPackageInfo();
        return packageInfo.versionCode;
    }

    /**
     * Retrieve the version code from the package
     *
     * @return An int containing the version code
     */
    public static String getVersionString() throws NameNotFoundException {
        final PackageInfo packageInfo = getPackageInfo();
        return packageInfo.versionName;
    }

    /**
     * Returns the maximum available memory for this app (max heap size). This can either be the
     * normal max heap size or the max heap size with android:largeHeap="true"
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static int getMaxHeapSize() {
        final Context applicationContext = MKCommons.getApplication();
        final ActivityManager activityManager =
                (ActivityManager) applicationContext.getSystemService(Context.ACTIVITY_SERVICE);

        final int maxMemoryNormal = activityManager.getMemoryClass();
        final int maxMemoryLarge;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            maxMemoryLarge = activityManager.getLargeMemoryClass();
        } else {
            maxMemoryLarge = 0;
        }

        Log.verbose("Max heap normal: " + maxMemoryNormal + "MB, max heap large: " + maxMemoryLarge + "MB");
        final int maxMemory = Math.max(maxMemoryNormal, maxMemoryLarge);
        return maxMemory;
    }

    public static void hideKeyboardForView(final View view) {
        final InputMethodManager imm =
                (InputMethodManager) MKCommons.getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isDebug(final Context context) {
        // TODO: refactor, use android specific
        final boolean isDebug = (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        return isDebug;
    }

}
