package bot;

/**
 * The DukeException class represents an exception that is thrown when an error
 * occurs in the Duke application.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException object.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}