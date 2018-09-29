package com.github.shaquu.client;

import com.github.shaquu.shared.prefs.JUPrefs;
import com.github.shaquu.shared.prefs.JUPrefsException;

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
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }
}