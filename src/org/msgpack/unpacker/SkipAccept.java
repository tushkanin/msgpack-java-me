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

import org.msgpack.utils.ByteBuffer;
import java.io.IOException;


final class SkipAccept extends Accept {

    void acceptBoolean(boolean v) {
    }


    void acceptInteger(byte v) {
    }


    void acceptInteger(short v) {
    }


    void acceptInteger(int v) {
    }


    void acceptInteger(long v) {
    }


    void acceptUnsignedInteger(byte v) {
    }


    void acceptUnsignedInteger(short v) {
    }


    void acceptUnsignedInteger(int v) {
    }


    void acceptUnsignedInteger(long v) {
    }


    void acceptRaw(byte[] raw) {
    }


    void acceptEmptyRaw() {
    }


    public void refer(ByteBuffer bb, boolean gift) throws IOException {
    }


    void acceptArray(int size) {
    }


    void acceptMap(int size) {
    }


    void acceptNil() {
    }


    void acceptFloat(float v) {
    }


    void acceptDouble(double v) {
    }
}
