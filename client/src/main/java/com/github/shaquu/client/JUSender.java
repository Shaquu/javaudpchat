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

public class JUSender implements Runnable {
    private InetAddress address;
    private int port;

    private DatagramSocket socket;

    private boolean connected = false;

    JUSender(DatagramSocket socket) throws JUPrefsException, UnknownHostException, JUUtilsException {
        this.socket = socket;

        String ip = (String) JUPrefs.read("ip", JUUtils.getGlobalIp(), JUPrefs.Type.STRING);
        this.address = InetAddress.getByName(ip);

        this.port = (int) JUPrefs.read("port", 12345, JUPrefs.Type.INT);
    }

    private boolean sendLogOn(String clientName) throws Exception {
        LogonData logonData = new LogonData(clientName);
        byte[] bytes = BaseData.getBytes(logonData);
        sendBytes(bytes);
        return true;
    }

    private void sendMessage(String clientName, String message) throws Exception {
        MessageData messagePacket = new MessageData(clientName, message);
        byte[] bytes = BaseData.getBytes(messagePacket);
        sendBytes(bytes);
    }

    public void run() {
        System.out.println("Starting sender.");


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String clientName = null;

        try {
            clientName = (String) JUPrefs.read("clientName", null, JUPrefs.Type.STRING);
        } catch (JUPrefsException e) {
            e.printStackTrace();
        }

        while (!connected) {
            try {
                if (clientName == null) {
                    System.out.println("Your name: ");
                    clientName = in.readLine();
                }
                connected = sendLogOn(clientName);
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        while (true) {
            try {
                while (!in.ready()) {
                    Thread.sleep(100);
                }
                sendMessage(clientName, in.readLine());
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    private void sendBytes(byte[] bytes) throws IOException {
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
        socket.send(packet);
    }
}
