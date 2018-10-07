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
 * The type BaseDataTest.
 */
class BaseDataTest {

    private final static Logger logger = Logger.getLogger(BaseDataTest.class.getName());

    /**
     * Constructor test.
     */
    @Test
    void constructor() {
        logger.log(Level.INFO, "Starting test constructor");

        BaseData.Type type = BaseData.Type.REQUEST;
        final String clientName = "clientName";
        final long id = 1;

        BaseData baseData = new LogonData(type, clientName);

        assertEquals(type, baseData.getType(), "Type is not the same.");

        baseData.setId(id);

        assertEquals(id, baseData.getId(), "Id is not the same.");
    }
}