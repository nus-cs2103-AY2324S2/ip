package duke.utility;

/**
 * Class that represents an Exception that is specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException Object.
     *
     * @param message String containing the exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}
