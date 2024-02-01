package duke.exceptions;

/**
 * The `ProcessingException` class represents an exception related to processing tasks or data in the Duke application.
 */
public class ProcessingException extends HalException {
    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
