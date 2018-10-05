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

/**
 * The type Client manager.
 */
public class ClientManager implements Iterable<ClientData> {
    private HashSet<String> ids;
    private ArrayList<ClientData> clients;

    /**
     * Instantiates a new Client manager.
     */
    ClientManager() {
        clients = new ArrayList<>();
        ids = new HashSet<>();
    }

    /**
     * Gets long id.
     *
     * @param address the address
     * @param port    the port
     *
     * @return the long id
     */
    protected String getLongId(InetAddress address, Integer port) {
        String clientName = getClientName(address, port);
        return clientName + " [" + address.toString() + ":" + port + "]";
    }

    /**
     * Gets short id.
     *
     * @param address the address
     * @param port    the port
     *
     * @return the short id
     */
    protected String getShortId(InetAddress address, Integer port) {
        return address.toString() + ":" + port;
    }

    /**
     * Gets client name.
     *
     * @param address the address
     * @param port    the port
     *
     * @return the client name
     */
    protected String getClientName(InetAddress address, Integer port) {
        String shortId = getShortId(address, port);
        return get(shortId).getName();
    }

    /**
     * Add string.
     *
     * @param address    the address
     * @param port       the port
     * @param clientName the client name
     *
     * @return the string
     */
    protected String add(InetAddress address, Integer port, String clientName) {
        String shortId = getShortId(address, port);

        if (!ids.contains(shortId)) {
            ClientData client = new ClientData(shortId, address, port, clientName);
            clients.add(client);
            ids.add(shortId);
        }

        return shortId;
    }

    /**
     * Get client data.
     *
     * @param shortId the short id
     *
     * @return the client data
     */
    protected ClientData get(String shortId) {
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
        private final int end;
        private int cursor;

        /**
         * Instantiates a new Client manager iterator.
         */
        ClientManagerIterator() {
            this.cursor = 0;
            this.end = clients.size();
        }

        public boolean hasNext() {
            return this.cursor < end;
        }

        public ClientData next() {
            if (this.hasNext()) {
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
