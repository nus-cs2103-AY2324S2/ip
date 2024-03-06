package paimon;

/**
 * Represents a custom exception type for handling chat errors.
 */
public class ChatException extends Exception {
    public ChatException(String error) {
        super(error);
    }
}
