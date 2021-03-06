package org.msgpack.packer;

import org.msgpack.MessageTypeException;
import org.msgpack.io.Output;
import org.msgpack.io.StreamOutput;
import org.msgpack.utils.ByteBuffer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 9:48
 */
public class Packer {
    protected final Output out;
    private PackerStack stack = new PackerStack();

    public Packer(OutputStream stream) {
        this(new StreamOutput(stream));
    }

    protected Packer(Output out) {
        this.out = out;
    }

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

    protected void writeByte(byte d) throws IOException {
        if (d < -(1 << 5)) {
            out.writeByteAndByte((byte) 0xd0, d);
        } else {
            out.writeByte(d);
        }
        stack.reduceCount();
    }

    protected void writeShort(short d) throws IOException {
        if (d < -(1 << 5)) {
            if (d < -(1 << 7)) {
                // signed 16
                out.writeByteAndShort((byte) 0xd1, d);
            } else {
                // signed 8
                out.writeByteAndByte((byte) 0xd0, (byte) d);
            }
        } else if (d < (1 << 7)) {
            // fixnum
            out.writeByte((byte) d);
        } else {
            if (d < (1 << 8)) {
                // unsigned 8
                out.writeByteAndByte((byte) 0xcc, (byte) d);
            } else {
                // unsigned 16
                out.writeByteAndShort((byte) 0xcd, d);
            }
        }
        stack.reduceCount();
    }

    protected void writeInt(int d) throws IOException {
        if (d < -(1 << 5)) {
            if (d < -(1 << 15)) {
                // signed 32
                out.writeByteAndInt((byte) 0xd2, d);
            } else if (d < -(1 << 7)) {
                // signed 16
                out.writeByteAndShort((byte) 0xd1, (short) d);
            } else {
                // signed 8
                out.writeByteAndByte((byte) 0xd0, (byte) d);
            }
        } else if (d < (1 << 7)) {
            // fixnum
            out.writeByte((byte) d);
        } else {
            if (d < (1 << 8)) {
                // unsigned 8
                out.writeByteAndByte((byte) 0xcc, (byte) d);
            } else if (d < (1 << 16)) {
                // unsigned 16
                out.writeByteAndShort((byte) 0xcd, (short) d);
            } else {
                // unsigned 32
                out.writeByteAndInt((byte) 0xce, d);
            }
        }
        stack.reduceCount();
    }

    protected void writeLong(long d) throws IOException {
        if (d < -(1L << 5)) {
            if (d < -(1L << 15)) {
                if (d < -(1L << 31)) {
                    // signed 64
                    out.writeByteAndLong((byte) 0xd3, d);
                } else {
                    // signed 32
                    out.writeByteAndInt((byte) 0xd2, (int) d);
                }
            } else {
                if (d < -(1 << 7)) {
                    // signed 16
                    out.writeByteAndShort((byte) 0xd1, (short) d);
                } else {
                    // signed 8
                    out.writeByteAndByte((byte) 0xd0, (byte) d);
                }
            }
        } else if (d < (1 << 7)) {
            // fixnum
            out.writeByte((byte) d);
        } else {
            if (d < (1L << 16)) {
                if (d < (1 << 8)) {
                    // unsigned 8
                    out.writeByteAndByte((byte) 0xcc, (byte) d);
                } else {
                    // unsigned 16
                    out.writeByteAndShort((byte) 0xcd, (short) d);
                }
            } else {
                if (d < (1L << 32)) {
                    // unsigned 32
                    out.writeByteAndInt((byte) 0xce, (int) d);
                } else {
                    // unsigned 64
                    out.writeByteAndLong((byte) 0xcf, d);
                }
            }
        }
        stack.reduceCount();
    }

    protected void writeFloat(float d) throws IOException {
        out.writeByteAndFloat((byte) 0xca, d);
        stack.reduceCount();
    }

    protected void writeDouble(double d) throws IOException {
        out.writeByteAndDouble((byte) 0xcb, d);
        stack.reduceCount();
    }

