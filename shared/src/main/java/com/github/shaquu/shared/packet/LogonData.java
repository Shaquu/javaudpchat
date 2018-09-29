/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:14 PM
 */

package com.github.shaquu.shared.packet;

/**
 * The type Logon data.
 */
public class LogonData extends BaseData {

    private String clientName;

    /**
     * Instantiates a new Logon data.
     *
     * @param clientName the client name
     */
    public LogonData(String clientName) {
        super(Type.LOGON);
        this.clientName = clientName;
    }

    /**
     * Gets client name.
     *
     * @return the client name
     */
    public String getClientName() {
        return clientName;
    }

    @Override
    public String toString() {
        return "LogonData{" +
                "clientName='" + clientName + '\'' +
                '}';
    }
}
