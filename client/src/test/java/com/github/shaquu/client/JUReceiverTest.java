/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/9/18 5:46 PM
 */

package com.github.shaquu.client;

import com.github.shaquu.shared.prefs.JUPrefsException;
import org.junit.jupiter.api.Test;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * The type JUReceiverTest.
 */
class JUReceiverTest {

    private final static Logger logger = Logger.getLogger(JUReceiverTest.class.getName());

    /**
     * Constructor test.
     */
    @Test
    void constructor() {
        logger.log(Level.INFO, "Starting test constructor");

        try {
            DatagramSocket socket = new DatagramSocket();
            JUReceiver receiver = new JUReceiver(socket);
            Thread receiverThread = new Thread(receiver);
            receiverThread.start();
        } catch (SocketException | JUPrefsException e) {
            fail("Failed on constructor.");
        }
    }
}