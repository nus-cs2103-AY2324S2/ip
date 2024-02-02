package exceptions;

/**
 * Represents a general exception class for the KaiYap application.
 * This class serves as a base for more specific exception types that might be encountered in the application.
 */
public class KaiYapException extends Exception {
    public KaiYapException(String errorMsg) {
        super(errorMsg);
    }
}
