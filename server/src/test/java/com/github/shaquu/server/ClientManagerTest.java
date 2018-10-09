/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/9/18 5:52 PM
 */

package com.github.shaquu.server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * The type ClientManagerTest.
 */
class ClientManagerTest {

    private final static Logger logger = Logger.getLogger(ClientManagerTest.class.getName());

    private ClientManager manager;
    private InetAddress address;
    private Integer port;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        logger.log(Level.INFO, "Starting setUp");
        manager = new ClientManager();

        try {
            address = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            fail("Cannot initialize inetAddress");
        }

        port = 12345;
    }

    /**
     * Destroy.
     */
    @AfterEach
    void destroy() {
        manager = null;
    }

    /**
     * Test all.
     */
    @Test
    void testAll() {
        logger.log(Level.INFO, "Starting test testAll");

        final String clientName = "clientName";
        manager.add(address, port, clientName);
        final String shortId = manager.getShortId(address, port);
        final String clientNameV = manager.getClientName(address, port);
        final String longId = manager.getLongId(address, port);
        ClientData clientData = manager.get(shortId);
    }

    @Test
    void loop() {
        logger.log(Level.INFO, "Starting test loop");

        final String clientName = "clientName";
        int i = 0;
        manager.add(address, port - i, clientName + i++);
        manager.add(address, port - i, clientName + i++);
        manager.add(address, port - i, clientName + i++);
        manager.add(address, port - i, clientName + i++);

        int j = 0;
        for (ClientData clientData : manager) {
            assertEquals(clientName + j, clientData.getName(), "Name not the same.");
            assertEquals(new Integer(port - j), clientData.getPort(), "Port not the same.");
            assertEquals(address, clientData.getAddress(), "Address not the same.");
            j++;
        }

        assertEquals(i, j, "Not looped for all.");
    }

    @Test
    void iterator() {
        logger.log(Level.INFO, "Starting test iterator");

        final String clientName = "clientName";
        int i = 0;
        manager.add(address, port - i, clientName + i++);
        manager.add(address, port - i, clientName + i++);
        manager.add(address, port - i, clientName + i++);
        manager.add(address, port - i, clientName + i++);

        Iterator<ClientData> iterator = manager.iterator();

        int j = 0;
        ClientData clientData;
        while (iterator.hasNext()) {
            clientData = iterator.next();
            assertEquals(clientName + j, clientData.getName(), "Name not the same.");
            assertEquals(new Integer(port - j), clientData.getPort(), "Port not the same.");
            assertEquals(address, clientData.getAddress(), "Address not the same.");
            j++;
        }

        assertEquals(i, j, "Not looped for all.");
    }
}