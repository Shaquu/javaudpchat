/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:13 PM
 */

package com.github.shaquu.shared.packet;

import java.io.*;

/**
 * The type Base data.
 */
public abstract class BaseData implements Serializable {

    private Type type;

    /**
     * Instantiates a new Base data.
     *
     * @param type the type
     */
    public BaseData(Type type) {
        this.type = type;
    }

    /**
     * Gets packet.
     *
     * @param bytes the bytes
     *
     * @return the packet
     *
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static BaseData getPacket(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        ObjectInput in = new ObjectInputStream(bis);

        BaseData packet = (BaseData) in.readObject();

        in.close();

        return packet;
    }

    /**
     * Get bytes byte [ ].
     *
     * @param packet the packet
     *
     * @return the byte [ ]
     *
     * @throws IOException the io exception
     */
    public static byte[] getBytes(BaseData packet) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(packet);
        out.flush();

        byte[] bytes = bos.toByteArray();

        bos.close();

        return bytes;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "type=" + type +
                '}';
    }

    /**
     * The enum Type.
     */
    public enum Type {
        /**
         * Logon type.
         */
        LOGON,
        /**
         * Message type.
         */
        MESSAGE
    }
}
