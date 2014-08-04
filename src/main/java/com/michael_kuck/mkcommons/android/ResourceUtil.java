/*
 * *
 *  * ResourceHelper.java
 *  * as part of whereisthat-android
 *  *
 *  * Created by michaelkuck, last updated on 7/30/14 2:29 PM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.mkcommons.android;

import com.michael_kuck.mkcommons.Stopwatch;

import java.io.*;

/**
 * Created by michaelkuck on 7/30/14.
 */
public class ResourceUtil {

    public static boolean isFileFromRawResourceReady(final String fileName) {
        final File outputFile = new File(MKCommons.getApplication().getExternalFilesDir(null), fileName);
        return outputFile.exists();
    }

    public static File getFileFromRawResources(final int rawResourceId) {
        final String resourceName = MKCommons.getApplication().getResources().getResourceEntryName(rawResourceId);
        final String fileName = resourceName + ".raw";
        final File outputFile = new File(MKCommons.getApplication().getExternalFilesDir(null), fileName);

        if (outputFile.exists()) {
            Log.verbose("Accessing previously copied raw resource: " + fileName);
            return outputFile;
        }

        Log.debug("Copying raw resource to external file dir: " + fileName);
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        // TODO: clean up
        InputStream inputStream = null;
        OutputStream outputStream = null;
        boolean success = true;
        try
        {
            inputStream = MKCommons.getApplication().getResources().openRawResource(rawResourceId);
            outputStream = new FileOutputStream(outputFile);

            final byte[] data = new byte[1024 * 64]; // TODO: good size?
            while (true) {
                if (inputStream.read(data) < 0) {
                    break;
                }
                outputStream.write(data);
            }
        } catch (final FileNotFoundException e)
        {
            Log.error("Could not file: " + e.getLocalizedMessage());
            success = false;
        } catch (final IOException e)
        {
            Log.error("Could not write file: " + e.getLocalizedMessage());
            success = false;
        } finally
        {
            try
            {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (final IOException e)
            {
                Log.error("Could not close map tiles file: " + e.getLocalizedMessage());
            }
        }

        stopwatch.stop();
        Log.verbose("Copying file took: " + stopwatch.getSeconds() + "s");
        return (success) ? outputFile : null;
    }

    /**
     * @param filename
     */
    public static void removeExternalFile(final String filename) {
        final File file = new File(MKCommons.getApplication().getExternalFilesDir(null), filename);
        if (file.delete()) {
            Log.debug(file.getName() + " deleted.");
        } else
        {
            Log.debug("Could not delete " + file.getName());
        }
    }

}
