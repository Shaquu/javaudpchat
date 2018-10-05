/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.shared;

/**
 * The type JavaUdpUtilsException.
 */
public class JUUtilsException extends Exception {

    /**
     * Instantiates a new JavaUdpUtilsException.
     *
     * @param message the message
     */
    JUUtilsException(String message) {
        super(message);
    }

    /**
     * Instantiates a new JavaUdpUtilsException.
     *
     * @param message   the message
     * @param exception the exception
     */
    JUUtilsException(String message, Exception exception) {
        super(message, exception);
    }

}
