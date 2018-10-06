/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:15 PM
 */

package com.github.shaquu.shared.packet;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageData)) return false;
        if (!super.equals(o)) return false;
        MessageData that = (MessageData) o;
        return Objects.equals(clientName, that.clientName) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientName, message);
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
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
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
