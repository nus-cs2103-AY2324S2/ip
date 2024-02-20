package chatbot.print;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This encapsulates interactions with the user by printing to the console. (System.out)
 *
 * @author Titus Chew
 */
public final class PrintFormatter {
    /** Stores the message strings to print. */
    private static final Queue<Message> FORMATTER_QUEUE = new LinkedList<>();

    /**
     * Formats multiple strings into a single message.
     */
    private static Message formatMessageStrings(String[] strings) {
        StringBuilder formattedMessage = new StringBuilder();

        for (String s : strings) {
            formattedMessage.append(s);
            formattedMessage.append("\n\n");
        }

        return Message.createMessage(formattedMessage.toString().trim());
    }

    /**
     * Gets multiple lines of messages from a queue.
     * The messages stored in the queue would be cleared.
     */
    public static Message[] getAllMessages() {
        Message[] messages = new Message[FORMATTER_QUEUE.size()];

        for (int i = 0; !FORMATTER_QUEUE.isEmpty(); i++) {
            messages[i] = FORMATTER_QUEUE.remove();
        }

        return messages;
    }

    /**
     * Gets a single message from a queue, popping it from the queue.
     */
    public static Message getMessage() {
        if (FORMATTER_QUEUE.isEmpty()) {
            return null;
        }
        return FORMATTER_QUEUE.remove();
    }

    /**
     * Adds messages to printing queue for printing with the chat message later.
     * <p>
     * The messages are popped when {@link #getAllMessages} is called.
     *
     * @param messages The messages, separated by lines.
     */
    public static void addToMessageQueue(String... messages) {
        FORMATTER_QUEUE.add(formatMessageStrings(messages));
    }

    /**
     * Adds log messages to printing queue for printing with the chat message later.
     * <p>
     * The messages are popped when {@link #getAllMessages} is called.
     *
     * @param logMessage The log message.
     */
    public static void addLogToMessageQueue(String logMessage) {
        FORMATTER_QUEUE.add(Message.createlogMessage(logMessage));
    }

    /**
     * Adds error messages to printing queue for printing with the chat message later.
     * <p>
     * The messages are popped when {@link #getAllMessages} is called.
     *
     * @param errorMessage The error message.
     */
    public static void addErrorToMessageQueue(String errorMessage) {
        FORMATTER_QUEUE.add(Message.createErrorMessage(errorMessage));
    }
}
