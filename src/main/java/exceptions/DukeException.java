package exceptions;

/**
 * Custom DukeException.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super("\t" + message);
    }
}
