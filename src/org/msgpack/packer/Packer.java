package org.msgpack.packer;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 9:48
 */
public interface Packer {
    public Packer write(boolean o) throws IOException;

    public Packer write(byte o) throws IOException;

    public Packer write(short o) throws IOException;

    public Packer write(int o) throws IOException;

    public Packer write(long o) throws IOException;

    public Packer write(float o) throws IOException;

    public Packer write(double o) throws IOException;

    public Packer write(byte[] o) throws IOException;

    public Packer write(byte[] o, int off, int len) throws IOException;

    public Packer write(ByteBuffer o) throws IOException;

    public Packer write(String o) throws IOException;

    public Packer writeNil() throws IOException;

    public Packer writeArrayBegin(int size) throws IOException;

    public Packer writeArrayEnd(boolean check) throws IOException;

    public Packer writeArrayEnd() throws IOException;

    public Packer writeMapBegin(int size) throws IOException;

    public Packer writeMapEnd(boolean check) throws IOException;

    public Packer writeMapEnd() throws IOException;

    public void flush() throws IOException ;

    public void close() throws IOException ;
}
