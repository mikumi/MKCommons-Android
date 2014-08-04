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
package com.michael_kuck.mkcommons.android;

/**
 * In case you want to know what S stands for: Smart. Smart Log.
 * Nope, I just wanted to keep it as short as "Log" without using the same name as Android does.
 *
 * @author michaelkuck
 */
public class Log {

    public static enum Level {
        NONE, ERROR, WARNING, INFO, DEBUG, VERBOSE;

        public boolean isAtLeast(final Level level) {
            return this.ordinal() >= level.ordinal();
        }

    }

    /**
     * The level of information to be logged.
     */
    private static Level logLevel = Level.INFO;

    private static boolean shouldEmphasizeTags = false;

    /**
     *
     */
    private Log() {
    }

    /**
     * Set the level of information to be logged.
     *
     * @param level
     */
    public static void setLogLevel(final Level level) {
        Log.info("Log level set to: " + level.toString());
        logLevel = level;
    }

    /**
     * @param shouldEmphasizeTags
     */
    public static void setShouldEmphasizeTags(final boolean shouldEmphasizeTags) {
        Log.shouldEmphasizeTags = shouldEmphasizeTags;
    }

    /**
     * Log an error.
     *
     * @param message
     */
    public static void error(final String message) {
        e(getLogTag(), message);
    }

    /**
     * @param LOG_TAG
     * @param message
     */
    private static void e(final String LOG_TAG, final String message) {
        if (logLevel.isAtLeast(Level.ERROR)) {
            android.util.Log.e(LOG_TAG, message);
        }
    }

    /**
     * Log an error.
     *
     * @param message
     */
    public static void warning(final String message) {
        w(getLogTag(), message);
    }

    /**
     * @param LOG_TAG
     * @param message
     */
    private static void w(final String LOG_TAG, final String message) {
        if (logLevel.isAtLeast(Level.WARNING)) {
            android.util.Log.w(LOG_TAG, message);
        }
    }

    /**
     * Log an error.
     *
     * @param message
     */
    public static void info(final String message) {
        i(getLogTag(), message);
    }

    /**
     * @param LOG_TAG
     * @param message
     */
    private static void i(final String LOG_TAG, final String message) {
        if (logLevel.isAtLeast(Level.INFO)) {
            android.util.Log.i(LOG_TAG, message);
        }
    }

    /**
     * Log an error.
     *
     * @param message
     */
    public static void debug(final String message) {
        d(getLogTag(), message);
    }

    /**
     * @param LOG_TAG
     * @param message
     */
    private static void d(final String LOG_TAG, final String message) {
        if (logLevel.isAtLeast(Level.DEBUG)) {
            android.util.Log.d(LOG_TAG, message);
        }
    }

    /**
     * Log an error.
     *
     * @param message
     */
    public static void verbose(final String message) {
        v(getLogTag(), message);
    }

    /**
     * @param LOG_TAG
     * @param message
     */
    private static void v(final String LOG_TAG, final String message) {
        if (logLevel.isAtLeast(Level.VERBOSE)) {
            android.util.Log.v(LOG_TAG, message);
        }
    }

    /**
     * @return
     */
    private static String getLogTag() {
        // 0 is current Thread, 1 is this method, 2 is log method, 3 is original calling method
        final StackTraceElement element = Thread.currentThread().getStackTrace()[4];
        final String classNameParts[] = element.getClassName().split("\\.");
        final String className = classNameParts[classNameParts.length - 1];
        return (shouldEmphasizeTags) ? "[" + className + "]" : className;
    }
}
