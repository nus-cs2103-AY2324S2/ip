package dino;

/**
 * Custom exception class for the Dino chatbot.
 * This exception is thrown to indicate errors specific to the Dino chatbot.
 */
public class DinoException extends Exception {

    /**
     * Constructs a new DinoException with the specified message.
     *
     * @param message The detail message.
     */
    public DinoException(String message) {
        super(message);
    }

}
