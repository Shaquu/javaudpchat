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

import static org.junit.jupiter.api.Assertions.fail;

/**
 * The type ClientManagerTest.
 */
class ClientManagerTest {

    private ClientManager manager;
    private InetAddress address;
    private Integer port;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
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
        final String clientName = "clientName";
        manager.add(address, port, clientName);
        final String shortId = manager.getShortId(address, port);
        final String clientNameV = manager.getClientName(address, port);
        final String longId = manager.getLongId(address, port);
        ClientData clientData = manager.get(shortId);
    }
}