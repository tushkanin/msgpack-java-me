package org.msgpack;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 11:18
 */

import org.msgpack.packer.Packer;

import org.msgpack.unpacker.Unpacker;

import java.io.InputStream;
import java.io.OutputStream;

public class MessagePack {

    public MessagePack() {

    }

    public Packer createPacker(OutputStream out) {
        return new Packer(out);
    }


    public Unpacker createUnpacker(InputStream in) {
        return new Unpacker(in);
    }


}