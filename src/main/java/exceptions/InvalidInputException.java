package exceptions;

/**
 * Represents an exception that is thrown when the input provided to the KaiYap application
 * is invalid. This could occur when the input format is incorrect or does not match expected patterns.
 */
public class InvalidInputException extends KaiYapException {
    public InvalidInputException(String errorMsg) {
        super(errorMsg);
    }
}
