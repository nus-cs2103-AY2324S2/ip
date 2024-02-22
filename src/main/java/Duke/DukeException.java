package duke;

/**
 * Serves as a base class for custom exceptions within the Duke application, providing a
 * consistent error handling mechanism.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with a customized error message.
     *
     * @param eString A descriptive error message explaining the exception.
     */
    public DukeException(String eString) {
        super("Exception: " + eString);
    }
}
