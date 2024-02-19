package Kokbot;

/**
 * Represents a DukeException
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException
     */
    public DukeException() {
        super();
    }

    /**
     * Constructor for DukeException
     *
     * @param message Error message to be displayed
     */
    public DukeException(String message) {
        super(message);
    }
}
