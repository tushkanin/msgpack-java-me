package org.msgpack.packer;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 10:15
 */
import org.msgpack.MessageTypeException;

public final class PackerStack {
    private int top;
    private byte[] types;
    private int[] counts;

    public static final int MAX_STACK_SIZE = 128;

    private static final byte TYPE_INVALID = 0;
    private static final byte TYPE_ARRAY = 1;
    private static final byte TYPE_MAP = 2;

    public PackerStack() {
        this.top = 0;
        this.types = new byte[MAX_STACK_SIZE];
        this.counts = new int[MAX_STACK_SIZE];
        this.types[0] = TYPE_INVALID;
    }

    public void pushArray(int size) {
        top++;
        types[top] = TYPE_ARRAY;
        counts[top] = size;
    }

    public void pushMap(int size) {
        top++;
        types[top] = TYPE_MAP;
        counts[top] = size * 2;
    }

    public void checkCount() {
        if (counts[top] > 0) {
            return;
        }

        if (types[top] == TYPE_ARRAY) {
            throw new MessageTypeException(
                    "Array is end but writeArrayEnd() is not called");

        } else if (types[top] == TYPE_MAP) {
            throw new MessageTypeException(
                    "Map is end but writeMapEnd() is not called");

        } else {
            // empty
            return;
        }
    }

    public void reduceCount() {
        counts[top]--;
    }

    public void pop() {
        top--;
    }

    public int getDepth() {
        return top;
    }

    public int getTopCount() {
        return counts[top];
    }

    public boolean topIsArray() {
        return types[top] == TYPE_ARRAY;
    }

    public boolean topIsMap() {
        return types[top] == TYPE_MAP;
    }

    public void clear() {
        top = 0;
    }
}
