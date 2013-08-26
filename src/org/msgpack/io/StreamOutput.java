package org.msgpack.io;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 10:08
 */
import org.msgpack.utils.ByteBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;


public class StreamOutput implements Output {
    private DataOutputStream out;

    public StreamOutput(OutputStream out) {
        this.out = new DataOutputStream(out);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
    }

    public void write(ByteBuffer bb) throws IOException {
        if (bb.hasArray()) {
            byte[] array = bb.array();
            int offset = bb.arrayOffset();
            out.write(array, offset, bb.remaining());
            bb.position(bb.limit());
        } else {
            // int pos = bb.position();
            byte[] buf = new byte[bb.remaining()];
            bb.get(buf);
            out.write(buf);
        }
    }
    public void writeByte(byte v) throws IOException {
        out.write(v);
    }

    public void writeShort(short v) throws IOException {
        out.writeShort(v);
    }

    public void writeInt(int v) throws IOException {
        out.writeInt(v);
    }

    public void writeLong(long v) throws IOException {
        out.writeLong(v);
    }

    public void writeFloat(float v) throws IOException {
        out.writeFloat(v);
    }

    public void writeDouble(double v) throws IOException {
        out.writeDouble(v);
    }

    public void writeByteAndByte(byte b, byte v) throws IOException {
        out.write(b);
        out.write(v);
    }

    public void writeByteAndShort(byte b, short v) throws IOException {
        out.write(b);
        out.writeShort(v);
    }

    public void writeByteAndInt(byte b, int v) throws IOException {
        out.write(b);
        out.writeInt(v);
    }

    public void writeByteAndLong(byte b, long v) throws IOException {
        out.write(b);
        out.writeLong(v);
    }

    public void writeByteAndFloat(byte b, float v) throws IOException {
        out.write(b);
        out.writeFloat(v);
    }

    public void writeByteAndDouble(byte b, double v) throws IOException {
        out.write(b);
        out.writeDouble(v);
    }

    public void flush() throws IOException {
    }

    public void close() throws IOException {
        out.close();
    }
    
}
