package duke;


/**
 * Represents a custom exception class for Duke application.
 * It extends the Exception class.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public DukeException(String message) {
        super(message);
    }
}
