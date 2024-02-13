package seedu.duke;

/**
 * Represents <code>Exceptions</code> corresponding to
 * Duke commands from user input.
 */
public class DukeException extends Exception {
    /**
     * Constructor for Duke-related exceptions.
     * @param e Represents the error message
     */
    public DukeException(String e) {
        super(e);
    }
}
