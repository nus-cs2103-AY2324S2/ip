package exception;

import exception.ChronosException;

public class InvalidListException extends ChronosException {
    InvalidListException(String message) {
        super(message);
    }
}
