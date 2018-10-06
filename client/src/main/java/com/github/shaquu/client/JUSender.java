/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.client;

import com.github.shaquu.shared.JUUtils;
import com.github.shaquu.shared.JUUtilsException;
import com.github.shaquu.shared.packet.BaseData;
import com.github.shaquu.shared.packet.LogonData;
import com.github.shaquu.shared.packet.MessageData;
import com.github.shaquu.shared.prefs.JUPrefs;
import com.github.shaquu.shared.prefs.JUPrefsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type JavaUdpSender.
 */
public class JUSender implements Runnable {
    private InetAddress address;
    private int port;

    private DatagramSocket socket;

    private boolean connected = false;

    private Logger logger = Logger.getLogger(JUSender.class.getName());

    /**
     * Instantiates a new JavaUdpSender.
     *
     * @param socket the socket
     *
     * @throws JUPrefsException     the JavaUdpPrefs exception
     * @throws UnknownHostException the unknown host exception
     * @throws JUUtilsException     the JavaUdpUtils exception
     */
    JUSender(DatagramSocket socket) throws JUPrefsException, UnknownHostException, JUUtilsException {
        this.socket = socket;

        String ip = (String) JUPrefs.read("ip", JUUtils.getGlobalIp(), JUPrefs.Type.STRING);
        this.address = InetAddress.getByName(ip);

        this.port = (int) JUPrefs.read("port", 12345, JUPrefs.Type.INT);
    }

    private boolean sendLogOn(String clientName) throws Exception {
        logger.log(Level.INFO, "Trying to LogOn : " + address.getHostAddress());
        LogonData logonData = new LogonData(BaseData.Type.REQUEST, clientName);
        byte[] bytes = BaseData.getBytes(logonData);
        sendBytes(bytes);
        return true;
    }

    private void sendMessage(String clientName, String message) throws Exception {
        MessageData messageData = new MessageData(BaseData.Type.REQUEST, clientName, "");

        if (message.length() > JUUtils.BUFFER_SIZE) {
            for (String m : JUUtils.splitMessage(message, JUUtils.BUFFER_SIZE)) {
                messageData.setMessage(m);
                sendData(messageData);
            }
        } else {
            messageData.setMessage(message);
            sendData(messageData);
        }
    }

    public void run() {
        logger.log(Level.INFO, "Starting sender.");

        String clientName;

        try {
            clientName = (String) JUPrefs.read("clientName", null, JUPrefs.Type.STRING);
        } catch (JUPrefsException e) {
            logger.log(Level.WARNING, e.getMessage());
            return;
        }

        while (!connected) {
            try {
                connected = sendLogOn(clientName);
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String message;

        while (true) {
            try {
                while (!in.ready()) {
                    Thread.sleep(100);
                }
                message = in.readLine();

                if (message.trim().length() <= 0) {
                    message = null;
                }

                if (message != null) {
                    sendMessage(clientName, message);
                }
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
    }

    private void sendData(BaseData data) throws IOException {
        byte[] bytes = BaseData.getBytes(data);
        sendBytes(bytes);
    }

    private void sendBytes(byte[] bytes) throws IOException {
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
        socket.send(packet);
    }
}
