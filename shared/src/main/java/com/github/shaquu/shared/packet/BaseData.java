/*
 *  @author  shaq
 *  @version 1.0
 *  @since   9/29/18 8:13 PM
 */

package com.github.shaquu.shared.packet;

import java.io.*;

public abstract class BaseData implements Serializable {

    private Type type;

    public BaseData(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public static BaseData getPacket(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        ObjectInput in = new ObjectInputStream(bis);

        BaseData packet = (BaseData) in.readObject();

        if (in != null) {
            in.close();
        }

        return packet;
    }

    public static byte[] getBytes(BaseData packet) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(packet);
        out.flush();

        byte[] bytes = bos.toByteArray();

        bos.close();

        return bytes;
    }

    public enum Type {
        LOGON, MESSAGE
    }
}
