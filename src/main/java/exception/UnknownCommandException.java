package exception;

/**
 * The UnknownCommandException class represents an exception that is thrown
 * when attempting to create a task with an invalid or improperly formatted deadline.
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }
}
