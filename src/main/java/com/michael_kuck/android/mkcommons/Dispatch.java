/*
 * *
 *  * This file is part of mkcommons-android
 *  *
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.android.mkcommons;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

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
                if (completionTask != null) {
                    completionTask.run();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
    }

    public static void toMainThread(final Runnable completion) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (completion != null) {
                    completion.run();
                }
            }
        });
    }

}
