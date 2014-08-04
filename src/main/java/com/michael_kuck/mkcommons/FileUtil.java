/*
 * *
 *  * FileHelper.java
 *  * as part of whereisthat-android
 *  *
 *  * Created by michaelkuck, last updated on 8/4/14 10:39 AM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.mkcommons;

import com.michael_kuck.mkcommons.android.Log;

import java.io.*;

/**
 * Created by michaelkuck on 8/4/14.
 */
public class FileUtil {

    public static final int FILE_COPY_BUFFER_SIZE = 1024 * 4; // TODO: is this a good buffer size?

    public void copyFile(final File inputFile, final File outputFile) throws IOException {
        final FileInputStream inputStream = new FileInputStream(inputFile);
        final FileOutputStream outputStream = new FileOutputStream(outputFile);
        copyStream(inputStream, outputStream);
        closeHandle(inputStream);
        closeHandle(outputStream);
    }

    public void copyStream(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] buffer = new byte[FILE_COPY_BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) >= 0) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }

    public void closeHandle(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.error("Failed to close handle: " + e.getLocalizedMessage());
            }
        }
    }

}
