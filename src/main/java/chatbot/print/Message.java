package chatbot.print;

/**
 * This represents a message that is sent from the chatbot to the UI.
 *
 * @author Titus Chew
 */
public class Message {
    /** The message that is stored. */
    private final String message;
    /** The status of the message that is stored. */
    private final Status status;

    /** The status of the message. */
    public enum Status {
        DEFAULT,
        LOG,
        ERROR,
    }

    /**
     * Private constructor for this.
     *
     * @param message The message to store.
     * @param status The status of the message to store.
     */
    private Message(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    /**
     * Creates a message.
     *
     * @param message The message to store.
     */
    public static Message createMessage(String message) {
        return new Message(message, Status.DEFAULT);
    }

    /**
     * Creates an error message.
     *
     * @param message The message to store.
     */
    public static Message createErrorMessage(String message) {
        return new Message(message, Status.ERROR);
    }

    /**
     * Creates a log message.
     *
     * @param message The message to store.
     */
    public static Message createlogMessage(String message) {
        return new Message(message, Status.LOG);
    }

    /**
     * Gets the stored message.
     */
    @Override
    public String toString() {
        return message;
    }

    public Status getStatus() {
        return status;
    }
}
