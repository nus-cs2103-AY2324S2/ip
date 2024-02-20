package duke.command;

/**
 * Duke error handling class.
 */
public class DukeException extends Exception {
    private String errorMessage;

    /**
     * DukeException constructor.
     *
     * @param errorMessage  Indicates why exception was thrown.
     */
    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Return error message.
     *
     * @return errorMessage     Reason why exception was thrown.
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
