/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.shared;

import com.github.shaquu.shared.prefs.JUPrefs;
import com.github.shaquu.shared.prefs.JUPrefsException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * The type JavaUdpUtils.
 */
public class JUUtils {

    /**
     * The constant BUFFER_SIZE.
     */
    public final static int BUFFER_SIZE = 1024;

    private static Scanner keyboard;

    private static Logger logger = Logger.getLogger(JUUtils.class.getName());

    /**
     * Instantiates a new JavaUdpUtils.
     */
    public JUUtils() {
        JUUtils.keyboard = new Scanner(System.in);
    }

    /**
     * Gets global ip.
     *
     * @return the global ip
     *
     * @throws JUUtilsException the JavaUdpUtils exception
     */
    public static String getGlobalIp() throws JUUtilsException {
        try {
            URL checkip = new URL("https://raw.githubusercontent.com/Shaquu/javaudpchat/master/global.ip");
            BufferedReader in = new BufferedReader(new InputStreamReader(checkip.openStream()));
            return in.readLine();
        } catch (IOException e) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException ee) {
                throw new JUUtilsException("Cannot determine ip address.", ee);
            }
        }
    }

    /**
     * Compress byte [ ].
     *
     * @param data the data
     *
     * @return the byte [ ]
     *
     * @throws IOException the io exception
     */
    public static byte[] compress(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        deflater.finish();
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * Decompress byte [ ].
     *
     * @param data the data
     *
     * @return the byte [ ]
     *
     * @throws IOException         the io exception
     * @throws DataFormatException the data format exception
     */
    public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * Gets pref from keyboard.
     *
     * @param path      the path
     * @param message   the message
     * @param mandatory how many times to try to get value from keyboard, 0 is unlimited
     * @param prefsType the prefs type
     * @param optional  the optional value
     *
     * @throws JUPrefsException the ju prefs exception
     * @throws JUUtilsException the ju utils exception
     */
    public static void getPrefFromKeyboard(String path, String message, int mandatory, JUPrefs.Type prefsType, Object optional) throws JUPrefsException, JUUtilsException {
        String value;
        int count = 0;

        while (!JUPrefs.exist(path)) {
            if (mandatory != 0 && count > mandatory) {
                if (optional != null) {
                    JUPrefs.write(path, optional, prefsType);
                    return;
                } else {
                    throw new JUUtilsException("Getting value timeout.");
                }
            }

            logger.log(Level.INFO, message);
            value = keyboard.nextLine();

            if (optional != null) {
                if (value == null || value.trim().length() == 0) {
                    JUPrefs.write(path, optional, prefsType);
                    return;
                }
            }

            if (value != null && value.trim().length() > 0) {
                JUPrefs.write(path, value, prefsType);
                return;
            }

            count++;
        }
    }

    /**
     * Split message list.
     *
     * @param message the message
     * @param size    the size
     *
     * @return the list
     */
    public static List<String> splitMessage(String message, int size) {
        List<String> messageParts = new ArrayList<>();

        int length = message.length();
        for (int i = 0; i < length; i += size) {
            messageParts.add(message.substring(i, Math.min(length, i + size)));
        }

        return messageParts;
    }

}
