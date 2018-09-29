package com.github.shaquu.client;

import com.github.shaquu.shared.prefs.JUPrefs;
import com.github.shaquu.shared.prefs.JUPrefsException;

import java.io.BufferedReader;
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

    JUSender(DatagramSocket socket) throws JUPrefsException, UnknownHostException {
        this.socket = socket;

        String ip = (String) JUPrefs.read("ip", "", JUPrefs.Type.STRING);
        this.address = InetAddress.getByName(ip);

        this.port = (int) JUPrefs.read("port", 12345, JUPrefs.Type.INT);
    }

    private void sendMessage(String message) throws Exception {
        byte buf[] = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);
    }

    public void run() {
        System.out.println("Starting sender.");
        do {
            try {
                sendMessage("Hello");
                connected = true;
            } catch (Exception e) {
                System.err.println(e);
            }
        } while (!connected);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                while (!in.ready()) {
                    Thread.sleep(100);
                }
                sendMessage(in.readLine());
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
