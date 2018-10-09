/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/9/18 6:03 PM
 */

package com.github.shaquu.server;

import com.github.shaquu.shared.prefs.JUPrefsException;
import org.junit.jupiter.api.Test;

import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * The type JUServerTest.
 */
class JUServerTest {

    private final static Logger logger = Logger.getLogger(JUServerTest.class.getName());

    /**
     * Constructor test.
     */
    @Test
    void constructor() {
        logger.log(Level.INFO, "Starting test constructor");

        try {
            new JUServer();
        } catch (JUPrefsException | SocketException e) {
            fail("Failed on constructor.");
        }
    }
}