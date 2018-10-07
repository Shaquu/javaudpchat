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

        BaseData baseData1 = new LogonData(type, clientName);
        BaseData baseData2 = new LogonData(type, clientName);

        assertEquals(type, baseData1.getType(), "Type is not the same.");

        baseData1.setId(id);
        baseData2.setId(id);

        assertEquals(id, baseData1.getId(), "Id is not the same.");

        assertEquals(baseData1.hashCode(), baseData2.hashCode(), "HashCode is not the same.");
    }
}