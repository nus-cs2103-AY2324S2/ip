package pookie;

/**
 * Represents an exception specific to pookie.
 */
public class PookieException extends Exception {

    /**
     * Constructor for PookieException.
     * @param message The message to be displayed when the exception is thrown.
     */
    public PookieException(String message) {
        super(message);
    }
}
