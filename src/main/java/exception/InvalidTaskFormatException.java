package exception;

/**
 * Represents an exception that is thrown when an attempt to create
 * a task fails due to an incorrectly formatted input string.
 * <p>
 * This class extends the {@link Exception} class and is used to indicate errors
 * encountered due to incorrect formatting of task input or data. It provides a
 * constructor to create exception instances with a custom message that can
 * describe
 * the specific formatting issue encountered.
 * </p>
 */
public class InvalidTaskFormatException extends Exception {
    /**
     * Constructs a new {@code InvalidTaskFormatException} instance with the
     * specified
     * error message.
     * 
     * @param message The error message to be associated with the exception.
     */
    public InvalidTaskFormatException(String message) {
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
