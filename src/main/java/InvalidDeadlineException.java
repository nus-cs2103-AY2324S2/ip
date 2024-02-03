package exception;

import exception.ChronosException;

public class InvalidDeadlineException extends ChronosException {
    InvalidDeadlineException(String message) {
        super(message);
    }
}
