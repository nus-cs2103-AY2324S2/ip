package arona.exception;

/**
 * Represents an exception that might happen in the chatbot.
 *
 * @author Maximilliano Utomo
 */
public class AronaException extends Exception {
    public AronaException(String message) {
        super(message);
    }
}
