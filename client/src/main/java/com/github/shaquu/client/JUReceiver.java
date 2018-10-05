/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.client;

import com.github.shaquu.shared.JUUtils;
import com.github.shaquu.shared.packet.BaseData;
import com.github.shaquu.shared.packet.LogonData;
import com.github.shaquu.shared.packet.MessageData;
import com.github.shaquu.shared.prefs.JUPrefs;
import com.github.shaquu.shared.prefs.JUPrefsException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * The type JavaUdpReceiver.
 */
public class JUReceiver implements Runnable {
    private DatagramSocket socket;
    private byte buf[];
    private String clientName;

    /**
     * Instantiates a new JavaUdpReceiver.
     *
     * @param socket the socket
     *
     * @throws JUPrefsException the ju prefs exception
     */
    JUReceiver(DatagramSocket socket) throws JUPrefsException {
        this.socket = socket;
        this.buf = new byte[JUUtils.BUFFER_SIZE];

        this.clientName = (String) JUPrefs.read("clientName", "", JUPrefs.Type.STRING);
    }

    public void run() {
        System.out.println("Starting receiver.");
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                BaseData data = BaseData.getPacket(buf);
                handlePacket(data, packet);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }


    private void handlePacket(BaseData data, DatagramPacket packet) {
        if (data instanceof LogonData) {
            LogonData logonData = (LogonData) data;
            String clientName = logonData.getClientName();

            System.out.println(clientName + " : GOOD");
        } else if (data instanceof MessageData) {
            MessageData messagePacket = (MessageData) data;

            if (this.clientName.equalsIgnoreCase(messagePacket.getClientName())) {
                return;
            }

            if (messagePacket.getType().equals(BaseData.Type.CONFIRM)) {
                return;
            }

            System.out.println(clientName + " : " + messagePacket.getMessage());
        }
    }
}