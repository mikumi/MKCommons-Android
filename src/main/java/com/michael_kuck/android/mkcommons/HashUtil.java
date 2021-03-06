/*
 * *
 *  * HashUtil.java
 *  * as part of mkcommons-android
 *  *
 *  * Created by michaelkuck, last updated on 7/29/14 2:12 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.android.mkcommons;

import com.michael_kuck.commons.Log;

/**
 * Created by michaelkuck on 7/29/14.
 */
public class HashUtil {

    public static String md5(final String message) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(message.getBytes());
            StringBuilder sb = new StringBuilder();
            for (final byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            Log.error("Could not find MD5 hashing algorithm: " + e.getLocalizedMessage());
        }
        return null;
    }

}
