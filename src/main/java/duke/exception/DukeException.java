package duke.exception;

/**
 * DukeException class.
 */
public class DukeException extends Exception {
    /**
     * Constructor of DukeException.
     *
     * @param errorMessage
     */
    public DukeException(String errorMessage) {
        super("Error: " + errorMessage);
    }
}
