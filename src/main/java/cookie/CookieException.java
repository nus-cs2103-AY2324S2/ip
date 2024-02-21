package cookie;


/**
 * Represents a custom exception class for Cookie application.
 * It extends the Exception class.
 */
public class CookieException extends Exception {
    /**
     * Constructs a CookieException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public CookieException(String message) {
        super(message);
    }
}
