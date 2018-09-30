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

        if (args.length >= 1) {
            JUPrefs.write("clientName", args[0], JUPrefs.Type.STRING);
        }

        if (args.length >= 2) {
            JUPrefs.write("ip", args[1], JUPrefs.Type.STRING);
        } else {
            JUPrefs.write("ip", JUUtils.getGlobalIp(), JUPrefs.Type.STRING);
        }

        DatagramSocket socket = new DatagramSocket();

        JUReceiver receiver = new JUReceiver(socket);
        JUSender sender = new JUSender(socket);

        Thread receiverThread = new Thread(receiver);
        Thread senderThread = new Thread(sender);

        receiverThread.start();
        senderThread.start();
    }
}
