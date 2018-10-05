/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.shared;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
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

    /**
     * Instantiates a new JavaUdpUtils.
     */
    public JUUtils() {

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
                throw new JUUtilsException("Cannot determing ip address.", ee);
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

}
