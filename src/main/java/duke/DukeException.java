package duke;

public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified detail message.
     * The detail message is for later by the getMessage() method.
     *
     * @param exception_text The detail message which provides more information about the exception reason.
     */
    public DukeException(String exception_text) {
        super(exception_text);
    }
}
