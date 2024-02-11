package duke;

/**
 * Represents an exception specific to the Duke application.
 */
public class DukeException extends Exception{

    /**
     * Constructs a DukeException with the specified message.
     *
     * @param message The detail message.
     */
    public DukeException(String message) {
        super(message);
    }
}
