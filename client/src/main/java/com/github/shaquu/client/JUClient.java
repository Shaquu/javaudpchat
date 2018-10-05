/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.client;

import com.github.shaquu.shared.JUUtils;
import com.github.shaquu.shared.prefs.JUPrefs;

import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JavaUdpClient.
 */
public class JUClient {

    private static Logger logger = Logger.getLogger(JUClient.class.getName());

    /**
     * Main.
     *
     * @param args the args
     *
     * @throws Exception the exception
     */
    public static void main(String args[]) throws Exception {
        logger.log(Level.INFO, "Starting client.");

        new JUPrefs();
        new JUUtils();

        JUUtils.getPrefFromKeyboard("clientName", "Enter your username: ", 0, JUPrefs.Type.STRING, null);
        JUUtils.getPrefFromKeyboard("ip", "Enter server ip [leave empty for default]: ", 0, JUPrefs.Type.STRING, JUUtils.getGlobalIp());

        DatagramSocket socket = new DatagramSocket();

        JUReceiver receiver = new JUReceiver(socket);
        JUSender sender = new JUSender(socket);

        Thread receiverThread = new Thread(receiver);
        Thread senderThread = new Thread(sender);

        receiverThread.start();
        senderThread.start();
    }
}
