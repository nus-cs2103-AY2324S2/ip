package exception;

/**
 * The InvalidEventException class represents an exception that is thrown
 * when attempting to create a task with an invalid or improperly formatted deadline.
 */
public class InvalidEventException extends Exception {
    public InvalidEventException(String message) {
        super(message);
    }
}
