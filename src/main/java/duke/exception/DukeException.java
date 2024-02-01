package duke.exception;

public class DukeException extends Exception {
    /**
     * Constructor of DukeException.
     *
     * @param errorMessage
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
