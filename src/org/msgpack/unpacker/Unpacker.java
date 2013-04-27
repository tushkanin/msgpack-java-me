package org.msgpack.unpacker;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 10:25
 */
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Standard deserializer.
 *
 * @version 0.6.0
 */
public interface Unpacker  {

    public void skip() throws IOException;

    public int readArrayBegin() throws IOException;

    public void readArrayEnd(boolean check) throws IOException;

    public void readArrayEnd() throws IOException;

    public int readMapBegin() throws IOException;

    public void readMapEnd(boolean check) throws IOException;

    public void readMapEnd() throws IOException;

    public void readNil() throws IOException;

    public boolean trySkipNil() throws IOException;

    public boolean readBoolean() throws IOException;

    public byte readByte() throws IOException;

    public short readShort() throws IOException;

    public int readInt() throws IOException;

    public long readLong() throws IOException;

    public float readFloat() throws IOException;

    public double readDouble() throws IOException;

    public byte[] readByteArray() throws IOException;

    public ByteBuffer readByteBuffer() throws IOException;

    public String readString() throws IOException;

    public int getReadByteCount();

    public void resetReadByteCount();

    public void setRawSizeLimit(int size);

    public void setArraySizeLimit(int size);

    public void setMapSizeLimit(int size);

    public void close() throws IOException ;
}
