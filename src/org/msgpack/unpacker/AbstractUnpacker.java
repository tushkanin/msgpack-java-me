package org.msgpack.unpacker;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 10:26
 */
import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class AbstractUnpacker implements Unpacker {

    protected int rawSizeLimit = 134217728;

    protected int arraySizeLimit = 4194304;

    protected int mapSizeLimit = 2097152;


    public ByteBuffer readByteBuffer() throws IOException {
        return ByteBuffer.wrap(readByteArray());
    }


    public void readArrayEnd() throws IOException {
        readArrayEnd(false);
    }


    public void readMapEnd() throws IOException {
        readMapEnd(false);
    }

    protected abstract boolean tryReadNil() throws IOException;

    public int getReadByteCount() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void resetReadByteCount() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void setRawSizeLimit(int size) {
        if (size < 32) {
            rawSizeLimit = 32;
        } else {
            rawSizeLimit = size;
        }
    }

    public void setArraySizeLimit(int size) {
        if (size < 16) {
            arraySizeLimit = 16;
        } else {
            arraySizeLimit = size;
        }
    }

    public void setMapSizeLimit(int size) {
        if (size < 16) {
            mapSizeLimit = 16;
        } else {
            mapSizeLimit = size;
        }
    }
}