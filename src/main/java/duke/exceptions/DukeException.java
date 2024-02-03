package duke.exceptions;

/**
 * Throws Duke Exception for invalid command.
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

}