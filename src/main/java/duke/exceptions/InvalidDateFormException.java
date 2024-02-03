package duke.exceptions;

import duke.TextTemplate;

/**
 * The InvalidDateFormException class represents an exception that occurs when a date or time provided
 * by the user is in an invalid format in the Duke application.
 * It is a subclass of the standard Java Exception class.
 */
public class InvalidDateFormException extends Exception {

    /**
     * Constructs a new InvalidDateFormException with a default error message.
     * The default error message is obtained from the TextTemplate class.
     */
    public InvalidDateFormException() {
        super(TextTemplate.INVALID_DATETIME);
    }

    /**
     * Constructs a new InvalidDateFormException with the specified error message.
     *
     * @param message The error message describing the specific cause of the exception.
     */
    public InvalidDateFormException(String message) {
        super(message);
    }
}
