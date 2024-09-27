package lamball.exception;

import java.time.DateTimeException;

/**
 * Exception class for
 */
public class InvalidDateException extends DateTimeException {

    public InvalidDateException(String msg) {
        super(msg);
    }
}
