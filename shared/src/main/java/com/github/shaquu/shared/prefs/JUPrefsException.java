/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.shared.prefs;

/**
 * JavaUdpChat PreferencesException
 */
public class JUPrefsException extends Exception {

    /**
     * Instantiates a new JUPrefsException.
     *
     * @param message   the message
     * @param exception the exception
     */
    public JUPrefsException(String message, Exception exception) {
        super(message, exception);
    }
}
