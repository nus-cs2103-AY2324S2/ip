package sylvia.task;

import sylvia.SylviaException;

/**
 * Represents an exception thrown when the data file is not in the correct
 * format.
 */
public class InvalidDataFormatException extends SylviaException {
    public InvalidDataFormatException(String message, String botMessage) {
        super(message, botMessage);
    }
}
