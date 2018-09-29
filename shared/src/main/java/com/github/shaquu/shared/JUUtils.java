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

    /**
     * Instantiates a new JavaUdpUtils.
     */
    public JUUtils() {

    }

    /**
     * Gets public ip.
     *
     * @return the public ip
     *
     * @throws JUUtilsException the JavaUdpUtils exception
     */
    public static String getPublicIp() throws JUUtilsException {
        try {
            URL checkip = new URL("http://checkip.amazonaws.com");
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
