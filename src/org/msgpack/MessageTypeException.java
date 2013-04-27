package org.msgpack;

/**
 * User: Aleksey.Shulga
 * Date: 27.04.13
 * Time: 10:14
 */
public class MessageTypeException extends RuntimeException {
    public MessageTypeException() {
        super();
    }

    public MessageTypeException(String message) {
        super(message);
    }

    public MessageTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageTypeException(Throwable cause) {
        super(cause);
    }
}
