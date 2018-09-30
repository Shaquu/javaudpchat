/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.shared.prefs;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * The type JavaUdpPrefs.
 */
public class JUPrefs {
    private static Preferences preferences;

    /**
     * Instantiates a new JavaUdpPrefs.
     *
     * @throws JUPrefsException the JavaUdpPefs exception
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
     * Write.
     *
     * @param path   the path
     * @param object the object
     * @param type   the type
     *
     * @throws JUPrefsException the JavaUdpPrefs exception
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
     * Exist boolean.
     *
     * @param path the path
     *
     * @return the boolean
     *
     * @throws JUPrefsException the JavaUdpPrefs exception
     */
    public static boolean exist(String path) throws JUPrefsException {
        try {
            return preferences.nodeExists(path);
        } catch (BackingStoreException e) {
            throw new JUPrefsException("Failed on JUPrefs exist, path[" + path + "].", e);
        }
    }

    /**
     * Read object.
     *
     * @param path     the path
     * @param optional the optional
     * @param type     the type
     *
     * @return the object
     *
     * @throws JUPrefsException the JavaUdpPrefs exception
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
                    return preferences.get(path, (String) optional);
                case BOOLEAN:
                    return preferences.getBoolean(path, (Boolean) optional);
                case BYTE_ARRAY:
                    return preferences.getByteArray(path, (byte[]) optional);
            }
        } catch (Exception e) {
            throw new JUPrefsException("Failed on JUPrefs read, path[" + path + "], optional[" + optional + "], type[" + type + "].", e);
        }

        return optional;
    }

    /**
     * Remove.
     *
     * @param path the path
     *
     * @throws JUPrefsException the JavaUdpPrefs exception
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
     * The enum Type.
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
