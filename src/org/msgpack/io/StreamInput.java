package org.msgpack.io;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 10:41
 */
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.io.EOFException;

public class StreamInput extends AbstractInput {
    private final InputStream in;

    private byte[] castBuffer;
    private ByteBuffer castByteBuffer;
    private int filled;

    public StreamInput(InputStream in) {
        this.in = in;
        this.castBuffer = new byte[8];
        this.castByteBuffer = ByteBuffer.wrap(castBuffer);
        this.filled = 0;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int remain = len;
        while (remain > 0) {
            int n = in.read(b, off, remain);
            if (n <= 0) {
                throw new EOFException();
            }
            incrReadByteCount(n);
            remain -= n;
            off += n;
        }
        return len;
    }

    public boolean tryRefer(BufferReferer ref, int size) throws IOException {
        return false;
    }

    public byte readByte() throws IOException {
        int n = in.read();
        if (n < 0) {
            throw new EOFException();
        }
        incrReadOneByteCount();
        return (byte) n;
    }

    public void advance() {
        incrReadByteCount(filled);
        filled = 0;
    }

    private void require(int len) throws IOException {
        while (filled < len) {
            int n = in.read(castBuffer, filled, len - filled);
            if (n < 0) {
                throw new EOFException();
            }
            filled += n;
        }
    }

    public byte getByte() throws IOException {
        require(1);
        return castBuffer[0];
    }

    public short getShort() throws IOException {
        require(2);
        return castByteBuffer.getShort(0);
    }

    public int getInt() throws IOException {
        require(4);
        return castByteBuffer.getInt(0);
    }

    public long getLong() throws IOException {
        require(8);
        return castByteBuffer.getInt(0);
    }

    public float getFloat() throws IOException {
        require(4);
        return castByteBuffer.getFloat(0);
    }

    public double getDouble() throws IOException {
        require(8);
        return castByteBuffer.getFloat(0);
    }

    public void close() throws IOException {
        in.close();
    }
}
