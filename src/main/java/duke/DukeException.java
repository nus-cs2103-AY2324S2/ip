package duke;

/**
 * Handles all the exception during the execution of the program
 */
public class DukeException extends Exception {
    private final String errorMessage;

    /**
     * Constructs a new DukeException.
     *
     * @param errorMessage The error message
     */
    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Prints the error message.
     *
     * @return errorMessage
     */
    @Override
    public String toString() {
        return errorMessage;
    }
}
