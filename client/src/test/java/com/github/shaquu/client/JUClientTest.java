/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/9/18 5:45 PM
 */

/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/9/18 5:43 PM
 */

package com.github.shaquu.client;

import com.github.shaquu.shared.JUUtils;
import com.github.shaquu.shared.JUUtilsException;
import com.github.shaquu.shared.prefs.JUPrefs;
import com.github.shaquu.shared.prefs.JUPrefsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * The type JUClientTest.
 */
class JUClientTest {

    private final static Logger logger = Logger.getLogger(JUClientTest.class.getName());

    @BeforeAll
    static void setUp() {
        try {
            new JUPrefs();
        } catch (JUPrefsException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Constructor test.
     */
    @Test
    void constructor() {
        logger.log(Level.INFO, "Starting test constructor");

        JUClient client = new JUClient();

        assertNotNull(client);

        try {
            writeClientInput();
            JUClient.main(new String[]{});
            removeClientInput();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void getPrefFromKeyboard() {
        writeClientInput();
        try {
            JUUtils.getPrefFromKeyboard("clientName", "Enter your username: ", 0, JUPrefs.Type.STRING, null);
            JUUtils.getPrefFromKeyboard("ip", "Enter server ip [leave empty for default]: ", 0, JUPrefs.Type.STRING, JUUtils.getGlobalIp());
        } catch (JUPrefsException | JUUtilsException e) {
            fail(e.getMessage());
        }
        removeClientInput();
    }

    private void writeClientInput() {
        try {
            JUPrefs.write("clientName", "clientName", JUPrefs.Type.STRING);
            JUPrefs.write("ip", "127.0.0.1", JUPrefs.Type.STRING);
        } catch (JUPrefsException e) {
            fail(e.getMessage());
        }
    }

    private void removeClientInput() {
        try {
            JUPrefs.remove("clientName");
            JUPrefs.remove("ip");
        } catch (JUPrefsException e) {
            fail(e.getMessage());
        }
    }

}