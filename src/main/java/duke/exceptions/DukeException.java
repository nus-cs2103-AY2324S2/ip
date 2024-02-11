package duke.exceptions;

/**
 * Custom exception class for Duke application.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with no error message.
     */
    public DukeException() {
        super() ;
    }

    /**
     * Constructs a new DukeException with a custom error message.
     *
     * @param errorMessage The detail error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
