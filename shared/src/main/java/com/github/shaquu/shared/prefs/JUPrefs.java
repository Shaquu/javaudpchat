package com.github.shaquu.shared.prefs;

import com.github.shaquu.shared.JUUtils;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class JUPrefs {
    private static Preferences preferences;

    public JUPrefs() throws JUPrefsException {
        init();
    }

    private static void init() throws JUPrefsException {
        if (preferences == null) {
            try {
                preferences = Preferences.userRoot().node(JUPrefs.class.getName());

                String ip = JUUtils.getPublicIp();
                write("ip", ip, Type.STRING);

                int port = 12345;
                write("port", port, Type.INT);

                int buffer = 1024;
                write("buffer", buffer, Type.INT);
            } catch (Exception e) {
                throw new JUPrefsException("Failed on JUPrefs initialization.", e);
            }
        }
    }

    public static void write(String path, Object object, Type type) throws JUPrefsException {
        init();

        try {
            switch (type){
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

    public static boolean exist(String path) throws JUPrefsException {
        try {
            return preferences.nodeExists(path);
        } catch (BackingStoreException e) {
            throw new JUPrefsException("Failed on JUPrefs exist, path[" + path + "].", e);
        }
    }

    public static Object read(String path, Object optional, Type type) throws JUPrefsException {
        init();

        try {
            switch (type){
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

    public static void remove(String path) throws JUPrefsException {
        init();

        try {
            preferences.remove(path);
        } catch (Exception e) {
            throw new JUPrefsException("Failed on JUPrefs remove, path[" + path + "].", e);
        }
    }

    public enum Type {
        STRING, BOOLEAN, BYTE_ARRAY, LONG, INT, FLOAT, DOUBLE
    }
}
