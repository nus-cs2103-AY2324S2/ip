package exception;

/**
 * Represents an exception that occurs when an invalid task index is provided to
 * the application.
 * <p>
 * This class extends {@link Exception} to provide detailed information about
 * the error
 * related to task index operations, such as accessing or modifying a task at an
 * index
 * that does not exist or is otherwise deemed invalid.
 * <p>
 * The purpose of this exception is to facilitate error handling specific to
 * task index
 * operations, enabling more precise feedback and actions to be taken when such
 * errors occur.
 * </p>
 */
public class InvalidTaskIndexException extends Exception {
    /**
     * Constructs a new {@code InvalidTaskIndexException} instance with the
     * specified error message.
     * 
     * @param message The error message to be associated with the exception.
     */
    public InvalidTaskIndexException(String message) {
        super(message);
    }

    /**
     * Returns the error message associated with the exception.
     * 
     * @return The error message associated with the exception.
     */
    public String getErrorMessage() {
        return super.getMessage();
    }
}
