package shuheng.exceptions;

/**
 * This class represents the error thrown when an invalid input is detected.
 */
public class InvalidTaskException extends InvalidInputException {
    /**
     * The constructor for the exception.
     */
    public InvalidTaskException() {
        super("That's an invalid task type! "
            + "Try choosing one from the following: todo | event | deadline");
    }
}
