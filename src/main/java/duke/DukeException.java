package duke;

/**
 * An extension of the Exception class used to identify exceptions unique to Duke.
 */
public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super("Something went wrong...\n" + message);
    }
}
