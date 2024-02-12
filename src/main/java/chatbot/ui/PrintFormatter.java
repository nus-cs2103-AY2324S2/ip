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
     * Gets multiple lines of indented messages from a queue and the arguments.
     * <p>
     * If there are no arguments, the messages stored in the queue would be returned.
     *
     * @param messages The messages, separated by lines.
     */
    public static String formatMessages(String... messages) {
        StringBuilder formattedMessage = new StringBuilder();

        while (!FORMATTER_QUEUE.isEmpty()) {
            formattedMessage.append(FORMATTER_QUEUE.remove());
            formattedMessage.append("\n\n");
        }

        for (String message : messages) {
            formattedMessage.append(message);
            formattedMessage.append("\n\n");
        }
        return formattedMessage.toString().trim();
    }

    /**
     * Adds messages to printing queue for printing with the chat message later.
     * <p>
     * The messages are printed when {@link #formatMessages} is called.
     *
     * @param messages The messages, separated by lines.
     */
    public static void addToFormatterQueue(String... messages) {
        FORMATTER_QUEUE.addAll(Arrays.asList(messages));
    }
}
