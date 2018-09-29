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

    String add(InetAddress address, Integer port) {
        String id = address.toString() + ":" + port;

        if (!ids.contains(id)) {
            ClientData client = new ClientData(id, address, port);
            clients.add(client);
            ids.add(id);
        }

        return id;
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
