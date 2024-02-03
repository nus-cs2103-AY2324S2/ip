package duke.exceptions;

/**
 * Superclass exception that handles all things Duke-related.
 */
public class DukeException extends Exception {
    /**
     * Constructs a <code>DukeException</code> of all exceptions Duke related.
     *
     * @param msg Exception message.
     */
    public DukeException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "________________________________________________\n"
                + super.getMessage()
                + "\n________________________________________________";
    }
}
