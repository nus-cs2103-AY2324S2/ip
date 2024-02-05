package badgpt.exceptions;

/**
 * General class of exceptions associated with the chatbot.
 */
public class BadException extends Exception {

    /**
     * Creates a new BadException with the specified message.
     *
     * @param message The error message.
     */
    public BadException(String message) {
        super(message);
    }
}
