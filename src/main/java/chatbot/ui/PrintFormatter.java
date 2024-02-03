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
    /** The indentation size. */
    private static final String INDENT = "    ";

    /** Stores the messages to print. */
    private static final Queue<String> FORMATTER_QUEUE = new LinkedList<>();

    /**
     * Returns a horizontal line.
     *
     * @return the line
     */
    private static String insertLine() {
        return INDENT + "____________________________________________________________";
    }

    /**
     * Prints an indented message to the console.
     *
     * @param message the message to print in the console
     */
    private static String formatMessage(String message) {
        StringBuilder formattedMessage = new StringBuilder();

        String[] lines = message.split("\n");
        for (String line : lines) {
            formattedMessage.append(INDENT + line);
        }

        return formattedMessage.toString();
    }

    /**
     * Prints multiple lines of indented messages to the console.
     *
     * @param messages the messages, separated by lines
     */
    public static String formatMessages(String... messages) {
        StringBuilder formattedMessage = new StringBuilder();

        while (!FORMATTER_QUEUE.isEmpty()) {
            formattedMessage.append(formatMessage(FORMATTER_QUEUE.remove()));
            formattedMessage.append(insertLine());
        }

        for (String message : messages) {
            formattedMessage.append(formatMessage(message));
        }
        return formattedMessage.toString();
    }

    /**
     * Add messages to printing queue for printing with the chat message later.
     * The messages are printed when {@link #formatMessages} is called.
     *
     * @param messages the messages, separated by lines
     */
    public static void addToFormatterQueue(String... messages) {
        FORMATTER_QUEUE.addAll(Arrays.asList(messages));
    }
}
