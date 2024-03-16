package ellie.exception;

/**
 * Exception thrown when an invalid input is encountered while processing tasks.
 */
public class InvalidTaskInputException extends Exception {
    public InvalidTaskInputException(String message) {
        super(message);
    }

}
