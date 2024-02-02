package zack;

/**
 * Custom exception class for handling Zack-specific exceptions.
 */
public class ZackException extends Exception {

    /**
     * Constructs a new ZackException with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public ZackException(String message) {
        super(message);
    }
}
