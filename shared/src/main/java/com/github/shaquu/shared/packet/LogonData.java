/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:14 PM
 */

package com.github.shaquu.shared.packet;

public class LogonData extends BaseData {

    private String clientName;

    public LogonData(String clientName) {
        super(Type.LOGON);
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }
}
