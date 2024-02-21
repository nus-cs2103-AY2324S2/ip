package capone.exceptions;

/**
 * Custom exception class for handling task-related exceptions.
 * Extends the Java built-in Exception class.
 *
 * @author Tay Rui-Jie
 */
public abstract class CaponeException extends Exception {
    /**
     * Constructs a new CaponeException with the specified error message.
     *
     * @param errorMessage The error message associated with the exception.
     */
    public CaponeException(String errorMessage) {
        super(errorMessage);
    }
}
