/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/9/18 5:45 PM
 */

/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/9/18 5:43 PM
 */

package com.github.shaquu.client;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The type JUClientTest.
 */
class JUClientTest {

    private final static Logger logger = Logger.getLogger(JUClientTest.class.getName());

    /**
     * Constructor test.
     */
    @Test
    void constructor() {
        logger.log(Level.INFO, "Starting test constructor");

        JUClient client = new JUClient();

        assertNotNull(client);
    }

}