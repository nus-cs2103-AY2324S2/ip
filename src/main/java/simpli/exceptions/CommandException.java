package simpli.exceptions;

/**
 * Thrown when no such chatbot action exists.
 */
public class CommandException extends SimpliException {
    /**
     * Initializes the exception without a message.
     */
    public CommandException() {
        super();
    }

    /**
     * Initializes the exception with a message.
     *
     * @param errorMsg Error message String to be thrown.
     */
    public CommandException(String errorMsg) {
        super(errorMsg);
    }
}
