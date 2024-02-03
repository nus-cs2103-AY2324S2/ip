package exception;

import exception.ChronosException;

public class MissingTaskNumberException extends ChronosException {
    MissingTaskNumberException(String message) {
        super(message);
    }
}
