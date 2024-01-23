package print;

/**
 * Pretty prints to the console. (System.out)
 *
 * @author Titus Chew
 */
public class Printer {
    private static final String INDENT = "    ";

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
        for (String message : messages) {
            printMessage(message);
        }
        insertLine();
    }
}
