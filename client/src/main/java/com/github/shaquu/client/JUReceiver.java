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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type JavaUdpReceiver.
 */
public class JUReceiver implements Runnable {
    private DatagramSocket socket;
    private byte buf[];
    private String clientName;

    private Logger logger = Logger.getLogger(JUReceiver.class.getName());

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
        logger.log(Level.INFO, "Starting receiver.");

        DatagramPacket packet;
        BaseData data;

        while (true) {
            try {
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                data = BaseData.getPacket(buf);
                handlePacket(data, packet);
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        }
    }


    private void handlePacket(BaseData data, DatagramPacket packet) {
        if (data instanceof LogonData) {
            LogonData logonData = (LogonData) data;
            String clientName = logonData.getClientName();

            logger.log(Level.INFO, clientName + " : GOOD");
        } else if (data instanceof MessageData) {
            MessageData messagePacket = (MessageData) data;

            if (this.clientName.equalsIgnoreCase(messagePacket.getClientName())) {
                return;
            }

            if (messagePacket.getType().equals(BaseData.Type.CONFIRM)) {
                return;
            }

            logger.log(Level.INFO, clientName + " : " + messagePacket.getMessage());
        }
    }
}