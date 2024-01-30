package chatbot.ui;

import java.util.*;

/**
 * Deals with interactions with the user by printing to the console. (System.out)
 *
 * @author Titus Chew
 */
public final class Printer {
    /**
     * The indentation size.
     */
    private static final String INDENT = "    ";

    /**
     * Stores the messages to print.
     */
    private static final Queue<String> printQueue = new LinkedList<>();

    /**
     * Inserts a horizontal line.
     */
    private static void insertLine() {
        System.out.println(INDENT + "____________________________________________________________");
    }

    /**
     * Prints an indented message to the console.
     *
     * @param message the message to print in the console
     */
    private static void printMessage(String message) {
        String[] lines = message.split("\n");
        for (String line : lines) {
            System.out.println(INDENT + line);
        }
    }

    /**
     * Prints multiple lines of indented messages to the console.
     *
     * @param messages the messages, separated by lines
     */
    public static void printMessages(String... messages) {
        insertLine();

        while (!printQueue.isEmpty()) {
            printMessage(printQueue.remove());
            insertLine();
        }

        for (String message : messages) {
            printMessage(message);
        }
        insertLine();
    }

    /**
     * Add messages to printing queue for printing with the chat message later.
     * The messages are printed when {@link #printMessages} is called.
     *
     * @param messages the messages, separated by lines
     */
    public static void addToPrintQueue(String... messages) {
        printQueue.addAll(Arrays.asList(messages));
    }
}
