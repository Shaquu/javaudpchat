/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/7/18 9:57 PM
 */

package com.github.shaquu.shared.prefs;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type JUPrefsExceptionTest.
 */
class JUPrefsExceptionTest {

    private final static Logger logger = Logger.getLogger(JUPrefsExceptionTest.class.getName());

    /**
     * Constructor test.
     */
    @Test
    public void constructor() {
        logger.log(Level.INFO, "Starting test constructor");

        String message = "message";
        Exception exception = new Exception(message);

        JUPrefsException juPrefsException = new JUPrefsException(message, exception);

        assertEquals(message, juPrefsException.getMessage(), "Message is not the same");
        assertEquals(exception, juPrefsException.getCause(), "Message is not the same");
    }
}