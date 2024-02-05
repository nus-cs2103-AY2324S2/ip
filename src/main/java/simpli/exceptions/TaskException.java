package simpli.exceptions;

/**
 * Thrown when tasks have invalid parameters.
 */
public class TaskException extends ActionException {
    /**
     * Initializes the exception without a message.
     */
    public TaskException() {
        super();
    }

    /**
     * Initializes the exception with a message.
     *
     * @param errorMsg Error message String to be thrown.
     */
    public TaskException(String errorMsg) {
        super(errorMsg);
    }
}
