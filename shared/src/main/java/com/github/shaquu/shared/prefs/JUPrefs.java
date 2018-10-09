/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.shared.prefs;

import java.util.prefs.Preferences;

/**
 * JavaUdpChat Preferences.
 */
public class JUPrefs {
    private static Preferences preferences;

    /**
     * Instantiates a new JUPrefs.
     *
     * @throws JUPrefsException on init fail
     */
    public JUPrefs() throws JUPrefsException {
        init();
    }

    private static void init() throws JUPrefsException {
        if (preferences == null) {
            try {
                preferences = Preferences.userRoot().node(JUPrefs.class.getName());
            } catch (Exception e) {
                throw new JUPrefsException("Failed on JUPrefs initialization.", e);
            }
        }
    }

    /**
     * Write value to preferences.
     *
     * @param path   the path where to write value
     * @param object the object itself
     * @param type   the type of the object
     *
     * @throws JUPrefsException if putting value in preferences fail
     */
    public static void write(String path, Object object, Type type) throws JUPrefsException {
        init();

        try {
            switch (type) {
                case INT:
                    preferences.putInt(path, (Integer) object);
                    break;
                case LONG:
                    preferences.putLong(path, (Long) object);
                    break;
                case FLOAT:
                    preferences.putFloat(path, (Float) object);
                    break;
                case DOUBLE:
                    preferences.putDouble(path, (Double) object);
                    break;
                case STRING:
                default:
                    preferences.put(path, (String) object);
                    break;
                case BOOLEAN:
                    preferences.putBoolean(path, (Boolean) object);
                    break;
                case BYTE_ARRAY:
                    preferences.putByteArray(path, (byte[]) object);
                    break;
            }
        } catch (Exception e) {
            throw new JUPrefsException("Failed on JUPrefs write, path[" + path + "], object[" + object + "], type[" + type + "].", e);
        }
    }

    /**
     * Check if there is value written on the path.
     *
     * @param path the path of the value
     *
     * @return the boolean if path is written
     *
     */
    public static boolean exist(String path) {
        String value = preferences.get(path, "false");
        if (value != null) {
            return !"".equals(value) && !"false".equalsIgnoreCase(value);
        } else return false;
    }

    /**
     * Read object from preferences on the path.
     *
     * @param path     the path of the value
     * @param optional the optional to return if there is no value written on path
     * @param type     the type of the object
     *
     * @return the object itself
     *
     * @throws JUPrefsException if reading fail
     */
    public static Object read(String path, Object optional, Type type) throws JUPrefsException {
        init();

        try {
            switch (type) {
                case INT:
                    return preferences.getInt(path, (Integer) optional);
                case LONG:
                    return preferences.getLong(path, (Long) optional);
                case FLOAT:
                    return preferences.getFloat(path, (Float) optional);
                case DOUBLE:
                    return preferences.getDouble(path, (Double) optional);
                case STRING:
                default:
                    return preferences.get(path, (String) optional);
                case BOOLEAN:
                    return preferences.getBoolean(path, (Boolean) optional);
                case BYTE_ARRAY:
                    return preferences.getByteArray(path, (byte[]) optional);
            }
        } catch (Exception e) {
            throw new JUPrefsException("Failed on JUPrefs read, path[" + path + "], optional[" + optional + "], type[" + type + "].", e);
        }
    }

    /**
     * Remove value from references
     *
     * @param path the path of the value
     *
     * @throws JUPrefsException if removing fail
     */
    public static void remove(String path) throws JUPrefsException {
        init();

        try {
            preferences.remove(path);
        } catch (Exception e) {
            throw new JUPrefsException("Failed on JUPrefs remove, path[" + path + "].", e);
        }
    }

    /**
     * The type of data used in Preferences
     */
    public enum Type {
        /**
         * String type.
         */
        STRING,
        /**
         * Boolean type.
         */
        BOOLEAN,
        /**
         * Byte array type.
         */
        BYTE_ARRAY,
        /**
         * Long type.
         */
        LONG,
        /**
         * Int type.
         */
        INT,
        /**
         * Float type.
         */
        FLOAT,
        /**
         * Double type.
         */
        DOUBLE
    }
}
