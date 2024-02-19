package exception;

/**
 * Represents an exception that is thrown to indicate that an operation
 * involving date validation has failed due to an invalid date format or value.
 * <p>
 * This class extends the {@link Exception} class, allowing
 * {@code InvalidDateException} to be caught and handled specifically for cases
 * related to date validation failures.
 * </p>
 */
public class InvalidDateException extends Exception {
    /**
     * Constructs a new {@code InvalidDateException} instance with the specified
     * error message.
     * 
     * @param message The error message to be associated with the exception.
     */
    public InvalidDateException(String message) {
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
