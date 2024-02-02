package duke;

/**
 * Signals an error caused by each operation where there should be none.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}