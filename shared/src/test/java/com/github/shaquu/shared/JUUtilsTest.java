/*
 *  @author  shaq
 *  @version 1.0
 *  @since   10/6/18 8:31 PM
 */

package com.github.shaquu.shared;

import com.github.shaquu.shared.packet.BaseData;
import com.github.shaquu.shared.packet.LogonData;
import com.github.shaquu.shared.packet.MessageData;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

class JUUtilsTest {

    private final static Logger logger = Logger.getLogger(JUUtilsTest.class.getName());

    @org.junit.jupiter.api.BeforeAll
    static void setUp() {
        new JUUtils();
    }

    @org.junit.jupiter.api.Test
    void getGlobalIp() {
        logger.log(Level.INFO, "Starting test getGlobalIp");

        try {
            final String globalIp = JUUtils.getGlobalIp();
            Assertions.assertEquals("104.248.142.11", globalIp, "Global IP is not correct.");
        } catch (JUUtilsException e) {
            logger.log(Level.WARNING, e.getMessage());
            Assertions.fail("Failed while getting global IP");
        }
    }

    @org.junit.jupiter.api.Test
    void compressLogonData() {
        logger.log(Level.INFO, "Starting test compressLogonData");

        LogonData logonData = new LogonData(BaseData.Type.REQUEST, "test");

        try {
            byte[] logonBytes = JUUtils.compress(BaseData.getBytes(logonData));
            LogonData logonObject = (LogonData) BaseData.getData(JUUtils.decompress(logonBytes));
            Assertions.assertEquals(logonData, logonObject, "Object is not the same.");
        } catch (IOException | ClassNotFoundException | DataFormatException e) {
            logger.log(Level.WARNING, e.getMessage());
            Assertions.fail("Failed while compressing logon data");
        }
    }

    @org.junit.jupiter.api.Test
    void compressMessageData() {
        logger.log(Level.INFO, "Starting test compressMessageData");

        MessageData messageData = new MessageData(BaseData.Type.REQUEST, "test", "message");

        try {
            byte[] messageBytes = JUUtils.compress(BaseData.getBytes(messageData));
            MessageData messageObject = (MessageData) BaseData.getData(JUUtils.decompress(messageBytes));
            Assertions.assertEquals(messageData, messageObject, "Object is not the same.");
        } catch (IOException | ClassNotFoundException | DataFormatException e) {
            logger.log(Level.WARNING, e.getMessage());
            Assertions.fail("Failed while compressing message data");
        }
    }

    @org.junit.jupiter.api.Test
    void splitMessage() {
        logger.log(Level.INFO, "Starting test splitMessage");

        final String msg = "test1test2test3";
        List<String> msgSplittedTest = new ArrayList<>();
        msgSplittedTest.add("test1");
        msgSplittedTest.add("test2");
        msgSplittedTest.add("test3");

        List<String> msgSplitted = JUUtils.splitMessage(msg, 5);

        Assertions.assertEquals(msgSplittedTest.size(), msgSplitted.size(), "Message splitted to bad size");

        for (int i = 0; i < msgSplitted.size(); i++) {
            Assertions.assertEquals(msgSplittedTest.get(i), msgSplitted.get(i), "Splitted message do not match");
        }
    }
}