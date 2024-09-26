package BotChat;

/**
 * Custom exception class for handling exceptions specific to the botChat application.
 */
public class BotChatException extends Exception {

    /**
     * Constructs a new botChatException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public BotChatException(String message) {
        super(message);
    }
}