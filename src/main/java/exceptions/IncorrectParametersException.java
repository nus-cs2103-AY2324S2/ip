package exceptions;

/**
 * This class signals that the command entered by the user is not a
 * valid command.
 */
public class IncorrectParametersException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraints
     */
    public IncorrectParametersException(String message) {
        super(message);
    }
}
