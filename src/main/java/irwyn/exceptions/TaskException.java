package irwyn.exceptions;

/**
 * This class represents a task exception for Irwyn chatbot.
 */
public class TaskException extends InputException {
    /**
     * Constructor for the TaskException class.
     */
    public TaskException() {
        super(
                "Invalid task type\n"
                + "Please use either todo | event | deadline\n"
        );
    }
}
