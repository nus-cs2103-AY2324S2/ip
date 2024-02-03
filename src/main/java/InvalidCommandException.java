package exception;

import exception.ChronosException;

public class InvalidCommandException extends ChronosException {
    InvalidCommandException(String message) {
        super(message);
    }
}
