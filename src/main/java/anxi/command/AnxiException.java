package anxi.command;

/**
 * Duke error handling class.
 */
public class AnxiException extends Exception {
    private String errorMessage;

    /**
     * DukeException constructor.
     *
     * @param errorMessage  Indicates why exception was thrown.
     */
    public AnxiException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns error message.
     *
     * @return errorMessage     Reason why exception was thrown.
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
