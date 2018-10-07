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
 * The type MessageDataTest.
 */
class MessageDataTest {

    private final static Logger logger = Logger.getLogger(MessageDataTest.class.getName());

    /**
     * Constructor test.
     */
    @Test
    void constructor() {
        logger.log(Level.INFO, "Starting test constructor");

        BaseData.Type type = BaseData.Type.REQUEST;
        final String clientName = "clientName";
        final String message = "message";
        final long id = 1;

        MessageData messageData = new MessageData(type, clientName, message);

        assertEquals(type, messageData.getType(), "Type is not the same.");
        assertEquals(clientName, messageData.getClientName(), "ClientName is not the same.");
        assertEquals(message, messageData.getMessage(), "Message is not the same.");

        messageData.setId(id);

        assertEquals(id, messageData.getId(), "Id is not the same.");
    }
}