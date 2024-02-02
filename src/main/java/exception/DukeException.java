package exception;

/**
 * DukeException are Exceptions specific to duke.Duke.
 */
public class DukeException extends Exception {
    String message;

    /**
     * The constructor of DukeException.
     */
    public DukeException() {
    }

    /**
     * Returns the exception message.
     *
     * @return The error message of the exception.
     */
    @Override
    public String getMessage() {
        return "OOPS!!! Something went wrong.";
    }
}
