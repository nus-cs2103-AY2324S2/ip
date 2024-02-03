package duke.exceptions;

import duke.TextTemplate;

/**
 * The InvalidInputException class represents an exception that occurs when the user provides an invalid command
 * or input in the Duke application.
 * It is a subclass of the standard Java Exception class.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs a new InvalidInputException with a default error message.
     * The default error message is obtained from the TextTemplate class.
     */
    public InvalidInputException() {
        super(TextTemplate.INVALID_COMMAND);
    }

    /**
     * Constructs a new InvalidInputException with the specified error message.
     *
     * @param message The error message describing the specific cause of the exception.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
