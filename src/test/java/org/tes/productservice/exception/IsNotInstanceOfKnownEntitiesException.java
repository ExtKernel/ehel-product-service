package org.tes.productservice.exception;

public class IsNotInstanceOfKnownEntitiesException extends RuntimeException {
    public IsNotInstanceOfKnownEntitiesException(String message) {
        super(message);
    }

    public IsNotInstanceOfKnownEntitiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public IsNotInstanceOfKnownEntitiesException(Throwable cause) {
        super(cause);
    }
}
