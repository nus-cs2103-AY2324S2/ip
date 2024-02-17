package sylvia.task;

import sylvia.SylviaException;

/**
 * Represents an exception thrown when the date and time is not in the correct
 * format.
 */
public class SylviaDateTimeParseException extends SylviaException {
    public SylviaDateTimeParseException(String message, String response) {
        super(message, response);
    }
}
