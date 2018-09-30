/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.server;

import com.github.shaquu.shared.JUUtils;
import com.github.shaquu.shared.packet.BaseData;
import com.github.shaquu.shared.packet.LogonData;
import com.github.shaquu.shared.packet.MessageData;
import com.github.shaquu.shared.prefs.JUPrefs;
import com.github.shaquu.shared.prefs.JUPrefsException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * The type JavaUdpServer.
 */
public class JUServer extends Thread {
    private static int port;

    private static DatagramSocket socket;

    private static ClientManager clientManager;

    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    /**
     * Instantiates a new JavaUdpServer.
     *
     * @throws JUPrefsException the JavaUdpPrefs exception
     * @throws SocketException  the socket exception
     */
    public JUServer() throws JUPrefsException, SocketException {
        System.out.println("Starting server.");
        new JUPrefs();

        port = (int) JUPrefs.read("port", 12345, JUPrefs.Type.INT);

        socket = new DatagramSocket(port);

        clientManager = new ClientManager();
    }

    /**
     * Main.
     *
     * @param args the args
     *
     * @throws Exception the exception
     */
    public static void main(String args[]) throws Exception {
        new JUServer().start();
    }

    public void run() {
        System.out.println("Listening...");
        byte[] buf = new byte[JUUtils.BUFFER_SIZE];
        while (true) {
            try {
                Arrays.fill(buf, (byte) 0);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                BaseData data = BaseData.getPacket(buf);

                handlePacket(data, packet);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    private void handlePacket(BaseData data, DatagramPacket packet) throws IOException {
        if (data instanceof LogonData) {
            LogonData logonData = (LogonData) data;
            String shortId = clientManager.add(packet.getAddress(), packet.getPort(), logonData.getClientName());
            String longId = clientManager.getLongId(packet.getAddress(), packet.getPort());

            System.out.println(longId + " : " + logonData);

            LogonData logonDataResponse = new LogonData(BaseData.Type.CONFIRM, "SERWER");
            byte[] bytes = BaseData.getBytes(logonDataResponse);

            ClientData client = clientManager.get(shortId);

            sendBytes(client, bytes);
        } else if (data instanceof MessageData) {
            MessageData messageData = (MessageData) data;
            String shortId = clientManager.getShortId(packet.getAddress(), packet.getPort());

            if (clientManager.get(shortId) == null) {
                System.out.println("UNREGISTERED USER");
                System.out.println(shortId + " : " + messageData);
            } else {
                String longId = clientManager.getLongId(packet.getAddress(), packet.getPort());
                System.out.println(longId + " : " + messageData);
                byte[] bytes = BaseData.getBytes(messageData);

                for (ClientData client : clientManager) {
                    if (!client.getId().equalsIgnoreCase(shortId)) {
                        sendBytes(client, bytes);
                    } else {
                        MessageData messageDataResponse = new MessageData(BaseData.Type.CONFIRM, "SERWER", "GOOD");
                        byte[] bytesResponse = BaseData.getBytes(messageDataResponse);
                        sendBytes(client, bytes);
                    }
                }
            }
        }
    }

    private void sendBytes(ClientData client, byte[] bytes) throws IOException {
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, client.getAddress(), client.getPort());
        socket.send(packet);
    }
}
