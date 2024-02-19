package exceptions;

/**
 * Represents an exception specific to the TaskYapper application.
 * This class is used for handling exceptional scenarios that are unique to the TaskYapper application's workflow.
 */
public class TaskYapperException extends Exception {

    /**
     * Constructs a new TaskYapperException with the specified detail message.
     * The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     *
     * @param errorMessage the detail message. The detail message is saved for later retrieval by the
     *                     {@link Throwable#getMessage()} method.
     */
    public TaskYapperException(String errormessage) {
        super(errormessage);
    }
}
