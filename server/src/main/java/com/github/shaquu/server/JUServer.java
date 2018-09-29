/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.server;

import com.github.shaquu.shared.prefs.JUPrefs;
import com.github.shaquu.shared.prefs.JUPrefsException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class JUServer extends Thread {
    private static int port;
    private static int buffer;

    private static DatagramSocket socket;

    private static ClientManager clientManager;

    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    public static void main(String args[]) throws Exception {
        new JUServer().start();
    }

    public JUServer() throws JUPrefsException, SocketException {
        System.out.println("Starting server.");
        new JUPrefs();

        port = (int) JUPrefs.read("port", 12345, JUPrefs.Type.INT);
        buffer = (int) JUPrefs.read("buffer", 1024, JUPrefs.Type.INT);

        socket = new DatagramSocket(port);

        clientManager = new ClientManager();
    }

    public void run() {
        System.out.println("Listening...");
        byte[] buf = new byte[buffer];
        while (true) {
            try {
                Arrays.fill(buf, (byte)0);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String content = new String(buf, UTF8_CHARSET);

                String id = clientManager.add(packet.getAddress(), packet.getPort());

                System.out.println(id + " : " + content);
                byte[] data = (id + " : " +  content).getBytes();

                for (ClientData client : clientManager) {
                    packet = new DatagramPacket(data, data.length, client.getAddress(), client.getPort());
                    socket.send(packet);
                }
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }
}
