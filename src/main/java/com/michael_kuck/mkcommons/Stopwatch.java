/*
 * *
 *  * Stopwatch.java
 *  * as part of mkcommons-android
 *  *
 *  * Created by michaelkuck, last updated on 7/30/14 2:57 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.mkcommons;

/**
 * Created by michaelkuck on 7/30/14.
 */
public class Stopwatch {

    private long startTime;
    private long stopTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        stopTime = System.currentTimeMillis();
    }

    public long getTimeInMillis() {
        return stopTime - startTime;
    }

    public double getTimeInSeconds() {
        return (stopTime - startTime) / 1000.0f;
    }

}
