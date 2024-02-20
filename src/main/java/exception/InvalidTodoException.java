package exception;

/**
 * The InvalidTodoException class represents an exception that is thrown
 * when attempting to create a task with an invalid or improperly formatted deadline.
 */
public class InvalidTodoException extends Exception {
    public InvalidTodoException(String message) {
        super(message);
    }
}
