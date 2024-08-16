package sylvia;

/**
 * Represents an exception thrown by the chatbot.
 */
public class SylviaException extends Exception {
    private String botMessage;

    /**
     * Constructs a new exception with the given message and bot message.
     *
     * @param message    The message of the exception.
     * @param botMessage The message to be shown to the user.
     */
    public SylviaException(String message, String botMessage) {
        super(message);
        this.botMessage = botMessage;
    }

    public String getBotMessage() {
        return botMessage;
    }
}
