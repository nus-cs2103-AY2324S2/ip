package lia;

/**
 * The LiaException class is a custom exception class for handling exceptions specific to the Lia application.
 */
public class LiaException extends Exception {
    /**
     * Constructs a LiaException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public LiaException(String message) {
        super(message);
    }
}
