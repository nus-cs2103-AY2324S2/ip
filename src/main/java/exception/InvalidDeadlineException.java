package exception;

/**
 * InvalidDeadlineException is a specific type of DukeException that represents an error
 * when an invalid deadline format is encountered in the Duke application.
 * It provides a predefined error message indicating the expected deadline input format.
 */
public class InvalidDeadlineException extends DukeException {

    /**
     * Constructs an InvalidDeadlineException with a predefined error message indicating
     * the expected deadline input format.
     */
    public InvalidDeadlineException() {
        super("Input in the form:\ndeadline Homework /by 8/10/2024 2000");
    }
}
