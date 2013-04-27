package org.msgpack.packer;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 9:53
 */
import java.io.IOException;
import java.nio.ByteBuffer;


public abstract class AbstractPacker implements Packer {


    public Packer write(boolean o) throws IOException {
        writeBoolean(o);
        return this;
    }


    public Packer write(byte o) throws IOException {
        writeByte(o);
        return this;
    }


    public Packer write(short o) throws IOException {
        writeShort(o);
        return this;
    }


    public Packer write(int o) throws IOException {
        writeInt(o);
        return this;
    }


    public Packer write(long o) throws IOException {
        writeLong(o);
        return this;
    }


    public Packer write(float o) throws IOException {
        writeFloat(o);
        return this;
    }


    public Packer write(double o) throws IOException {
        writeDouble(o);
        return this;
    }


    public Packer write(byte[] o) throws IOException {
        if (o == null) {
            writeNil();
        } else {
            writeByteArray(o);
        }
        return this;
    }

    public Packer write(byte[] o, int off, int len) throws IOException {
        if (o == null) {
            writeNil();
        } else {
            writeByteArray(o, off, len);
        }
        return this;
    }


    public Packer write(ByteBuffer o) throws IOException {
        if (o == null) {
            writeNil();
        } else {
            writeByteBuffer(o);
        }
        return this;
    }


    public Packer write(String o) throws IOException {
        if (o == null) {
            writeNil();
        } else {
            writeString(o);
        }
        return this;
    }



    public Packer writeArrayEnd() throws IOException {
        writeArrayEnd(true);
        return this;
    }


    public Packer writeMapEnd() throws IOException {
        writeMapEnd(true);
        return this;
    }


    public void close() throws IOException {
    }

    abstract protected void writeBoolean(boolean v) throws IOException;

    abstract protected void writeByte(byte v) throws IOException;

    abstract protected void writeShort(short v) throws IOException;

    abstract protected void writeInt(int v) throws IOException;

    abstract protected void writeLong(long v) throws IOException;

    abstract protected void writeFloat(float v) throws IOException;

    abstract protected void writeDouble(double v) throws IOException;

    protected void writeByteArray(byte[] b) throws IOException {
        writeByteArray(b, 0, b.length);
    }

    abstract protected void writeByteArray(byte[] b, int off, int len) throws IOException;

    abstract protected void writeByteBuffer(ByteBuffer bb) throws IOException;

    abstract protected void writeString(String s) throws IOException;
}
