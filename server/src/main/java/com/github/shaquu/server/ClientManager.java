/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:06 PM
 */

package com.github.shaquu.server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ClientManager implements Iterable<ClientData> {
    private HashSet<String> ids;
    private ArrayList<ClientData> clients;

    ClientManager() {
        clients = new ArrayList<>();
        ids = new HashSet<>();
    }

    String getLongId(InetAddress address, Integer port) {
        String clientName = getClientName(address, port);
        return clientName + " [" + address.toString() + ":" + port + "]";
    }

    String getShortId(InetAddress address, Integer port) {
        return address.toString() + ":" + port;
    }

    String getClientName(InetAddress address, Integer port) {
        String shortId = getShortId(address, port);
        return get(shortId).getName();
    }

    String add(InetAddress address, Integer port, String clientName) {
        String shortId = getShortId(address, port);

        if (!ids.contains(shortId)) {
            ClientData client = new ClientData(shortId, address, port, clientName);
            clients.add(client);
            ids.add(shortId);
        }

        return shortId;
    }

    ClientData get(String shortId) {
        for (ClientData clientData : clients) {
            if (clientData.getId().equalsIgnoreCase(shortId)) {
                return clientData;
            }
        }
        return null;
    }

    @Override
    public Iterator<ClientData> iterator() {
        return new ClientManagerIterator();
    }

    private class ClientManagerIterator implements Iterator {
        private int cursor;
        private final int end;

        ClientManagerIterator() {
            this.cursor = 0;
            this.end = clients.size();
        }

        public boolean hasNext() {
            return this.cursor < end;
        }

        public ClientData next() {
            if(this.hasNext()) {
                ClientData current = clients.get(cursor);
                cursor++;
                return current;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
