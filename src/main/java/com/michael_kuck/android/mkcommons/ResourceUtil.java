/*
 * *
 *  * ResourceUtil.java
 *  * as part of mkcommons-android
 *  *
 *  * Created by michaelkuck, last updated on 7/30/14 2:29 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.android.mkcommons;

import com.michael_kuck.commons.FileUtil;
import com.michael_kuck.commons.Log;
import com.michael_kuck.commons.Stopwatch;

import java.io.*;

/**
 * Created by michaelkuck on 7/30/14.
 */
public class ResourceUtil {

    public static boolean isFileFromRawResourceReady(final String fileName) {
        final File outputFile = new File(Android.getApplication().getExternalFilesDir(null), fileName);
        return outputFile.exists();
    }

    /**
     * Copies a raw resource to external storage and returns a handle to that. If the file already exists, it will not
     * be copied again.
     * <p/>
     * On Android it is impossible to get a direct file handle to a raw resource. If this is still required, we can get
     * around that by using this method.
     *
     * @param rawResourceId ID of the raw resource which will be copied to external storage
     * @return File handle to the copied resource
     */
    public static File getFileFromRawResources(final int rawResourceId) throws IOException {
        final String resourceName = Android.getApplication().getResources().getResourceEntryName(rawResourceId);
        final String fileName = resourceName + ".raw";
        // null means external root (instead of a subdir)
        final File outputFile = new File(Android.getApplication().getExternalFilesDir(null), fileName);

        if (outputFile.exists()) {
            Log.verbose("Accessing previously copied raw resource: " + fileName);
            return outputFile;
        } else {
            Log.debug("Copying raw resource to external file dir: " + fileName);
            Stopwatch stopwatch = new Stopwatch();
            stopwatch.start();

            InputStream inputStream = Android.getApplication().getResources().openRawResource(rawResourceId);
            OutputStream outputStream = new FileOutputStream(outputFile);
            FileUtil.copyStream(inputStream, outputStream);
            FileUtil.closeHandle(inputStream);
            FileUtil.closeHandle(outputStream);

            stopwatch.stop();
            Log.verbose("Copying file took: " + stopwatch.getTimeInSeconds() + "s");

            return outputFile;
        }
    }

    /**
     * @param filename
     */
    public static void removeFileFromExternalStorage(final String filename) {
        final File file = new File(Android.getApplication().getExternalFilesDir(null), filename);
        if (file.delete()) {
            Log.debug(file.getName() + " deleted.");
        } else {
            Log.debug("Could not delete " + file.getName());
        }
    }

}
