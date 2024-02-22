package exception;

/**
 * InvalidCommandException are Exceptions when user's input
 * is invalid.
 */
public class InvalidCommandException extends DukeException {
    private String message;

    /**
     * The constructor of InvalidCommandException.
     *
     * @param message The error message.
     */
    public InvalidCommandException(String message) {
        this.message = message;
    }

    /**
     * Returns the exception message.
     *
     * @return The error message of the exception.
     */
    @Override
    public String getMessage() {
        return "\nbuzz buzz~~ Something went wrong D:\n";
    }
}
