package duke.exception;

/**
 * Abstract class that is the parent class of all exceptions created in the program.
 */
public abstract class DukeException extends Exception {
    /**
     * Constructor for an DukeException,
     * which initialises the exception with its error message.
     *
     * @param message Description of the DukeException error.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Prints the descriptive message of the exception error for the user.
     *
     * @return Descriptive error message.
     */
    public abstract String errorMessage();
}
