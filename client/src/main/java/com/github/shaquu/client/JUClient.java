/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.client;

import com.github.shaquu.shared.JUUtils;
import com.github.shaquu.shared.prefs.JUPrefs;

import java.net.DatagramSocket;

/**
 * JavaUdpClient.
 */
public class JUClient {

    /**
     * Main.
     *
     * @param args the args
     *
     * @throws Exception the exception
     */
    public static void main(String args[]) throws Exception {
        System.out.println("Starting client.");
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
