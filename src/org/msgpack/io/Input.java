package org.msgpack.io;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 10:31
 */
import java.io.IOException;


public interface Input  {
    public int read(byte[] b, int off, int len) throws IOException;

    public boolean tryRefer(BufferReferer ref, int len) throws IOException;

    public byte readByte() throws IOException;

    public void advance();

    public byte getByte() throws IOException;

    public short getShort() throws IOException;

    public int getInt() throws IOException;

    public long getLong() throws IOException;

    public float getFloat() throws IOException;

    public double getDouble() throws IOException;

    public int getReadByteCount();

    public void resetReadByteCount();

    public void close() throws IOException ;
}
