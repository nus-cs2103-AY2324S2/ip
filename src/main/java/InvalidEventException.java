package exception;

import exception.ChronosException;

public class InvalidEventException extends ChronosException {
    InvalidEventException(String message) {
        super(message);
    }
}
