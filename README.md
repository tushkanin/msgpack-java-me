# MessagePack for Java ME

An implementation of [MessagePack](http://msgpack.org/) for Java ME.

## Example

### Pack

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Packer packer = new MessagePack().createPacker(out);
    packer.writeArrayBegin(4);
    packer.write(1);
    packer.write("Hello world");
    packer.write(true);
    packer.write(1.2f);
    packer.writeArrayEnd();
    packer.flush();
    byte[] bytes = out.toByteArray();
      
### Unpack

    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
    Unpacker unpacker = new MessagePack().createUnpacker(in);
    unpacker.readArrayBegin();           
    int a = unpacker.readInt();
    String b = unpacker.readString();
    boolean c = unpacker.readBool();
    float d = unpacker.readFloat();
    unpacker.readArrayEnd();
    unpacker.close();
