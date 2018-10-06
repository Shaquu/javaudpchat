/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:15 PM
 */

package com.github.shaquu.shared.packet;

/**
 * MessageData used for sending message on the chat.
 */
public class MessageData extends BaseData {

    private String clientName;
    private String message;

    /**
     * Instantiates a new MessageData.
     *
     * @param type       the type
     * @param clientName the client name
     * @param message    the message
     */
    public MessageData(Type type, String clientName, String message) {
        super(type);

        this.clientName = clientName;
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageData{" +
                "clientName='" + clientName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets client name.
     *
     * @return the client name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
