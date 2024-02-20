package toothless;

/**
 * Represents an exception specific to the Toothless chatbot.
 */
public class ToothlessException extends Exception {

    /**
     * Constructs a new ToothlessException with the specified detail message.
     * @param message The detail message.
     */
    public ToothlessException(String message) {
        super(message);
    }
}
