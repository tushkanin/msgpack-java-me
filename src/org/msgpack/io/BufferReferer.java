package org.msgpack.io;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 10:38
 */
import org.msgpack.utils.ByteBuffer;
import java.io.IOException;


public interface BufferReferer {
    public void refer(ByteBuffer bb, boolean gift) throws IOException;
}