    protected void writeBoolean(boolean d) throws IOException {
        if (d) {
            // true
            out.writeByte((byte) 0xc3);
        } else {
            // false
            out.writeByte((byte) 0xc2);
        }
        stack.reduceCount();
    }

    protected void writeByteArray(byte[] b, int off, int len)
            throws IOException {
        if (len < 32) {
            out.writeByte((byte) (0xa0 | len));
        } else if (len < 65536) {
            out.writeByteAndShort((byte) 0xda, (short) len);
        } else {
            out.writeByteAndInt((byte) 0xdb, len);
        }
        out.write(b, off, len);
        stack.reduceCount();
    }

    protected void writeByteArray(byte[] b) throws IOException {
        writeByteArray(b, 0, b.length);
    }

    public Packer writeArrayEnd() throws IOException {
        writeArrayEnd(true);
        return this;
    }

    public Packer writeMapEnd() throws IOException {
        writeMapEnd(true);
        return this;
    }

    protected void writeByteBuffer(ByteBuffer bb) throws IOException {
        int len = bb.remaining();
        if (len < 32) {
            out.writeByte((byte) (0xa0 | len));
        } else if (len < 65536) {
            out.writeByteAndShort((byte) 0xda, (short) len);
        } else {
            out.writeByteAndInt((byte) 0xdb, len);
        }
        int pos = bb.position();
        try {
            out.write(bb);
        } finally {
            bb.position(pos);
        }
        stack.reduceCount();
    }

    protected void writeString(String s) throws IOException {
        byte[] b;
        try {
            // TODO encoding error?
            b = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new MessageTypeException(ex);
        }
        writeByteArray(b, 0, b.length);
        stack.reduceCount();
    }

    public Packer writeNil() throws IOException {
        out.writeByte((byte) 0xc0);
        stack.reduceCount();
        return this;
    }

    public Packer writeArrayBegin(int size) throws IOException {
        // TODO check size < 0?
        if (size < 16) {
            // FixArray
            out.writeByte((byte) (0x90 | size));
        } else if (size < 65536) {
            out.writeByteAndShort((byte) 0xdc, (short) size);
        } else {
            out.writeByteAndInt((byte) 0xdd, size);
        }
        stack.reduceCount();
        stack.pushArray(size);
        return this;
    }

    public Packer writeArrayEnd(boolean check) throws IOException {
        if (!stack.topIsArray()) {
            throw new MessageTypeException(
                    "writeArrayEnd() is called but writeArrayBegin() is not called");
        }

        int remain = stack.getTopCount();
        if (remain > 0) {
            if (check) {
                throw new MessageTypeException(
                        "writeArrayEnd(check=true) is called but the array is not end: " + remain);
            }
            for (int i = 0; i < remain; i++) {
                writeNil();
            }
        }
        stack.pop();
        return this;
    }

    public Packer writeMapBegin(int size) throws IOException {
        // TODO check size < 0?
        if (size < 16) {
            // FixMap
            out.writeByte((byte) (0x80 | size));
        } else if (size < 65536) {
            out.writeByteAndShort((byte) 0xde, (short) size);
        } else {
            out.writeByteAndInt((byte) 0xdf, size);
        }
        stack.reduceCount();
        stack.pushMap(size);
        return this;
    }

    public Packer writeMapEnd(boolean check) throws IOException {
        if (!stack.topIsMap()) {
            throw new MessageTypeException(
                    "writeMapEnd() is called but writeMapBegin() is not called");
        }

        int remain = stack.getTopCount();
        if (remain > 0) {
            if (check) {
                throw new MessageTypeException(
                        "writeMapEnd(check=true) is called but the map is not end: " + remain);
            }
            for (int i = 0; i < remain; i++) {
                writeNil();
            }
        }
        stack.pop();
        return this;
    }

    public void reset() {
        stack.clear();
    }

    public void flush() throws IOException {
        out.flush();
    }

    public void close() throws IOException {
        out.close();
    }
}
