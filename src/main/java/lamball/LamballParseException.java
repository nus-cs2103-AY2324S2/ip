package lamball;

import java.time.format.DateTimeParseException;

public class LamballParseException extends Exception {
    /**
     * Constructor for Lamball parse exception.
     *
     * @param message Error message.
     */
    public LamballParseException(String message) {
        super(message);
    }
}
