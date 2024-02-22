package exceptions;

/**
 * Represents an exception that is thrown when a required input is missing in the KaiYap application.
 * This exception is typically thrown in scenarios where a user command lacks essential information
 * or parameters that are necessary for the command's execution.
 */
public class MissingInputException extends KaiYapException {
    public MissingInputException(String errorMsg) {
        super(errorMsg);
    }
}
