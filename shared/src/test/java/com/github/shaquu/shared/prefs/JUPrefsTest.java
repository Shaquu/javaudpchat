/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/7/18 9:56 PM
 */

package com.github.shaquu.shared.prefs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type JUPrefsTest.
 */
class JUPrefsTest {

    private final static Logger logger = Logger.getLogger(JUPrefsTest.class.getName());

    /**
     * Sets up.
     */
    @BeforeAll
    static void setUp() {
        logger.log(Level.INFO, "Starting setUp");

        try {
            JUPrefs prefs = new JUPrefs();
            assertNotNull(prefs);
        } catch (JUPrefsException e) {
            fail("Failed on initializing preferences.");
        }
    }

    /**
     * Write exists read remove.
     */
    @Test
    void writeExistsReadRemove() {
        logger.log(Level.INFO, "Starting test writeExistsReadRemove");
        try {
            addAllTypes();
            int existsCount1 = existsAllTypes();
            assertEquals(7, existsCount1, "Not all types created.");

            assertEquals("string", JUPrefs.read("string", "empty", JUPrefs.Type.STRING));
            assertEquals(1L, JUPrefs.read("long", 2L, JUPrefs.Type.LONG));
            assertEquals(1, JUPrefs.read("int", 2, JUPrefs.Type.INT));
            assertEquals(true, JUPrefs.read("boolean", false, JUPrefs.Type.BOOLEAN));
            assertEquals(Arrays.toString(new byte[]{0, 1}), Arrays.toString((byte[]) JUPrefs.read("byte_arr", new byte[]{1, 0}, JUPrefs.Type.BYTE_ARRAY)));
            assertEquals(1.01D, JUPrefs.read("double", 2.02D, JUPrefs.Type.DOUBLE));
            assertEquals(1.02F, JUPrefs.read("float", 2.01F, JUPrefs.Type.FLOAT));

            removeAllTypes();
            int existsCount2 = existsAllTypes();
            assertEquals(0, existsCount2, "Not all types removed.");

            assertNotEquals("string", JUPrefs.read("string", "empty", JUPrefs.Type.STRING));
            assertNotEquals(1L, JUPrefs.read("long", 2L, JUPrefs.Type.LONG));
            assertNotEquals(1, JUPrefs.read("int", 2, JUPrefs.Type.INT));
            assertNotEquals(true, JUPrefs.read("boolean", false, JUPrefs.Type.BOOLEAN));
            assertNotEquals(Arrays.toString(new byte[]{0, 1}), Arrays.toString((byte[]) JUPrefs.read("byte_arr", new byte[]{1, 0}, JUPrefs.Type.BYTE_ARRAY)));
            assertNotEquals(1.01D, JUPrefs.read("double", 2.02D, JUPrefs.Type.DOUBLE));
            assertNotEquals(1.02F, JUPrefs.read("float", 2.01F, JUPrefs.Type.FLOAT));
        } catch (JUPrefsException e) {
            fail(e.getMessage());
        }
    }

    private void addAllTypes() throws JUPrefsException {
        JUPrefs.write("string", "string", JUPrefs.Type.STRING);
        JUPrefs.write("long", 1L, JUPrefs.Type.LONG);
        JUPrefs.write("int", 1, JUPrefs.Type.INT);
        JUPrefs.write("boolean", true, JUPrefs.Type.BOOLEAN);
        JUPrefs.write("byte_arr", new byte[]{0, 1}, JUPrefs.Type.BYTE_ARRAY);
        JUPrefs.write("double", 1.01D, JUPrefs.Type.DOUBLE);
        JUPrefs.write("float", 1.02F, JUPrefs.Type.FLOAT);
    }

    private void removeAllTypes() throws JUPrefsException {
        JUPrefs.remove("string");
        JUPrefs.remove("long");
        JUPrefs.remove("int");
        JUPrefs.remove("boolean");
        JUPrefs.remove("byte_arr");
        JUPrefs.remove("double");
        JUPrefs.remove("float");
    }

    private int existsAllTypes() {
        int existsCount = 0;

        if (JUPrefs.exist("string"))
            existsCount++;

        if (JUPrefs.exist("long"))
            existsCount++;

        if (JUPrefs.exist("int"))
            existsCount++;

        if (JUPrefs.exist("boolean"))
            existsCount++;

        if (JUPrefs.exist("byte_arr"))
            existsCount++;

        if (JUPrefs.exist("double"))
            existsCount++;

        if (JUPrefs.exist("float"))
            existsCount++;

        return existsCount;
    }
}