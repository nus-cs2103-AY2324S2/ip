package arc.exceptions.parser;

import arc.exceptions.ArcException;

/**
 * Represents an exception that is thrown when invalid arguments are detected
 * during the parsing of user commands. This exception is used to signal errors
 * such as missing required arguments, arguments with incorrect formats, or any
 * other condition where the provided arguments do not meet the command's requirements.
 * <p>
 * Extending {@link ArcException}, this class ensures that all argument-related errors
 * are handled uniformly, providing detailed and specific feedback about what went wrong
 * during command parsing. This helps in guiding users to correct their inputs and use
 * the application as intended.
 */
public class InvalidArgumentException extends ArcException {

    /**
     * Constructs a new InvalidArgumentException with a detailed message
     * indicating which argument(s) are invalid and why.
     *
     * @param argName The name of the argument(s) that were found to be invalid.
     * @param message A message describing why the argument(s) are invalid.
     */
    public InvalidArgumentException(String argName, String message) {
        super(String.format("Argument(s) '%s' %s.", argName, message));
    }

    /**
     * Constructs a new InvalidArgumentException with a detailed message
     * that includes an external error message, providing additional context.
     * This constructor is useful when the invalid argument exception is the
     * result of another exception, and that original error message needs to be
     * included for clarity.
     *
     * @param argName The name of the argument(s) that were found to be invalid.
     * @param message A message describing why the argument(s) are invalid.
     * @param externalErrMsg An additional message from another exception that
     *                       occurred during argument processing, providing more
     *                       detail about the error.
     */
    public InvalidArgumentException(String argName, String message, String externalErrMsg) {
        super(String.format("Argument(s) '%s' %s. %s", argName, message, externalErrMsg));
    }
}
