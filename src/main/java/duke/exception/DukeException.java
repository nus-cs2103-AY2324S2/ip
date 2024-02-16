package duke.exception;

/**
 * Represents exceptions specific to Duke application. This exception is thrown when the
 * application encounters an issue specific to its logic, such as invalid user input.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

}
