/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * The type JavaUdpUtils.
 */
public class JUUtils {

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

}
