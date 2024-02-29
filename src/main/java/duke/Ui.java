package duke;

import java.util.List;

/**
 * TerminalUI is a utility class that provides methods for printing formatted
 * output to the terminal
 */
public class Ui {
    // Separator line to be used in the terminal
    private static final String SEPARATOR_LINE = "____________________________________________________";

    /**
     * Prints a separator line to the terminal
     */
    public static void printSeparatorLine() {
        System.out.println(SEPARATOR_LINE);
    }

    /**
     * Wraps a string with separator lines and returns the result
     *
     * @param input The string to be wrapped
     * @return The input string wrapped with separator lines
     */
    public static String wrapWithSepLine(String input) {
        return SEPARATOR_LINE + "\n" + input + "\n" + SEPARATOR_LINE;
    }

    /**
     * Prints a list of strings to the terminal
     * Each string in the list is placed on a new line
     *
     * @param list The list of strings to be printed
     */
    public static void printList(List<String> list) {
        System.out.println();
        for (String item : list) {
            System.out.println(item);
        }
    }
}