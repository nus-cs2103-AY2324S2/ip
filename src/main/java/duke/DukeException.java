package duke;

/**
 * Represents an exception specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Creates an exception with the provided error message.
     *
     * @param message The error message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getMessage() {
        return "ERROR: " + super.getMessage();
    }
}
