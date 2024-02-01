package sleepy.tools;

/**
 * This class helps to print out the Sleepy AI Chatbot's lines.
 *
 * @author kjw142857
 */
public class LinePrinter {
    /**
     * Prints a given line, with indentation.
     *
     * @param line Line to be printed.
     */
    public static void printLine(String line) {
        System.out.println("  " + line);
    }
    /**
     * Prints the command typed in by the user.
     *
     * @param command Command entered.
     */
    public static void echoCommand(String command) {
        printLine(command);
    }

    /**
     * Prints the exit line.
     */
    public static void printExit() {
        String exitLine = "Bye. Gonna go back to sleep now *yawn*";
        printLine(exitLine);
    }
}
