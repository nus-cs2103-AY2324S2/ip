package yapper;

/**
 * Custom exception class for Yapper-specific exceptions.
 */
public class YapperException extends Exception {

    /**
     * Constructs a new YapperException with the specified detail message.
     *
     * @param message The detail message.
     */
    public YapperException(String message) {
        super(message);
    }
}
