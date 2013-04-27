package org.msgpack.io;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 10:40
 */
abstract class AbstractInput implements Input {

    private int readByteCount = 0;

    public int getReadByteCount() {
        return readByteCount;
    }

    public void resetReadByteCount() {
        readByteCount = 0;
    }

    protected final void incrReadByteCount(int size) {
        readByteCount += size;
    }

    protected final void incrReadOneByteCount() {
        readByteCount += 1;
    }
}
