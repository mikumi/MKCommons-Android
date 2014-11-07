/*
 * *
 *  * This file is part of whereisthat-android
 *  *
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.android.mkcommons;

import android.os.AsyncTask;

public class Dispatch {

    // TODO: Important, verify this is correct AsyncTask usage!
    public static void toBackground(final Runnable backgroundTask, final Runnable completionTask) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... params) {
                backgroundTask.run();
                return null;
            }

            @Override
            protected void onPostExecute(final Void aVoid) {
                completionTask.run();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
    }

}
