package exceptions;

/**
 * Handles errors generated when added tasks.
 */
public class InvalidTaskNameException extends Exception {
    /**
     * Constructor with error message.
     *
     * @param errorMessage to be printed on console.
     */
    public InvalidTaskNameException(String errorMessage) {
        super(errorMessage);
    }
}
