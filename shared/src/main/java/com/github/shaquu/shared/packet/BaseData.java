/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:13 PM
 */

package com.github.shaquu.shared.packet;

import com.github.shaquu.shared.JUUtils;

import java.io.*;
import java.util.zip.DataFormatException;

/**
 * The type Base data.
 */
public abstract class BaseData implements Serializable {

    private static long globalId = 0;
    private Type type;
    private long id;

    /**
     * Instantiates a new Base data.
     *
     * @param type the type
     */
    public BaseData(Type type) {
        this.type = type;
        id = globalId++;
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
     * @throws DataFormatException    the data format exception
     */
    public static BaseData getPacket(byte[] bytes) throws IOException, ClassNotFoundException, DataFormatException {
        byte[] uncompressed = JUUtils.decompress(bytes);

        ByteArrayInputStream bis = new ByteArrayInputStream(uncompressed);

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

        return JUUtils.compress(bytes);
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
                ", id=" + id +
                '}';
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * The enum Type.
     */
    public enum Type {
        /**
         * Request type.
         */
        REQUEST,
        /**
         * Confirm type.
         */
        CONFIRM
    }
}
