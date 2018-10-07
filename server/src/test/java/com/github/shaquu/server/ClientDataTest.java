/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/7/18 9:04 PM
 */

package com.github.shaquu.server;

import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * The type ClientDataTest.
 */
class ClientDataTest {

    private final static Logger logger = Logger.getLogger(ClientDataTest.class.getName());

    /**
     * Constructor test.
     */
    @Test
    void constructor() {
        logger.log(Level.INFO, "Starting test constructor");

        final String id1 = "id1";
        InetAddress address1 = null;
        final Integer port1 = 11111;
        final String name1 = "name1";

        final String id2 = "id2";
        InetAddress address2 = null;
        final Integer port2 = 22222;
        final String name2 = "name2";

        try {
            address1 = InetAddress.getByName("127.0.0.1");
            address2 = InetAddress.getByName("127.0.0.2");
        } catch (UnknownHostException e) {
            fail("Failed while instancing address.");
        }

        ClientData clientData = new ClientData(id1, address1, port1, name1);

        assertEquals(id1, clientData.getId(), "Id1 is not the same.");
        assertEquals(address1, clientData.getAddress(), "Address1 is not the same.");
        assertEquals(port1, clientData.getPort(), "Port1 is not the same.");
        assertEquals(name1, clientData.getName(), "Name1 is not the same.");

        clientData.setId(id2);
        clientData.setAddress(address2);
        clientData.setPort(port2);
        clientData.setName(name2);

        assertEquals(id2, clientData.getId(), "Id2 is not the same.");
        assertEquals(address2, clientData.getAddress(), "Address2 is not the same.");
        assertEquals(port2, clientData.getPort(), "Port2 is not the same.");
        assertEquals(name2, clientData.getName(), "Name2 is not the same.");
    }
}