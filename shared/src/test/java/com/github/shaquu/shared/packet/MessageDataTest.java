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

        MessageData messageData1 = new MessageData(type, clientName, message);
        MessageData messageData2 = new MessageData(type, clientName, message);

        assertEquals(type, messageData1.getType(), "Type is not the same.");
        assertEquals(clientName, messageData1.getClientName(), "ClientName is not the same.");
        assertEquals(message, messageData1.getMessage(), "Message is not the same.");

        messageData1.setId(id);
        messageData2.setId(id);

        assertEquals(id, messageData1.getId(), "Id is not the same.");

        assertEquals(messageData1.hashCode(), messageData2.hashCode(), "HashCode is not the same.");
    }
}