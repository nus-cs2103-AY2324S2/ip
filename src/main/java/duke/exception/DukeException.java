package duke.exception;

/**
 * The `DukeException` class represents an exception specific to the Duke application.
 * It extends the general `Exception` class to handle custom exceptions within Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a `DukeException` with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
