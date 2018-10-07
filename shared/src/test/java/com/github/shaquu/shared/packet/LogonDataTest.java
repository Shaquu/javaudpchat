/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/7/18 9:47 PM
 */

package com.github.shaquu.shared.packet;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type LogonDataTest.
 */
class LogonDataTest {

    private final static Logger logger = Logger.getLogger(LogonDataTest.class.getName());

    /**
     * Constructor test.
     */
    @Test
    void constructor() {
        logger.log(Level.INFO, "Starting test constructor");

        BaseData.Type type = BaseData.Type.REQUEST;
        final String clientName = "clientName";
        final long id = 1;

        LogonData logonData = new LogonData(type, clientName);

        assertEquals(type, logonData.getType(), "Type is not the same.");
        assertEquals(clientName, logonData.getClientName(), "ClientName is not the same.");

        logonData.setId(id);

        assertEquals(id, logonData.getId(), "Id is not the same.");
    }
}