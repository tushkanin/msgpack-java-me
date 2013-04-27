//
// MessagePack for Java
//
// Copyright (C) 2009 - 2013 FURUHASHI Sadayuki
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//
package org.msgpack.unpacker;

import org.msgpack.MessageTypeException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

final class StringAccept extends Accept {
    String value;


    public StringAccept() {

    }


    void acceptRaw(byte[] raw) {
        try {
            this.value = new String(raw,"UTF-8");

    } catch (UnsupportedEncodingException e) {
            throw new MessageTypeException(e);
        }
    }


    void acceptEmptyRaw() {
        this.value = "";
    }


    public void refer(ByteBuffer bb, boolean gift) throws IOException {
        try {
            this.value = new String(bb.array(),"UTF-8");

        } catch (UnsupportedEncodingException e) {
            throw new MessageTypeException(e);
        }
    }
}
