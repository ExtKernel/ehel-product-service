package org.tes.productservice.exception;

public class NullTokenException extends RuntimeException {
    public NullTokenException(String message) {
        super(message);
    }

    public NullTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullTokenException(Throwable cause) {
        super(cause);
    }
}
