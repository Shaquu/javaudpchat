/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:15 PM
 */

package com.github.shaquu.shared.packet;

public class MessageData extends BaseData {

    private String clientName;
    private String message;

    public MessageData(String clientName, String message) {
        super(Type.MESSAGE);

        this.clientName = clientName;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getClientName() {
        return clientName;
    }
}
