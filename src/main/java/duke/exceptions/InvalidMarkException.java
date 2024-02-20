package duke.exceptions;

import duke.TextTemplate;

/**
 * The InvalidDateFormException class represents an exception that occurs when a task tries to be marked erroneously.
 * It is a subclass of the standard Java Exception class.
 */
public class InvalidMarkException extends Exception {

    /**
     * Constructs a new InvalidMarkException with a default error message.
     * The default error message is obtained from the TextTemplate class.
     */
    public InvalidMarkException() {
        super(TextTemplate.INVALID_MARK);
    }

    /**
     * Constructs a new InvalidMarkException with the specified error message.
     *
     * @param message The error message describing the specific cause of the exception.
     */
    public InvalidMarkException(String message) {
        super(message);
    }
}
