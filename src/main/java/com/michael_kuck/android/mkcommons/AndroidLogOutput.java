/*
 * *
 *  * Log.java
 *  * as part of mkcommons-android
 *  *
 *  * Created by michaelkuck, last updated on 7/30/14 11:05 AM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

/**
 *
 */
package com.michael_kuck.android.mkcommons;

import com.michael_kuck.commons.logoutput.ILogOutput;

public class AndroidLogOutput implements ILogOutput {

    private boolean shouldEmphasizeTags = false;

    public AndroidLogOutput() {
    }

    /**
     * @param shouldEmphasizeTags
     */
    public void setShouldEmphasizeTags(final boolean shouldEmphasizeTags) {
        this.shouldEmphasizeTags = shouldEmphasizeTags;
    }

    @Override
    public void error(final String message) {
        android.util.Log.e(getLogTag(), message);
    }

    @Override
    public void warning(final String message) {
        android.util.Log.w(getLogTag(), message);
    }

    @Override
    public void info(final String message) {
        android.util.Log.i(getLogTag(), message);
    }

    @Override
    public void debug(final String message) {
        android.util.Log.d(getLogTag(), message);
    }

    @Override
    public void verbose(final String message) {
        android.util.Log.v(getLogTag(), message);
    }

    private String getLogTag() {
        // 0 is current Thread, 1 is this method, 2 is log method, [...?]
        final StackTraceElement element = Thread.currentThread().getStackTrace()[5];
        final String classNameParts[] = element.getClassName().split("\\.");
        final String className = classNameParts[classNameParts.length - 1];
        return (shouldEmphasizeTags) ? "[" + className + "]" : className;
    }
}
