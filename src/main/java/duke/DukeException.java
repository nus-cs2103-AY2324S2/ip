package duke;

/**
 * Class that represents a Duke-specific exception
 */
public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }
}
