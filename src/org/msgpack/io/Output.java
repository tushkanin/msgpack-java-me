package org.msgpack.io;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 10:07
 */
import java.io.IOException;
import java.nio.ByteBuffer;

public interface Output {
    public void write(byte[] b, int off, int len) throws IOException;

    public void write(ByteBuffer bb) throws IOException;

    public void writeByte(byte v) throws IOException;

    public void writeShort(short v) throws IOException;

    public void writeInt(int v) throws IOException;

    public void writeLong(long v) throws IOException;

    public void writeFloat(float v) throws IOException;

    public void writeDouble(double v) throws IOException;

    public void writeByteAndByte(byte b, byte v) throws IOException;

    public void writeByteAndShort(byte b, short v) throws IOException;

    public void writeByteAndInt(byte b, int v) throws IOException;

    public void writeByteAndLong(byte b, long v) throws IOException;

    public void writeByteAndFloat(byte b, float v) throws IOException;

    public void writeByteAndDouble(byte b, double v) throws IOException;

    public void flush() throws IOException ;

    public void close() throws IOException ;
}
