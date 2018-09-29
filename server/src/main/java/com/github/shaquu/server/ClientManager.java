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

    String add(InetAddress address, Integer port) {
        String id = address.toString() + ":" + port;

        if (!ids.contains(id)) {
            ClientData client = new ClientData(id, address, port);
            clients.add(client);
        }

        return id;
    }

    @Override
    public Iterator<ClientData> iterator() {
        return new ClientManagerIterator(clients.size());
    }

    private class ClientManagerIterator implements Iterator {
        private int cursor;
        private final int end;

        ClientManagerIterator(int end) {
            this.cursor = 0;
            this.end = end;
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
