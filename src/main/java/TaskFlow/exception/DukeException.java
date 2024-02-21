package TaskFlow.exception;

/**
 * Represents an exception specific to the Duke chatbot application.
 * It is thrown to indicate that there is an error or exceptional condition in the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified detail message.
     *
     * @param message The error message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
