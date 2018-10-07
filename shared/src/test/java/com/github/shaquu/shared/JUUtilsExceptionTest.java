/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/7/18 8:25 PM
 */

package com.github.shaquu.shared;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type JUUtilsExceptionTest.
 */
class JUUtilsExceptionTest {

    private final static Logger logger = Logger.getLogger(JUUtilsException.class.getName());

    /**
     * Constructor test.
     */
    @Test
    public void constructor() {
        logger.log(Level.INFO, "Starting test constructor");

        String message = "message";
        Exception exception = new Exception(message);

        JUUtilsException juUtilsException1 = new JUUtilsException(message);
        JUUtilsException juUtilsException2 = new JUUtilsException(message, exception);

        assertEquals(message, juUtilsException1.getMessage(), "Message1 is not the same");
        assertEquals(message, juUtilsException2.getMessage(), "Message2 is not the same");
        assertEquals(exception, juUtilsException2.getCause(), "Exception is not the same");
    }
}