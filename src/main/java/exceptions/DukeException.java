package exceptions;

/**
 * Represents an exception specific to the Duke application.
 * This class is used for handling exceptional scenarios that are unique to the Duke application's workflow.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified detail message.
     * The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     *
     * @param errorMessage the detail message. The detail message is saved for later retrieval by the
     *                     {@link Throwable#getMessage()} method.
     */
    public DukeException(String errormessage) {
        super(errormessage);
    }
}
