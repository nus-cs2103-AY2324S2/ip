package exception;

/**
 * InvalidInputException are Exceptions when user's input
 * is not valid.
 */
public class InvalidInputException extends DukeException {
    private String message;

    /**
     * The constructor of InvalidInputException.
     *
     * @param message The error message.
     */
    public InvalidInputException(String message) {
        this.message = message;
    }

    /**
     * Returns the exception message.
     *
     * @return The error message of the exception.
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}
