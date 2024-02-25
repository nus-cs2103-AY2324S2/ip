package bob.exception;

/**
 * Represents an error indicating that a parameter has not been found in the command given by the user.
 * A <code>ParameterNotFoundException</code> object corresponds to an error indicating
 * that an expected parameter is missing in the command given by the user.
 */
public class ParameterNotFoundException extends BobException {
    private static final String MESSAGE = "tell me what's your %s";

    /**
     * Returns an error indicating that a parameter has not been found in the command given by the user.
     *
     * @param parameter The parameter that has not been found.
     */
    public ParameterNotFoundException(String parameter) {
        super(String.format(MESSAGE, parameter));
    }

    /**
     * Returns an error indicating that a parameter has not been found in the command given by the user.
     *
     * @param parameters The possible expected parameters that has not been found.
     */
    public ParameterNotFoundException(String[] parameters) {
        super(String.format(MESSAGE, String.join(" or ", parameters)));
    }
}
