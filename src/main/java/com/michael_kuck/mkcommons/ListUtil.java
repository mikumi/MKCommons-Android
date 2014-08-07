/*
 * *
 *  * This file is part of mkcommons-android
 *  *
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.mkcommons;

import java.util.List;

public class ListUtil {

    public static <T> T getFirstItem(final List<T> list) {
        if (list.isEmpty()) {
            return null;
        } else{
            return list.get(0);
        }
    }

    public static <T> T getLastItem(final List<T> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(list.size() - 1);
        }
    }

}
