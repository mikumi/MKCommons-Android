/*
 * *
 *  * PersistentSettings.java
 *  * as part of mkcommons-android
 *  *
 *  * Created by michaelkuck, last updated on 7/31/14 10:23 AM
 *  * Unless otherwise stated in a separate LICENSE file for this project
 *  * or agreed via contract, all rights reserved by the author.
 *
 */

package com.michael_kuck.android.mkcommons;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.michael_kuck.commons.Log;

/**
 * Created by michaelkuck on 7/31/14.
 */
public class PersistentSettings {

    private final SharedPreferences preferences =
            PreferenceManager.getDefaultSharedPreferences(Android.getApplication());

    /**
     * Hold the static singleton
     */
    private static class SingletonHolder {
        static final PersistentSettings INSTANCE = new PersistentSettings();
    }

    /**
     * Get the singleton instance. A new one will be created if necessart
     *
     * @return The singleton instance
     */
    public static PersistentSettings getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * @param key
     * @param value
     */
    public void setBoolean(final String key, final boolean value) {
        synchronized (preferences) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    /**
     * @param key
     * @return
     */
    public boolean getBoolean(final String key) {
        synchronized (preferences) {
            return preferences.getBoolean(key, false);
        }
    }

    /**
     * @param key
     * @param value
     */
    public void setInt(final String key, final int value) {
        synchronized (preferences) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    /**
     * @param key
     * @return
     */
    public int getInt(final String key) {
        synchronized (preferences) {
            return preferences.getInt(key, 0);
        }
    }

    /**
     * @param key
     * @param value
     */
    public void setFloat(final String key, final float value) {
        synchronized (preferences) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putFloat(key, value);
            editor.apply();
        }
    }

    /**
     * @param key
     * @return
     */
    public double getFloat(final String key) {
        synchronized (preferences) {
            return preferences.getFloat(key, 0.0f);
        }
    }

    /**
     * @param key
     * @param value
     */
    public void setString(final String key, final String value) {
        synchronized (preferences) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    /**
     * @param key
     * @return
     */
    public String getString(final String key) {
        synchronized (preferences) {
            return preferences.getString(key, null);
        }
    }

    /**
     * @param key
     * @param object
     */
    public void setObject(final String key, final Object object) {
        synchronized (preferences) {
            final SharedPreferences.Editor editor = preferences.edit();
            final Gson gson = new Gson();
            final String json = gson.toJson(object);
            editor.putString(key, json);
            editor.apply();
        }
    }

    /**
     * @param key
     * @return
     */
    public Object getObject(final String key, final Class<?> cls) {
        synchronized (preferences) {
            Object object = null;
            final String json = preferences.getString(key, null);
            if (json != null) {
                final Gson gson = new Gson();
                try {
                    object = gson.fromJson(json, cls);
                } catch (JsonSyntaxException e) {
                    Log.error("Could not parse json string to " + cls.getSimpleName());
                }
            }
            return object;
        }
    }

    /**
     * @param key
     * @return
     */
    public boolean hasKey(final String key) {
        synchronized (preferences) {
            return preferences.contains(key);
        }
    }

    /**
     * @param key
     */
    public void removeItemForKey(final String key) {
        synchronized (preferences) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.remove(key);
            editor.apply();
        }
    }

    /**
     *
     */
    public void resetAll() {
        synchronized (preferences) {
            final SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
        }
    }

}
