/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.client;

import com.github.shaquu.shared.packet.BaseData;
import com.github.shaquu.shared.packet.LogonData;
import com.github.shaquu.shared.packet.MessageData;
import com.github.shaquu.shared.prefs.JUPrefs;
import com.github.shaquu.shared.prefs.JUPrefsException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class JUReceiver implements Runnable {
    private DatagramSocket socket;
    private byte buf[];

    JUReceiver(DatagramSocket socket) throws JUPrefsException {
        this.socket = socket;

        int buffer = (int) JUPrefs.read("buffer", 1024, JUPrefs.Type.INT);
        this.buf = new byte[buffer];
    }

    public void run() {
        System.out.println("Starting receiver.");
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                BaseData data = BaseData.getPacket(buf);
                handlePacket(data, packet);
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }


    private void handlePacket (BaseData data, DatagramPacket packet) throws IOException {
        if (data instanceof LogonData) {
            LogonData logonData = (LogonData) data;
            String clientName = logonData.getClientName();

            System.out.println(clientName + " : GOOD");
        } else if (data instanceof MessageData) {
            MessageData messagePacket = (MessageData) data;
            String clientName = messagePacket.getClientName();

            System.out.println(clientName + " : " + messagePacket.getMessage());
        }
    }
}