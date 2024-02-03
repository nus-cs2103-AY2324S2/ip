package exception;

import exception.ChronosException;

public class MissingDescriptionException extends ChronosException {
    MissingDescriptionException(String message) {
        super(message);
    }
}
