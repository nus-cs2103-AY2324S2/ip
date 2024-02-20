package exception;

/**
 * The InvalidDeadlineException class represents an exception that is thrown
 * when attempting to create a task with an invalid or improperly formatted deadline.
 */
public class InvalidDeadlineException extends Exception {
    public InvalidDeadlineException(String message) {
        super(message);
    }
}
