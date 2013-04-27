package org.msgpack.io;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 10:38
 */
import java.io.IOException;
import java.nio.ByteBuffer;

public interface BufferReferer {
    public void refer(ByteBuffer bb, boolean gift) throws IOException;
}
