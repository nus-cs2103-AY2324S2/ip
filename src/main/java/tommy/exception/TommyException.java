package tommy.exception;

/**
 * Abstract class that is the parent class of all exceptions created in the program.
 */
public abstract class TommyException extends Exception {

    /**
     * Constructor for an TommyException,
     * which initialises the exception with its error message.
     *
     * @param message Description of the TommyException error.
     */
    public TommyException(String message) {
        super(message);
    }

    /**
     * Returns the descriptive message of the exception error for the user.
     *
     * @return Descriptive error message.
     */
    public abstract String getErrorMessage();
}
