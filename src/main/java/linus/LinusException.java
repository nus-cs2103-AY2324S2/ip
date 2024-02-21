package linus;

/**
 * Represents the exception class specific to Linus class.
 */
public class LinusException extends Exception {
    /**
     * Prints out the error message specified.
     *
     * @param message Error message to be printed.
     */
    public LinusException(String message) {
        super(message);
    }
}
