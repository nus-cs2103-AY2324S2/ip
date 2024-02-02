package duke.exception;

/**
 * Represents exceptions specific to duke and inherits from the Exception class.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the given error message.
     *
     * @param message Error message for the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}


