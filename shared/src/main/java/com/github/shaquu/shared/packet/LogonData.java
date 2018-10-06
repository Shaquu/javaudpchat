/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:14 PM
 */

package com.github.shaquu.shared.packet;

import java.util.Objects;

/**
 * LogonData used for logging to the server for the first time in session.
 */
public class LogonData extends BaseData {

    private String clientName;

    /**
     * Instantiates a new LogonData.
     *
     * @param type       the type
     * @param clientName the client name
     */
    public LogonData(Type type, String clientName) {
        super(type);
        this.clientName = clientName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogonData)) return false;
        if (!super.equals(o)) return false;
        LogonData logonData = (LogonData) o;
        return Objects.equals(clientName, logonData.clientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientName);
    }

    @Override
    public String toString() {
        return "LogonData{" +
                "clientName='" + clientName + '\'' +
                '}';
    }

    /**
     * Gets client name.
     *
     * @return the client name
     */
    public String getClientName() {
        return clientName;
    }

}
