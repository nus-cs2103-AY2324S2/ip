package duke;

/**
 * A custom throwable exception class that returns a specified error message.
 */

public class DukeException extends Exception {
    public DukeException(String err) {
        super(err);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
