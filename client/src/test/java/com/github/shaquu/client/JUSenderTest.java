/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/9/18 5:47 PM
 */

package com.github.shaquu.client;

import com.github.shaquu.shared.JUUtilsException;
import com.github.shaquu.shared.prefs.JUPrefsException;
import org.junit.jupiter.api.Test;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * The type JUSenderTest.
 */
class JUSenderTest {

    private final static Logger logger = Logger.getLogger(JUSenderTest.class.getName());

    /**
     * Constructor test.
     */
    @Test
    void constructor() {
        logger.log(Level.INFO, "Starting test constructor");

        try {
            DatagramSocket socket = new DatagramSocket();
            JUSender sender = new JUSender(socket);
            Thread senderThread = new Thread(sender);
            senderThread.start();
        } catch (SocketException | JUPrefsException | JUUtilsException | UnknownHostException e) {
            fail("Failed on constructor.");
        }
    }
}