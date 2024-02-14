package bob.exception;

/**
 * Represents an error specific to Bob. A <code>BobException</code> object corresponds to
 * an error that is specific to the program.
 */
public class BobException extends Exception {
    /**
     * Returns an error specific to Bob with a specified message.
     *
     * @param message The message for the error specific to Bob.
     */
    public BobException(String message) {
        super(message);
    }
}
