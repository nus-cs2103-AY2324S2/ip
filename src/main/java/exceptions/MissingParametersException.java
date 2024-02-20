package exceptions;

/**
 * This class signals that the command entered by the user is missing
 * one or more parameters required.
 */
public class MissingParametersException extends Exception {
    /**
     * @param message should contain relevant information on failed constraints
     */
    public MissingParametersException(String message) {
        super(message);
    }
}
