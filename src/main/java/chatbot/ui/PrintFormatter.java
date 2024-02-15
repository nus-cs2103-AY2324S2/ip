package chatbot.ui;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This encapsulates interactions with the user by printing to the console. (System.out)
 *
 * @author Titus Chew
 */
public final class PrintFormatter {
    /** Stores the messages to print. */
    private static final Queue<String> FORMATTER_QUEUE = new LinkedList<>();

    /**
     * Gets multiple lines of indented messages from a queue.
     * The messages stored in the queue would be cleared.
     */
    public static String getMessages() {
        StringBuilder formattedMessage = new StringBuilder();

        while (!FORMATTER_QUEUE.isEmpty()) {
            formattedMessage.append(FORMATTER_QUEUE.remove());
            formattedMessage.append("\n\n");
        }

        return formattedMessage.toString().trim();
    }

    /**
     * Adds messages to printing queue for printing with the chat message later.
     * <p>
     * The messages are popped when {@link #getMessages} is called.
     *
     * @param messages The messages, separated by lines.
     */
    public static void addToFormatterQueue(String... messages) {
        FORMATTER_QUEUE.addAll(Arrays.asList(messages));
    }
}
