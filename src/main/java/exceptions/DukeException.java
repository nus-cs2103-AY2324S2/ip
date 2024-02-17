package exceptions;

/**
 * Represents Exceptions local to this application.
 */
public class DukeException extends Exception {

    public DukeException() {
        super();
    }
    public DukeException(String message) {
        super(message);
    }
}
